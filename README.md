# MyTunes
Final semester Assignement, join work with Adam (https://github.com/Ladam0203) and Jàn (Jano) (https://github.com/pelonidas)
Mp3 Manager with relational database;
Script to create the database:
--Database creation
CREATE DATABASE MyTunesTeam1;

-- Tables creation:

create table AUTHOR (
	id int identity,
	name nvarchar(1000)
);

create table CATEGORY (
	id int identity,
	name nvarchar(1000)
);

create table CORR_SONG_PLAYLIST (
	id int identity,
	songID int not null,
	playListID int not null,
	rankSong int not null
);

create table PLAYLIST (
	id int identity,
	name nvarchar(1000)
);


create table SONG (
	id int identity,
	name nvarchar(1000),
	authorID int not null,
	categoryID int not null,
	songFile nvarchar(1000),
	duration int default 0 not null
);

Team 1.
