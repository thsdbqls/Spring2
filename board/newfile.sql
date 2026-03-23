
/* Drop Tables */

DROP TABLE BOARD;
DROP TABLE USERS;




/* Create Tables */

CREATE TABLE BOARD
(
	id int PRIMARY KEY,
	title varchar(128) NOT NULL,
	user_id int NOT NULL
);


CREATE TABLE USERS
(
	id int PRIMARY KEY,
	username varchar(9) NOT NULL UNIQUE,
	password varchar(8) NOT NULL
);



/* Create Foreign Keys */

ALTER TABLE BOARD
	ADD FOREIGN KEY (id)
	REFERENCES USERS (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



