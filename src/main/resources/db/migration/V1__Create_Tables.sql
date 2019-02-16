

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

CREATE TABLE IF NOT EXISTS corso (
	id UUID PRIMARY KEY,
	codice_corso varchar(255) NOT NULL,
	titolo_corso varchar(255) NOT NULL,
	tipologia_corso varchar(255) NOT NULL,
	area_tematica varchar(255) NOT NULL,
	tipologia_discenti varchar(255) NOT NULL,
	modalita_di_erogazione varchar(255) NOT NULL,
	modalita_di_pagamento varchar(255) NOT NULL,
	costo numeric NOT NULL,
	tipologia_fondi varchar(255) NOT NULL,
	descrizione_obiettivi_formativi varchar(255) NOT NULL,
	descrizoine_del_corso varchar(255) NOT NULL,
	data_inizio_corso DATE NOT NULL,
	data_fine_corso DATE NOT NULL,
	ore_teoria numeric NOT NULL,
	ore_pratica numeric NOT NULL,
	ore_di_affiancamento numeric NOT NULL,
	ore_di_visita numeric NOT NULL,
	ore_bilancio_competenze numeric NOT NULL,
	monte_ore_totali numeric NOT NULL,
	ore_totali_di_formazione numeric NOT NULL,
	ore_giornaliere numeric NOT NULL,
	tipologia_attestato varchar(255) NOT NULL,
	numero_minimo_di_partecipanti numeric NOT NULL,
	disabili BOOLEAN NOT NULL,
	logo_del_corso varchar(255) NOT NULL,
	part_full_time varchar(255) NOT NULL,
	ora_inizio_mattina TIME NOT NULL,
	ora_fine_mattina TIME NOT NULL,
	ora_inizio_pomeriggio TIME NOT NULL,
	ora_fine_pomeriggio TIME NOT NULL,
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


