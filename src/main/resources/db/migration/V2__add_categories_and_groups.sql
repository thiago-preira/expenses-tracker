DROP TABLE IF EXISTS groups;

CREATE TABLE groups(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS categories;

CREATE TABLE categories(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    group_id INTEGER REFERENCES groups (id)
);

ALTER TABLE transactions
ADD column category_id INTEGER;

ALTER TABLE transactions
ADD CONSTRAINT fk_transactions_category
foreign key (category_id)
REFERENCES categories (id);