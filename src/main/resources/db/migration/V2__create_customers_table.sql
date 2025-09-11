CREATE TABLE IF NOT EXISTS "customers" (
    "id" VARCHAR(26) PRIMARY KEY,
    "client_id" VARCHAR(255) NOT NULL,
    "encrypted_password" VARCHAR(100) NOT NULL,

    CONSTRAINT "fk_customer_person" FOREIGN KEY ("id") REFERENCES "persons"("id") ON DELETE CASCADE,
    CONSTRAINT "uk_customer_client_id" UNIQUE ("client_id")
);
