

CREATE TABLE IF NOT EXISTS address (
	id UUID PRIMARY KEY,
	nation varchar(255) NULL,
	region varchar(255) NOT NULL,
	province varchar(255) NOT NULL,
	city varchar(255) NOT NULL,
	street varchar(255) NOT NULL,
	number varchar(255) NOT NULL,
	zip_code varchar(255) NOT NULL,
	formatted_address varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS learner (
	id UUID PRIMARY KEY,
	username varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	surname varchar(255) NOT NULL,
	fiscal_code varchar(16) NOT NULL,
	date_of_birth timestamptz NOT NULL,
	birth_place varchar(255) NOT NULL,
	address_id UUID NOT NULL,
	phone varchar(255) NULL,
	email varchar(255) NOT NULL,
	degree_of_studies varchar(255) NOT NULL,
	course_of_study varchar(255) NULL,
	curriculum_vitae varchar(255) NOT NULL,
	note varchar(255) NULL,

	FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE TABLE IF NOT EXISTS teacher (
	id UUID PRIMARY KEY,
	username varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	surname varchar(255) NOT NULL,
	fiscal_code varchar(16) NOT NULL,
	date_of_birth timestamptz NOT NULL,
	birth_place varchar(255) NOT NULL,
	address_id UUID NOT NULL,
	phone varchar(255) NULL,
	email varchar(255) NOT NULL,
	professional_area varchar(255) NOT NULL,
	public_employee bool NOT NULL,
	accredited_ft bool NOT NULL,
	accredited_ft_code varchar(255) NOT NULL,
	authorized bool NOT NULL,
	professional_order_registration bool NOT NULL,
	register varchar(255) NOT NULL,
	vat_holder bool NOT NULL,
	vat_number varchar(255) NULL,
	curriculum_vitae varchar(255) NOT NULL,
	sector varchar(255) NOT NULL,
	note varchar(255) NULL,

	FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE TABLE IF NOT EXISTS branch (
	id UUID PRIMARY KEY,
	username varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	super bool NOT NULL,
	address_id UUID NOT NULL,
	right_of_access_to_the_courses varchar(255),

	FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE TABLE IF NOT EXISTS course (

	id UUID PRIMARY KEY,
	course_code varchar(255) NOT NULL,
	course_title varchar(255) NOT NULL,
	course_type varchar(255) NOT NULL,
	contents_area varchar(255) NOT NULL,
	learner_type varchar(255) NOT NULL,
	supply_modality varchar(255) NOT NULL,
	payment_modality varchar(255) NOT NULL,
	cost numeric NOT NULL,
	founds_type varchar(255) NOT NULL,
	educational_target_description varchar(255) NOT NULL,
	course_description varchar(255) NOT NULL,
	course_start_date DATE NOT NULL,
	course_end_date DATE NOT NULL,
	theory_hours numeric NOT NULL,
	practice_hours numeric NOT NULL,
	coaching_hours numeric NOT NULL,
	visit_hours numeric NOT NULL,
	skils_analysis_hours numeric NOT NULL,
	total_hours numeric NOT NULL,
	total_hours_training numeric NOT NULL,
	daily_hours numeric NOT NULL,
	certificate_type varchar(255) NOT NULL,
	minimum_number_of_participants numeric NOT NULL,
	disabled BOOLEAN NOT NULL,
	course_logo varchar(255) NOT NULL,
	part_full_time varchar(255) NOT NULL,
	morning_start_hour TIME NOT NULL,
	morning_end_hour TIME NOT NULL,
	afternoon_statr_hour TIME NOT NULL,
	afternoon_end_hour TIME NOT NULL,
	note varchar(500) NOT NULL,

	id_indirizzo UUID NOT NULL,
	id_docente UUID NOT NULL,

	FOREIGN KEY (id_indirizzo) REFERENCES address (id),
	FOREIGN KEY (id_docente) REFERENCES teacher (id)
);

CREATE TABLE IF NOT EXISTS discenti_corso(

	id_corso UUID NOT NULL,
	id_discente UUID NOT NULL,
	accettato BOOLEAN NOT NULL,
	rifiutato BOOLEAN NOT NULL,
	ritirato BOOLEAN NOT NULL,
	ritirato_con_motivo varchar(255) NULL,
	data_ritiro DATE NULL,
	modulo_ritiro varchar(255) NULL,

	FOREIGN KEY (id_corso) REFERENCES corso (id),
	FOREIGN KEY (id_discente) REFERENCES learner (id)

);


CREATE TABLE IF NOT EXISTS partner (

	id UUID NOT NULL,
	business_name varchar(500) NOT NULL,
	vat_number varchar(500) NOT NULL,
	company BOOLEAN  NOT NULL,
	email varchar (255) NULL,
	phone varchar (255) NULL,
	fax varchar (255) NULL,
	manager_name varchar (255) NOT NULL,
	manager_number varchar (255) NOT NULL,
	accredited_ft bool NOT NULL,
	accredited_ft_code varchar(255) NOT NULL,
	cost_element numeric NOT NULL,
	note VARCHAR (500) NULL

);

CREATE TABLE IF NOT EXISTS teacher_partner(

	partner_id UUID NOT NULL,
	teacher_id UUID NOT NULL,
	"status" varchar (255) NOT NULL
);

CREATE TABLE IF NOT EXISTS address_partner(

	partner_id UUID NOT NULL,
	address_id UUID NOT NULL,
	address_type varchar (255) NOT NULL
);


