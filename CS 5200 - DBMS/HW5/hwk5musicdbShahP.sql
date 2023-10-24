use musicShahP;

-- 1 SELECT
-- @block
select artist_name, record_label_id from artists;
-- ------------------------------------------------

-- 2 GROUP
select 	record_label_id, record_label.label_name,
		count(artist_name) as `signed_artists`        

from artists

right join record_label on record_label.rid = artists.record_label_id
group by record_label_id, record_label.label_name
order by (`signed_artists`) desc;
-- ------------------------------------------------

-- 3 GROUP AND COUNT
select aid, count(user_id) as num_followers
from user_follows_artist
group by aid;
-- ------------------------------------------------

-- 4 table using select *
drop table if exists rock_songs;
create table rock_songs as
select * from songs where genre_id = 1;
select * from rock_songs;
-- ------------------------------------------------

-- 5 INNER JOIN 
select 	artist_performs_song.sid as `Song ID`, songs.song_name as `Song Title`,
		albums.album_name as `Album Name`, record_label.label_name as `Recording Label`, 
        artists.artist_name as `Artist Name`
from artist_performs_song
left join songs on songs.sid = artist_performs_song.sid
left join albums on albums.alid = songs.album_id
left join artists on artists.aid = artist_performs_song.aid
left join record_label on artists.record_label_id = record_label.rid

order by(`Song ID`);
-- ------------------------------------------------

-- 6 GROUP CONCAT
select 	songs.song_name as `Song Title`,
		albums.album_name, 
        record_label.label_name,
		group_concat(artists.artist_name order by artists.artist_name asc) as Artists
        
	from artist_performs_song

		inner join artists on artists.aid = artist_performs_song.aid
		inner join songs on songs.sid = artist_performs_song.sid
		inner join albums on albums.alid = songs.album_id
		inner join record_label on record_label.rid = artists.record_label_id

		-- make them unique
		GROUP BY artist_performs_song.sid, songs.song_name, albums.album_name, record_label.label_name
        order by (`Song Title`) asc;
-- ------------------------------------------------


-- 7 WHERE CLAUSE
select song_name
from songs
where sid =
(select 	artist_performs_song.sid as `Song ID`
		
			from 	artist_performs_song
	
			group by(artist_performs_song.sid)
			order by count(artist_performs_song.aid) desc
			limit 1);
-- ------------------------------------------------

-- 8 LEFT JOIN
select 	artists.artist_name,
		count(user_follows_artist.user_id) as `num_followers`
        
        from artists
        
        left join user_follows_artist on user_follows_artist.aid = artists.aid
        group by(artists.artist_name)
        order by(`num_followers`) desc;
-- ------------------------------------------------        

        
-- 9
select 	record_label.label_name, 
		count(albums.alid) as `num_albums`
        
        from record_label
        left join artists on artists.record_label_id = record_label.rid
        left join albums on albums.artist = artists.artist_name
        
        group by(record_label.rid)
        order by(`num_albums`) desc;
-- ------------------------------------------------
        
        
-- 10 LEFT JOIN
select 	genres.genre_name as `Genre`,
		count(songs.sid) as `num_songs`

		from genres
        left join songs on songs.genre_id = genres.gid
        
        group by (`Genre`)
        order by(`num_songs`) desc;
-- ------------------------------------------------

-- 11 
select 	
		songs.song_name as `Song Title`,
		albums.album_name,
        albums.artist,
        genres.genre_name,
        moods.mood_name,
        count(user_follows_artist.user_id)
        
        from songs
        
        left join albums on songs.album_id = albums.alid
        left join genres on songs.genre_id = genres.gid
        left join moods on songs.mood_id = moods.mid
        left join artists on albums.artist = artists.artist_name
        left join user_follows_artist on user_follows_artist.aid = artists.aid
        
        GROUP BY
				songs.song_name,
				albums.album_name,
				albums.artist,
				genres.genre_name,
				moods.mood_name;

-- ------------------------------------------------
        
-- 12 HAVING
select 	artists.artist_name
        
        from user_follows_artist
        
        inner join user on user.user_id = user_follows_artist.user_id
        inner join artists on artists.aid = user_follows_artist.aid
        
        group by(user_follows_artist.aid)
        having count(user_follows_artist.user_id) = 
				(select count(distinct user_id) from user);
-- ------------------------------------------------

-- 13 
select 	user.*,
		count(ufa.aid) as total_count

		from user_follows_artist as ufa
        
        inner join user on ufa.user_id = user.user_id
		group by(user.user_id)
        
        having count(ufa.aid) = (SELECT COUNT(DISTINCT aid) FROM artists);
					-- (select count(distinct ufa.aid));
					-- This didn't work maybe due to `ufa` ??
-- ------------------------------------------------

-- 14 
select 	albums.*,
		record_label.label_name

		from albums

        inner join artists on artists.artist_name = albums.artist
        inner join record_label on record_label.rid = artists.record_label_id

        where record_label.label_name like '%Music%';
-- ------------------------------------------------

-- 15
select 	group_concat(user.user_name SEPARATOR ', ') as username,
		artists.artist_name
		
		from user_follows_artist as ufa
        
        inner join user on ufa.user_id = user.user_id
        inner join artists on artists.aid = ufa.aid
        
        group by artists.artist_name
		having count(user.user_name) > 1
        order by artists.artist_name asc;
-- ------------------------------------------------

-- 16 
select 	artists.artist_name,
		count(albums.alid) as num_albums
        
        from albums
        
        right join artists on artists.artist_name = albums.artist
        
        group by(artists.artist_name)
        order by (num_albums) desc;
-- ------------------------------------------------

-- 17
select * from artist_performs_song;
select 	artists.artist_name,
		count(songs.sid) as most_songs
        
        from songs
        
        right join albums on albums.alid = songs.album_id
        right join artists on artists.artist_name = albums.artist
        right join artist_performs_song on 
				artist_performs_song.sid = songs.sid

        group by(artists.artist_name)
        order by(most_songs) desc;
-- ------------------------------------------------

-- @block
-- 18 Artist who PERFORMED most number of songs
select 	artists.artist_name,
		count(artist_performs_song.sid) as num_songs
        
		from artist_performs_song
        inner join artists on artist_performs_song.aid = artists.aid
        
		group by(artists.artist_name)
        order by num_songs desc;
-- ------------------------------------------------


-- 19 
select 	record_label.label_name, 
		count(songs.sid)
		
        from songs
        
        inner join albums on albums.alid = songs.album_id
        inner join artists on artists.artist_name = albums.artist
        left join record_label on record_label.rid = artists.record_label_id
        
        group by(record_label.label_name)
        order by (count(songs.sid)) desc;
-- ------------------------------------------------		
        