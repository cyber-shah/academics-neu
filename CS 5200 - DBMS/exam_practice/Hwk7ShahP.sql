USE musicshahp;

-- 1
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

