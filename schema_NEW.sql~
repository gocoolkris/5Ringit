create table post(
pid int,
usrid int,
title varchar(100),
description varchar(250),
lnk varchar(100),
posttime TIMESTAMP,
SCORE FLOAT,
CONSTRAINT PK_KEY1 PRIMARY KEY(PID)
);

create table userlist(
usrid int,
uname varchar(20),
passwrd varchar(20),
constraint pk_key2 primary key(usrid)
);


create table comments(
usrid int,
pid int,
posttime timestamp,
content varchar(250),
constraint pk_key4 primary key(usrid,pid),
constraint fk_key1 foreign key(usrid) references userlist(usrid),
constraint fk_key2 foreign key(pid) references post(pid)
);

create table postlike(
usrid int,
pid int,
liketime timestamp,
constraint pk_key5 primary key(usrid,pid),
constraint fk_key3 foreign key(usrid) references userlist(usrid),
constraint fk_key4 foreign key(pid) references post(pid)
);


create table postdislike(
usrid int,
pid int,
disliketime timestamp,
constraint pk_key6 primary key(usrid,pid),
constraint fk_key5 foreign key(usrid) references userlist(usrid),
constraint fk_key6 foreign key(pid) references post(pid)
);

CREATE TABLE FOLLOWERLIST(
FOLLOWERID INT,
FOLLOWEEID INT,
CONSTRAINT PK_KEY7 PRIMARY KEY(FOLLOWERID,FOLLOWEEID),
CONSTRAINT FK_KEY7 FOREIGN KEY(FOLLOWERID) REFERENCES USERLIST(USRID),
CONSTRAINT FK_KEY8 FOREIGN KEY(FOLLOWEEID) REFERENCES USERLIST(USRID)
);

CREATE TABLE COUNTRIES(
CID INT,
COUNTRY VARCHAR(100),
CONSTRAINT PK_KEY8 PRIMARY KEY (CID)
);

CREATE TABLE SUMMEROLYMPICS(
CID INT,
RNK INT,
YEAR INT,
GOLD INT,
SILVER INT,
BRONZE INT,
TOTAL INT,
CONSTRAINT PK_KEY9 PRIMARY KEY(CID,YEAR),
CONSTRAINT FK_KEY9 FOREIGN KEY(CID) REFERENCES COUNTRIES(CID)
);

CREATE TABLE WINTEROLYMPICS(
CID INT,
RNK INT,
YEAR INT,	
GOLD INT,
SILVER INT,
BRONZE INT,
TOTAL INT,
CONSTRAINT PK_KEY10 PRIMARY KEY(CID,YEAR),
CONSTRAINT FK_KEY10 FOREIGN KEY(CID) REFERENCES COUNTRIES(CID)
);


CREATE TABLE ATHELETEPERSONALINFO(
AID INT,
USRID INT,
NAME VARCHAR(100),
GENDER VARCHAR(10),
DOB VARCHAR(100),
BIRTHCITY  VARCHAR(100),
BIRTHSTATE VARCHAR(100),
COUNTRY VARCHAR(100),
SPORT VARCHAR(80), 
CONSTRAINT PK_KEY11 PRIMARY KEY(AID),
CONSTRAINT FK_KEY11 FOREIGN KEY(USRID) REFERENCES USERLIST(USRID)
);

CREATE TABLE EVENT(
EID INT,
YEAR INT,
SEASON VARCHAR(25),
CITY VARCHAR(100),
EVENTNAME VARCHAR(100),
CONSTRAINT PK_KEY12 PRIMARY KEY(EID)	
);

CREATE TABLE ATHELETEPARTICIPATION(
AID INT,	
EVENTID INT,
AGE INT,
TEAM VARCHAR(80),
RNK VARCHAR(15),
MEDALSWON VARCHAR(80),
CONSTRAINT PK_KEY13 PRIMARY KEY (AID,EVENTID),
CONSTRAINT FK_KEY12 FOREIGN KEY(EVENTID) REFERENCES EVENT(EID),
CONSTRAINT FK_KEY13 FOREIGN KEY(AID) REFERENCES ATHELETEPERSONALINFO(AID)
);
