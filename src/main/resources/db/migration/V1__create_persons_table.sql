CREATE TABLE IF NOT EXISTS "persons" (
    "id" VARCHAR(26) PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    "gender" VARCHAR(50) NOT NULL,
    "age" INTEGER NOT NULL,
    "address" VARCHAR(255) NOT NULL,
    "phone" VARCHAR(100) NOT NULL,
    "is_active" BOOLEAN NOT NULL DEFAULT false,
    "is_deleted" BOOLEAN NOT NULL DEFAULT false,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP,
    "deleted_at" TIMESTAMP
);
