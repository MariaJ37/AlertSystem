create table if not exists batteryPerc(
	roomCode int primary key,
	percentage int
);

create table if not exists alerts(
	aID int auto_increment UNIQUE,
	roomCode int,
	alert varchar(120),
	timeOf dateTime,
	dismissed bool DEFAULT 0,
	constraint PK_roomCode_alert primary key (roomCode,alert)
);

create table if not exists lastMovement(
	roomCode int primary key,
	timeOfMov dateTime
);

create table if not exists wifiStatus(
	roomCode int primary key,
	lastPing dateTime
);

create table if not exists patientInfo(
	roomCode int primary key,
	patientName varchar(30),
	address varchar(50),
	phone char(14),
	emergencyName varchar(30),
	emergencyPhone char(14)
);

create table if not exists users(
	username varchar(30),
	roomCode int,
	constraint PK_username_roomCode primary key (username, roomCode)
);

create table if not exists alertParameters(
	paraID int primary key,
	percent int,
	ping int,
	move int,
	notification int
);