USE musicshahp;

-- 1 Write a function num_songs_with_genre(genre_p) that accepts a genre name and 
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

SELECT num_songs_with_genre('none');
SELECT num_songs_with_genre('Rock');



-- 4 Write a function named album_length(length_p)  that accepts one parameter, 
-- a count of songs and returns the number of albums with that length (5 points)
DELIMITER $$
CREATE FUNCTION album_length (
    input_num_songs INT
    ) RETURNS INT DETERMINISTIC
    BEGIN
        DECLARE total_num_albums INT;
			SELECT COUNT(num_albums) INTO total_num_albums
			FROM (SELECT    COUNT(songs.sid) AS count_songs,
							songs.album_id AS num_albums
							FROM songs
							GROUP BY (songs.album_id)
							HAVING count_songs = input_num_songs) 
							as dp;
        RETURN total_num_albums;
    END $$
DELIMITER ;

SELECT album_length(1);
SELECT album_length(2);
SELECT album_length(4);


-- Write a function named more_followers(artist1,artist2). 
-- It accepts 2 artist names and returns 1 if artist1 has more followers than artist2, 0 if they have the same number of followers  , 
-- and -1 if artist2 has more followers that  artist1. (5 points)
DELIMITER $$
CREATE FUNCTION more_followers (
	input_artist1 VARCHAR(20),
    input_artist2 VARCHAR(20)
    ) RETURNS INT DETERMINISTIC
    BEGIN
		DECLARE result INT;

    END $$
DELIMITER ;


