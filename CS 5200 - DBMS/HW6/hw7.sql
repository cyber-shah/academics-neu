-- @block
-- 1. Write a function num_songs_with_genre(genre_p) that accepts a 
-- genre name and returns the number of songs with the genre. (5 points) 
USE musicshahp;

DELIMITER $$
CREATE FUNCTION num_songs_with_genre (
    input_genre_name VARCHAR(50)
    ) RETURNS INT DETERMINISTIC
    BEGIN
        DECLARE genre_count INT;
            SELECT COUNT(songs.sid) INTO genre_count
            FROM songs
            INNER JOIN genres ON genres.gid = songs.genre_id
            WHERE genres.genre_name = input_genre_name;
        RETURN genre_count;
    END $$
DELIMITER ;

-- @block
SELECT num_songs_with_genre('Rock');
SELECT num_songs_with_genre('none');






-- @block
-- 4. Write a function named album_length(length_p)  
-- that accepts one parameter, a count of songs and returns the number 
-- of albums with that length (5 points)
DELIMITER $$
CREATE FUNCTION album_length (
    input_num_songs INT
    ) RETURNS INT DETERMINISTIC
    BEGIN
        DECLARE num_albums INT;
        SELECT COUNT(num_albums)
        FROM (SELECT    COUNT(songs.sid) AS count_songs,
                        songs.album_id AS num_albums
                        FROM songs
                        GROUP BY (songs.album_id)
                        HAVING count_songs = 1) 
                        as dp;
        



-- @block
SELECT COUNT(num_albums)
FROM (SELECT    COUNT(songs.sid) AS count_songs,
                songs.album_id AS num_albums
                FROM songs
                GROUP BY (songs.album_id)
                HAVING count_songs = 1) 
                as dp;


