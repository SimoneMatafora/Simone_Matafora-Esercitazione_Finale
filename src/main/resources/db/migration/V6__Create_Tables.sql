CREATE TABLE IF NOT EXISTS alert_settings (
	id UUID PRIMARY KEY,
	type varchar(255) UNIQUE NULL,
	alert_description varchar(255) NULL,
	periodicy numeric NULL
);

CREATE TABLE IF NOT EXISTS alert_course (
	id UUID PRIMARY KEY,
	id_alert UUID NULL,
	id_course UUID NULL,
	course_name varchar(255) NULL,
	course_code varchar(255) NULL,
	date_start_alert timestamptz NULL,
	date_end_alert timestamptz NULL,
	active BOOLEAN NULL,
	status BOOLEAN NULL,
	priority varchar(255) NULL,
	alert_description varchar(255) NULL
);

INSERT INTO public.alert_settings(id, type, alert_description, periodicy) VALUES ('10e4409a-0947-48c0-89aa-cedb7e5971fb', 'INIZIO_CORSO', 'Inizio corso.', 0);
INSERT INTO public.alert_settings(id, type, alert_description, periodicy) VALUES ('20e4409a-0947-48c0-89aa-cedb7e5971fb', 'INVIO_INAIL', 'Invio inail.', 0);
INSERT INTO public.alert_settings(id, type, alert_description, periodicy) VALUES ('30e4409a-0947-48c0-89aa-cedb7e5971fb', 'INSERIMENTO_CODICE_FISCALE', 'Inserimento c.f. allievi.', 4);
INSERT INTO public.alert_settings(id, type, alert_description, periodicy) VALUES ('40e4409a-0947-48c0-89aa-cedb7e5971fb', 'DATA_CONSEGNA_PROGETTO', 'Inserimento data consegna progetto.', 50);
INSERT INTO public.alert_settings(id, type, alert_description, periodicy) VALUES ('50e4409a-0947-48c0-89aa-cedb7e5971fb', 'POSSIBILITA_RENDICONTAZIONE', 'Possibilità di rendiconto.', 15);
INSERT INTO public.alert_settings(id, type, alert_description, periodicy) VALUES ('60e4409a-0947-48c0-89aa-cedb7e5971fb', 'RENDICONTAZIONE', 'Rendicontazione.', 40);
INSERT INTO public.alert_settings(id, type, alert_description, periodicy) VALUES ('70e4409a-0947-48c0-89aa-cedb7e5971fb', 'RENDICONTAZIONE_ELETTRONICA', 'Invio rendicontazione elettronica.', 50);
INSERT INTO public.alert_settings(id, type, alert_description, periodicy) VALUES ('80e4409a-0947-48c0-89aa-cedb7e5971fb', 'RENDICONTAZIONE_CARTACEA', 'Invio rendiconto cartaceo.', 2);
INSERT INTO public.alert_settings(id, type, alert_description, periodicy) VALUES ('90e4409a-0947-48c0-89aa-cedb7e5971fb', 'SCADENZA_PLACEMENT', 'Placement in scadenza.', 90);
INSERT INTO public.alert_settings(id, type, alert_description, periodicy) VALUES ('11e4409a-0947-48c0-89aa-cedb7e5971fb', 'INVIO_PLACEMENT', 'Invio Placement.', 220);
INSERT INTO public.alert_settings(id, type, alert_description, periodicy) VALUES ('12e4409a-0947-48c0-89aa-cedb7e5971fb', 'INVIO_PLACEMENT_ELETTRONICO', 'Invio placement elettronico', 50);
INSERT INTO public.alert_settings(id, type, alert_description, periodicy) VALUES ('13e4409a-0947-48c0-89aa-cedb7e5971fb', 'INVIO_PLACEMENT_CARTACEO', 'Invio placement cartaceo', 2);

