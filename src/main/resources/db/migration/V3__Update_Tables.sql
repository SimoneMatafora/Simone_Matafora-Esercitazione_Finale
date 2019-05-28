ALTER TABLE teacher RENAME COLUMN address TO residential_address;
ALTER TABLE teacher ADD COLUMN IF NOT EXISTS domicile_address jsonb;
ALTER TABLE teacher ADD COLUMN IF NOT EXISTS domicile_equals_residential bool;

ALTER TABLE learner RENAME COLUMN address TO residential_address;
ALTER TABLE learner ADD COLUMN IF NOT EXISTS domicile_address jsonb;
ALTER TABLE learner ADD COLUMN IF NOT EXISTS domicile_equals_residential bool;