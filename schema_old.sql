	
CREATE TABLE POST(
PID INT,
TITLE VARCHAR(100),
DESCRIPTION VARCHAR(250),
LINK VARCHAR(100),
CONSTRAINT PK_KEY1 PRIMARY KEY(PID)
);


CREATE TABLE USERLIST(
USRID INT,
UNAME VARCHAR(20),
CONSTRAINT PK_KEY2 PRIMARY KEY(USRID)
);


CREATE TABLE PUBLISH(
USRID INT,
PID INT,
POSTTIME DATE,
CONSTRAINT PK_KEY3 PRIMARY KEY(USRID,PID),
CONSTRAINT FK_KEY FOREIGN KEY(USRID) REFERENCES USERLIST(USRID)
);

CREATE TABLE COMMENTS(
USRID INT,
PID INT,
TIME DATE,
CONTENT VARCHAR(100),
CONSTRAINT PK_KEY4 PRIMARY KEY(USRID,PID),
CONSTRAINT FK_KEY1 FOREIGN KEY(USRID) REFERENCES USERLIST(USRID),
CONSTRAINT FK_KEY2 FOREIGN KEY(PID) REFERENCES POST(PID)
);

CREATE TABLE POSTLIKE(
USRID INT,
PID INT,
TIME DATE,
CONSTRAINT PK_KEY5 PRIMARY KEY(USRID,PID),
CONSTRAINT FK_KEY3 FOREIGN KEY(USRID) REFERENCES USERLIST(USRID),
CONSTRAINT FK_KEY4 FOREIGN KEY(PID) REFERENCES POST(PID)
);

CREATE TABLE POSTDISLIKE(
USRID INT,
PID INT,
TIME DATE,
CONSTRAINT PK_KEY6 PRIMARY KEY(USRID,PID),
CONSTRAINT FK_KEY5 FOREIGN KEY(USRID) REFERENCES USERLIST(USRID),
CONSTRAINT FK_KEY6 FOREIGN KEY(PID) REFERENCES POST(PID)
);

CREATE TABLE FRIENDSLIST(
USRID INT,
FRIEND INT,
CONSTRAINT PK_KEY7 PRIMARY KEY(USRID,FRIEND),
CONSTRAINT FK_KEY7 FOREIGN KEY(USRID) REFERENCES USERLIST(USRID),
CONSTRAINT FK_KEY8 FOREIGN KEY(USRID) REFERENCES USERLIST(USRID)
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
NAME VARCHAR(100),
GENDER VARCHAR(2),
DOB DATE,
BIRTHCITY  VARCHAR(100),
BIRTHSTATE VARCHAR(100),
COUNTRY VARCHAR(30),
SPORT VARCHAR(25), 
CONSTRAINT PK_KEY11 PRIMARY KEY(AID)
);

CREATE TABLE EVENTS(
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
TEAM VARCHAR(30),
RNK VARCHAR(5),
MEDALSWON VARCHAR(10),
CONSTRAINT PK_KEY13 PRIMARY KEY (AID,EVENTID),
CONSTRAINT FK_KEY12 FOREIGN KEY(EVENTID) REFERENCES EVENTS(EID),
CONSTRAINT FK_KEY13 FOREIGN KEY(AID) REFERENCES ATHELETEPERSONALINFO(AID)
);









