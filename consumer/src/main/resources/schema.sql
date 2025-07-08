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

---

-- Run this at company creation time (replace tenant1 with companyId)
CREATE SCHEMA IF NOT EXISTS tenant1;

CREATE TABLE IF NOT EXISTS tenant1.transaction (
    id UUID PRIMARY KEY,
    product VARCHAR(255),
    quantity INTEGER,
    received_at TIMESTAMP,
    company_id VARCHAR(255),
    user_id VARCHAR(255)
);
