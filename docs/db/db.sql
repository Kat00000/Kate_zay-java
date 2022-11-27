CREATE TABLE room (
	id integer PRIMARY KEY AUTOINCREMENT,
	apartment varchar NOT NULL,
	number varchar NOT NULL,
	number_bed integer NOT NULL,
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

CREATE TABLE orderObject (
	id integer PRIMARY KEY AUTOINCREMENT,
	service_id integer NOT NULL REFERENCES service(id),
	time_stay integer NOT NULL,
	user_id integer  REFERENCES user(id),
	room_id integer NOT NULL REFERENCES room(id),
	created datetime NOT NULL,
	updated datetime NOT NULL
);





