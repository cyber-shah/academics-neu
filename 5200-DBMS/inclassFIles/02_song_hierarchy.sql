-- @block create database
CREATE DATABASE song_db;
use song_db;

-- @block create table
-- {MANDATORY, OR}
CREATE TABLE reggae (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    tempo INT,
    meter INT,
    staccato BOOLEAN NOT NULL);

CREATE TABLE rock (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    tempo INT,
    meter INT,
    guitar BOOLEAN NOT NULL);

CREATE TABLE jazz (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    tempo INT,
    meter INT,
    improv BOOLEAN,
    scat BOOLEAN NOT NULL);
-- the problem here is that there is no query that can be answered as
-- song type? because the type is created as a table? that doesn't make sense.
-- no unique ids as well?

-- @block lets see an alternative
-- song_type.genre can take on values or names of the types
CREATE TABLE song_type (
    genre VARCHAR(64) PRIMARY KEY,
)

create table song (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    tempo INT,
    meter INT,
    improv BOOLEAN,
    scat BOOLEAN,
    guitar BOOLEAN,
    staccato BOOLEAN,
    song_type VARCHAR(50) NOT NULL,

    FOREIGN KEY (song_type) REFERENCES
    song_type(genre) ON UPDATE CASCADE ON DELETE CASCADE,
    CHECK(
        (song_type == 'Rock' AND guitar IS NOT NULL) or
        (song_type == 'Jazz' AND improv IS NOT NULL AND scat IS NOT NULL) or
        (song_type == 'Reggae' AND staccato IS NOT NULL)
    ),
);


-- @block another alternative
-- without a foreign key and a different entity
create table song (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    tempo INT,
    meter INT,
    improv BOOLEAN,
    scat BOOLEAN,
    guitar BOOLEAN,
    staccato BOOLEAN,
    song_type VARCHAR(50) NOT NULL,

    FOREIGN KEY (song_type) REFERENCES
    song_type ENUM ('Rock', 'Jazz', 'Reggae') ON UPDATE CASCADE ON DELETE CASCADE,
    CHECK(
        (song_type == 'Rock' AND guitar IS NOT NULL) or
        (song_type == 'Jazz' AND improv IS NOT NULL AND scat IS NOT NULL) or
        (song_type == 'Reggae' AND staccato IS NOT NULL)
    ),
);



-- @block what if its optional and not mandatory?
-- {OPTIONAL, OR}
create table song (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    tempo INT,
    meter INT,
    improv BOOLEAN,
    song_type VARCHAR(50));

create table rock (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    guitar BOOLEAN NOT NULL,
    FOREIGN KEY (song_id) REFERENCES song(song_id)
    ON UPDATE CASCADE ON DELETE CASCADE );

create table reggae (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    staccato BOOLEAN NOT NULL,
    FOREIGN KEY (song_id) REFERENCES song(song_id)
    ON UPDATE CASCADE ON DELETE CASCADE );

create table jazz (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    scat BOOLEAN NOT NULL,
    FOREIGN KEY (song_id) REFERENCES song(song_id)
    ON UPDATE CASCADE ON DELETE CASCADE );

-- @block {mandatory, and}
create table song (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    tempo INT,
    meter INT,
    improv BOOLEAN,
    song_type VARCHAR(50),
    is_reggae BOOLEAN,
    is_rock BOOLEAN,
    is_jazz BOOLEAN,
    scat BOOLEAN,
    staccato BOOLEAN,
    guitar BOOLEAN,
    improv BOOLEAN,
    CHECK (is_reggae == TRUE and staccato IS NOT NULL) or
    (is_rock == TRUE and guitar IS NOT NULL) or
    (is_jazz == TRUE and scat IS NOT NULL and improv IS NOT NULL));

