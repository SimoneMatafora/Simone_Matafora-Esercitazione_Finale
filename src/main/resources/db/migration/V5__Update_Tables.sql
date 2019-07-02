ALTER TABLE course ADD COLUMN IF NOT EXISTS created_at timestamp;
ALTER TABLE course ADD COLUMN IF NOT EXISTS update_at timestamp;