-- Insertar cliente de prueba
INSERT INTO "persons" (
    "id",
    "name",
    "gender",
    "age",
    "address",
    "phone",
    "is_active",
    "is_deleted",
    "created_at",
    "updated_at",
    "deleted_at"
) VALUES (
    '01K50WS6MVTVHFQ1X9TXBMWV9Y',
    'Jose Lema',
    'FEMALE',
    35,
    'Otavalo sn and main',
    '+5552686590',
    true,
    false,
    NOW(),
    NULL,
    NULL
);

-- Insertar cliente de prueba
INSERT INTO "customers" (
    "id",
    "client_id",
    "encrypted_password"
) VALUES (
    '01K50WS6MVTVHFQ1X9TXBMWV9Y',
    'XAXX010101000',
    '$2a$10$wwRQ2wix7dcMp0UGBO08POm2kZanCog4.gp9WxtLawVD6pcGe6qY2'
)
ON CONFLICT (client_id) DO NOTHING;