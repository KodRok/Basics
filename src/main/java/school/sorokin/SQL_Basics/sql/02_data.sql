-- Вставка данных в таблицу tutors
INSERT INTO tutors (name, email, phone) VALUES
('Иван Смирнов', 'ivan.smirnov@email.ru', '+79101234567'),
('Елена Ковалева', 'elena.kovaleva@email.ru', '+79267654321'),
('Андрей Попов', 'andrey.popov@email.ru', '+79039876543'),
('Ольга Федорова', 'olga.fedorova@email.ru', '+79162345678'),
('Дмитрий Морозов', 'dmitry.morozov@email.ru', '+79858765432');

-- Вставка данных в таблицу lessons
INSERT INTO lessons (name, category) VALUES
('Введение в Python', 'Программирование'),
('Алгебра и начала анализа', 'Математика'),
('Основы веб-дизайна', 'Дизайн'),
('Английский для начинающих', 'Языки'),
('Физика. Механика', 'Физика');

-- Вставка данных в таблицу students
INSERT INTO students (name, email, phone, tutor_id) VALUES
('Алексей Иванов', 'alexey.ivanov@mail.ru', '+79051112233', 1),
('Мария Петрова', 'maria.petrova@mail.ru', '+79054445566', 1),
('Сергей Сидоров', 'sergey.sidorov@mail.ru', '+79057778899', 2),
('Анна Кузнецова', 'anna.kuznetsova@mail.ru', '+79050001122', 3),
('Екатерина Волкова', 'ekaterina.volkova@mail.ru', '+79053334455', 4),
('Павел Зайцев', 'pavel.zaitsev@mail.ru', '+79056667788', 4);

-- Вставка данных в таблицу lessons_list
INSERT INTO lessons_list (student_id, lesson_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 1);