CREATE TABLE room (
	id integer PRIMARY KEY AUTOINCREMENT,
	apartment varchar NOT NULL,
	numberBeds integer NOT NULL,
	price integer NOT NULL,
	status integer NOT NULL,
	created datetime NOT NULL,
	updated datetime NOT NULL
);

CREATE TABLE service (
	id integer PRIMARY KEY AUTOINCREMENT,
	type varchar NOT NULL,
	price float NOT NULL,
	created datetime NOT NULL,
	updated datetime NOT NULL
);

CREATE TABLE user (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar NOT NULL,
	email varchar NOT NULL,
	password varchar NOT NULL,
	created datetime NOT NULL,
	updated datetime NOT NULL
);

CREATE TABLE orderr (
	id integer PRIMARY KEY AUTOINCREMENT,
	serviceId integer NOT NULL REFERENCES service(id),
	timeStay integer NOT NULL,
	userId integer  REFERENCES user(id),
	roomId integer NOT NULL REFERENCES room(id),
	created datetime NOT NULL,
	updated datetime NOT NULL
);





