create table IF NOT EXISTS persons (
	id serial not null,
	firstname varchar(30) not null,
	lastname varchar(30) not null,
	age int,
	group_number int, 
	constraint pk_person_id primary key (id)
);

create table IF NOT EXISTS subjects (
	id serial not null,
	subject_name varchar(50) not null,
	unique (subject_name),
	constraint pk_subject_id primary key (id)
);

create table IF NOT EXISTS grades (
	grade int,
	person_id int references persons (id),
	subject_name varchar(50) references subjects (subject_name),
	primary key (person_id, subject_name)
);
