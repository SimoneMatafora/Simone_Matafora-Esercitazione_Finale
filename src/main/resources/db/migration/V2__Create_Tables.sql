ALTER TABLE course ADD COLUMN IF NOT EXISTS status varchar (255);
ALTER TABLE learner RENAME COLUMN curriculum_vitae TO attachments;