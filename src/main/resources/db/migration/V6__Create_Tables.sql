CREATE TABLE IF NOT EXISTS alert (
	id UUID PRIMARY KEY,
	course_code varchar(255) NULL,
	course_name varchar(255) NULL,
	start_date timestamptz NULL,
	number_of_days numeric NULL,
	alert_description varchar (255) NULL,
	active bool NULL,
	priority varchar(255) NULL,
    status bool NULL,
    type varchar(255) NULL
);
