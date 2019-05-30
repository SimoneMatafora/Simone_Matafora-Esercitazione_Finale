ALTER TABLE course ADD COLUMN IF NOT EXISTS invoice_authorization bool;
ALTER TABLE course ADD COLUMN IF NOT EXISTS accounting_code varchar(255);