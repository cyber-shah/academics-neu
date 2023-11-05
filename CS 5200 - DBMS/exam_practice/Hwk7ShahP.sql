USE musicshahp;

-- 1 
-- Write a function num_songs_with_genre(genre_p) that accepts a genre name and 
-- returns the number of songs with the genre. (5 points) 
DELIMITER $$
CREATE FUNCTION num_songs_with_genre (
    input_genre_name VARCHAR(50)
) RETURNS INT DETERMINISTIC
BEGIN
    DECLARE genre_count INT;
    SELECT COUNT(songs.sid) INTO genre_count
    FROM songs
    INNER JOIN genres on genres.gid = songs.genre_id
    WHERE genres.genre_name = input_genre_name;
    RETURN genre_count;
END $$
DELIMITER ;

SELECT NUM_SONGS_WITH_GENRE('none');
SELECT NUM_SONGS_WITH_GENRE('Rock');

-- 2. Write a procedure get_artists_with_label(label_p) that accepts a record label name and 
-- returns a result set of all artist names and the corresponding label name. (5 points) 

DELIMITER $$
CREATE PROCEDURE get_artists_with_label (
	input_label VARCHAR(50)
)
BEGIN
	SELECT artists.artist_name,
    r.label_name
    from artists
    inner join record_label as r on r.rid = artists.record_label_id
    where r.label_name = input_label;
    
END $$
DELIMITER ;

call get_artists_with_label('Island Records');
call get_artists_with_label('Capitol Music Group');


-- 3
-- Write a procedure named song_has_genre(genre_p)  that accepts a genre name and  
-- returns a result set of the songs with that genre. 
-- The result should contain the song id , the song name, and the album name. 
-- If a genre is provided that is not found in the genre table, generate an error from the procedure 
-- stating that the passed genre is not valid and use SIGNAL to throw error ‘45000’.  (10 points)

drop procedure if exists song_has_genre;

DELIMITER $$
CREATE PROCEDURE song_has_genre (input_name VARCHAR(50))
BEGIN
  DECLARE genre_count INT;

  SELECT COUNT(*) INTO genre_count
  FROM genres
  WHERE genre_name = input_name;

  IF genre_count = 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Genre not found in database';
  ELSE
    SELECT 
      songs.sid, songs.song_name, albums.album_name
    FROM
      songs
        INNER JOIN
      albums ON albums.alid = songs.album_id
        INNER JOIN
      genres ON genres.gid = songs.genre_id
    WHERE
      genres.genre_name = input_name;
  END IF;
END $$
DELIMITER ;


select * from genres;
call song_has_genre('Rock');
call song_has_genre('Hey');



-- 4 
-- Write a function named album_length(length_p)  that accepts one parameter, 
-- a count of songs and returns the number of albums with that length (5 points)
DELIMITER $$
CREATE FUNCTION album_length (
    input_num_songs INT
    ) RETURNS INT DETERMINISTIC
    BEGIN
        DECLARE total_num_albums INT;
			SELECT 
    COUNT(num_albums)
INTO total_num_albums FROM
    (SELECT 
        COUNT(songs.sid) AS count_songs,
            songs.album_id AS num_albums
    FROM
        songs
    GROUP BY (songs.album_id)
    HAVING count_songs = input_num_songs) AS dp;
        RETURN total_num_albums;
    END $$
DELIMITER ;

SELECT ALBUM_LENGTH(1);
SELECT ALBUM_LENGTH(2);
SELECT ALBUM_LENGTH(4);



-- 5. Write a procedure  named get_song_details()  that accepts a song name as an argument  
-- and returns the song name, the song id, the recording label, the album name, the genre name and 
-- the mood name. (10 points)

DELIMITER $$
CREATE PROCEDURE get_song_details (input_name VARCHAR(50))
BEGIN
	SELECT 
		s.song_name,
		s.sid,
		r.label_name,
		al.album_name,
		g.genre_name,
		m.mood_name
	FROM
		songs AS s
			LEFT JOIN
		albums AS al ON al.alid = s.album_id
			LEFT JOIN
		artists ON artists.artist_name = al.artist
			LEFT JOIN
		record_label AS r ON r.rid = artists.record_label_id
			LEFT JOIN
		genres AS g ON g.gid = s.genre_id
			LEFT JOIN
		moods AS m ON m.mid = s.mood_id
	WHERE 
		s.song_name = input_name;
END $$
DELIMITER ;

call get_song_details('Escape');
call get_song_details('Habit');
call get_song_details('');




-- 6 
-- Write a function named more_followers(artist1,artist2). 
-- It accepts 2 artist names and returns 1 if artist1 has more followers than artist2, 0 if they have the same number of followers  , 
-- and -1 if artist2 has more followers that  artist1. (5 points)

