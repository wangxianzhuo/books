-- database name : library
-- database : postgresql
CREATE TABLE books (
  id           VARCHAR(36)  NOT NULL,
  isbn         VARCHAR(13)  NOT NULL,
  name         VARCHAR(200) NOT NULL,
  publisher    TEXT      DEFAULT NULL,
  authors      TEXT      DEFAULT NULL,
  created_time TIMESTAMP    NOT NULL,
  updated_time TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE book_facts (
  id           VARCHAR(36) NOT NULL,
  book_id      VARCHAR(36) NOT NULL REFERENCES books (id) ON DELETE CASCADE,
  name         TEXT      DEFAULT NULL,
  value        TEXT      DEFAULT NULL,
  created_time TIMESTAMP   NOT NULL,
  updated_time TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (id)
);

