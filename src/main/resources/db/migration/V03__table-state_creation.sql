create table state (
	id bigint not null auto_increment,
	name varchar(80) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;

insert into state (name) select distinct state_name from city;

alter table city add column state bigint not null;

update city c set c.state = (select e.id from state e where e.name = c.state_name);

alter table city add constraint fk_city_state
foreign key (state) references state (id);

alter table city drop column state_name;

alter table city change city_name name varchar(80) not null;