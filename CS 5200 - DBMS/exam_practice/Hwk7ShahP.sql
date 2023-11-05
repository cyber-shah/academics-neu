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

DELIMITER $$
CREATE PROCEDURE song_has_genre (
	input_name VARCHAR(50)
)
BEGIN


END $$
DELIMITER ;




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



