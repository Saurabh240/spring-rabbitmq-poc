CREATE TABLE IF NOT EXISTS transactions (
    id UUID PRIMARY KEY,
    company_id UUID NOT NULL,
    user_id UUID NOT NULL,
    header TEXT,
    company_name TEXT,
    staff_name TEXT,
    device_id TEXT,
    raw_sms TEXT,
    timestamp TIMESTAMP,
    received_at TIMESTAMP DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_transactions_company_id ON transactions(company_id);
