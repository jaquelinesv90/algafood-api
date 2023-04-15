CREATE TABLE CITY(
	id BIGINT NOT NULL AUTO_INCREMENT,
	city_name VARCHAR(80) NOT NULL,
	state_name VARCHAR(80) NOT NULL,

	PRIMARY KEY(id)
)engine=InnoDB default charset=utf8;