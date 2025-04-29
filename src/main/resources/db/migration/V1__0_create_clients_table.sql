CREATE TABLE clients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    shared_key VARCHAR(255) NOT NULL UNIQUE,
    business_id VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255),
    start_date DATE,
    end_date DATE
);