-- part 1: a function that returns count for an artist
DELIMITER $$
CREATE FUNCTION artist_followers (
	input_artist_name VARCHAR(20)
    ) RETURNS INT DETERMINISTIC
    BEGIN
		DECLARE result INT;
		SELECT 
    COUNT(user_follows_artist.user_id) into result
FROM
    artists
        INNER JOIN
    user_follows_artist ON user_follows_artist.aid = artists.aid
WHERE artists.artist_name  = input_artist_name;
return result;
    END $$
DELIMITER ;

SELECT ARTIST_FOLLOWERS('Vulfpeck');
SELECT ARTIST_FOLLOWERS('Low Hum');

-- part 2: compare
DELIMITER $$
CREATE FUNCTION more_followers (
	input_artist1 VARCHAR(20),
    input_artist2 VARCHAR(20)
    ) RETURNS INT DETERMINISTIC
    BEGIN
		DECLARE result INT;
		IF artist_followers(input_artist1) > artist_followers(input_artist2) THEN
			set result = 1;
        ELSEIF artist_followers(input_artist1) < artist_followers(input_artist2) THEN
			set result = -1;
		ELSE
			set result = 0;
		END IF;
        RETURN result;
    END $$
DELIMITER ;

SELECT MORE_FOLLOWERS('Vulfpeck', 'Low Hum');
SELECT MORE_FOLLOWERS('Low Hum', 'Vulfpeck');
SELECT MORE_FOLLOWERS('Low Hum', 'Still Woozy');



-- 7
-- Create a procedure named create_song( title_p, artist_p, record_label_p, mood_p, genre_p, album_title) 
-- that inserts a song  into the database . Make sure you create the appropriate tuples in the album and 
-- other required tables before attempting to insert the song.  
-- Also, ensure that the specified record label, genre name and mood name already exist in the database. 
-- If they do not exist, use SIGNAL with error number 450000.  When adding a song, it can be associated with
-- a known artist’s current existing album or the song could belong to a new album for the artist. 
-- Also, assume the producer of the song performs on the song. 
-- (HINT: The combination of album name and musician is unique for each album. ) 
-- Insert the following song  into the song table.Title = “Me about You” , Artist = “The Turtles”,  
-- recording_label  = “Def Jam Recordings“ , genre = “Pop”, mood = “Calm”, album = “Happy Together”. 
-- Please also provide SELECT statements that verify the tuples have been inserted into the appropriate tables.  (10 points)
DELIMITER $$
CREATE PROCEDURE create_song (
	title_p VARCHAR(50),
	artist_p VARCHAR(50), 
	record_label_p VARCHAR(50),
	mood_p VARCHAR(50),
	genre_p VARCHAR(50),
	album_title VARCHAR(50)
)
BEGIN
	DECLARE fetch_mood_id INT;
	DECLARE fetch_genre_id INT;
	DECLARE fetch_record_id INT;
    DECLARE fetch_alid INT;
        
	SELECT gid INTO fetch_genre_id FROM genres WHERE genre_name = genre_p LIMIT 1;
	IF fetch_genre_id IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Genre not found in database';
	END IF;

	SELECT mid INTO fetch_mood_id FROM moods WHERE mood_name = mood_p LIMIT 1;
	IF fetch_mood_id IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Mood not found in database';
	END IF;

	SELECT rid INTO fetch_record_id FROM record_label WHERE label_name = record_label_p LIMIT 1;
	IF fetch_record_id IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Record not found in database';
	END IF;
    
    SELECT alid INTO fetch_alid FROM albums WHERE album_name = album_title LIMIT 1;
	IF fetch_record_id IS NULL THEN
		INSERT INTO albums 		(album_name)
        VALUES					(album_title);
	END IF;

    
    INSERT INTO songs	(song_name, mood_p, genre_p)
    VALUES 				(title_p, mood_p, genre_p);
END $$
DELIMITER ;




-- 8. Write a procedure named get_songs_with_mood() that accepts a mood name and  
-- returns the song name, the mood name, mood description and the artist who released the song. (5 points)
DELIMITER $$
CREATE PROCEDURE get_songs_with_mood (input_name VARCHAR(50))
BEGIN
	SELECT 
		s.song_name,
		m.mood_name,
        m.mood_description,
        artists.artist_name
	FROM
		songs AS s
			LEFT JOIN
		albums AS al ON al.alid = s.album_id
			LEFT JOIN
		artists ON artists.artist_name = al.artist
			LEFT JOIN
		moods AS m ON m.mid = s.mood_id
	WHERE 
		m.mood_name = input_name;
END $$
DELIMITER ;

call get_songs_with_mood('Happy');
call get_songs_with_mood('Calm');




-- 10. Create a procedure named update_all_artists_num_releases( ) that assigns the artist.num_releases  
-- to the correct value. The correct value is determined by the number of albums the artist has released. 
-- Use the procedure from problem 9 to complete this procedure. 
-- You will need a cursor and a handler to complete this procedure (5 points)
