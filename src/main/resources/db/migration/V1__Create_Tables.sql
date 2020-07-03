CREATE TABLE IF NOT EXISTS branch (
	id UUID PRIMARY KEY,
	name varchar(255) ,
	email varchar(255) ,
	super bool ,
	address jsonb ,
	right_of_access_to_the_courses varchar(255)
);

CREATE TABLE IF NOT EXISTS learner (
	id UUID PRIMARY KEY,
	name varchar(255) ,
	surname varchar(255) ,
	fiscal_code varchar(255) ,
	date_of_birth Date ,
	birth_place varchar(255) ,
	phone varchar(255) ,
	email varchar(255) ,
	degree_of_studies varchar(255) ,
	course_of_study varchar(255) ,
    note varchar(255) ,
	residential_address jsonb ,
	domicile_address jsonb ,
	domicile_equals_residential bool
);
