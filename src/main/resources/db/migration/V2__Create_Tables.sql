CREATE TABLE IF NOT EXISTS course (
	id UUID PRIMARY KEY,
	title varchar(255) ,
	maximum_number_of_learner int ,
	start_date timestamptz ,
	last_update timestamptz ,
	state varchar(255),
	number_of_learner int,
	id_creator UUID
);