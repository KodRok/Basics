
-- Создание таблицы tutors
CREATE TABLE tutors (
    tutor_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(255)
);

-- Создание таблицы lessons
CREATE TABLE lessons (
    lesson_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL
);

-- Создание таблицы students
CREATE TABLE students(
    student_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(255),
    tutor_id INT REFERENCES tutors(tutor_id) ON DELETE SET NULL
);

-- Создание таблицы lessons_list
CREATE TABLE lessons_list (
    student_id INT REFERENCES students(student_id) ON DELETE CASCADE,
    lesson_id INT REFERENCES lessons(lesson_id) ON DELETE CASCADE,
    PRIMARY KEY (student_id, lesson_id)
);