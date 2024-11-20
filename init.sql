-- Drop tables if they already exist
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS publisher;

-- Create tables
CREATE TABLE author (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        birth_date DATE,
                        nationality VARCHAR(100)
);

CREATE TABLE publisher (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(255) NOT NULL,
                           country VARCHAR(100)
);

CREATE TABLE book (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(255) NOT NULL,
                      genre VARCHAR(100),
                      publish_date DATE,
                      page_count INT,
                      author_id BIGINT,
                      publisher_id BIGINT,
                      CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES author(id),
                      CONSTRAINT fk_publisher FOREIGN KEY (publisher_id) REFERENCES publisher(id)
);

-- Insert authors
INSERT INTO author (name, birth_date, nationality) VALUES
                                                       ('J.K. Rowling', '1965-07-31', 'British'),
                                                       ('George R.R. Martin', '1948-09-20', 'American'),
                                                       ('Agatha Christie', '1890-09-15', 'British'),
                                                       ('Dan Brown', '1964-06-22', 'American'),
                                                       ('Isaac Asimov', '1920-01-02', 'Russian-American');

-- Insert publishers
INSERT INTO publisher (name, country) VALUES
                                          ('Bloomsbury', 'United Kingdom'),
                                          ('Bantam Books', 'United States'),
                                          ('HarperCollins', 'United States'),
                                          ('Penguin Random House', 'United States'),
                                          ('Gollancz', 'United Kingdom');

-- Insert books
INSERT INTO book (title, genre, publish_date, page_count, author_id, publisher_id) VALUES
                                                                                       ('Harry Potter and the Sorcerer''s Stone', 'Fantasy', '1997-06-26', 309, 1, 1),
                                                                                       ('Harry Potter and the Chamber of Secrets', 'Fantasy', '1998-07-02', 341, 1, 1),
                                                                                       ('A Game of Thrones', 'Fantasy', '1996-08-06', 694, 2, 2),
                                                                                       ('A Clash of Kings', 'Fantasy', '1998-11-16', 768, 2, 2),
                                                                                       ('Murder on the Orient Express', 'Mystery', '1934-01-01', 256, 3, 3),
                                                                                       ('And Then There Were None', 'Mystery', '1939-11-06', 272, 3, 3),
                                                                                       ('The Da Vinci Code', 'Thriller', '2003-03-18', 489, 4, 4),
                                                                                       ('Angels & Demons', 'Thriller', '2000-05-03', 616, 4, 4),
                                                                                       ('Foundation', 'Science Fiction', '1951-06-01', 255, 5, 5),
                                                                                       ('I, Robot', 'Science Fiction', '1950-12-02', 253, 5, 5);
