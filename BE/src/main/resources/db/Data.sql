USE EnglishLearning
GO
INSERT INTO users(full_name,email,password,role,status)
VALUES
(N'Administrator','admin@gmail.com','123456','ADMIN',1),
(N'Nguyễn Văn A','user@gmail.com','123456','USER',1),
(N'Trần Thị B','user2@gmail.com','123456','USER',1);
INSERT INTO courses(title,description,image,level,status)
VALUES
    (
        N'English Basic',
        N'Khóa học tiếng Anh cơ bản cho người mới bắt đầu.',
        'https://images.unsplash.com/photo-1503676260728-1c00da094a0b',
        'BEGINNER',
        1
    ),
    (
        N'English Communication',
        N'Giao tiếp tiếng Anh hằng ngày.',
        'https://images.unsplash.com/photo-1513258496099-48168024aec0',
        'ELEMENTARY',
        1
    ),
    (
        N'English Grammar',
        N'Học ngữ pháp tiếng Anh cơ bản.',
        'https://images.unsplash.com/photo-1455390582262-044cdead277a',
        'INTERMEDIATE',
        1
    );
INSERT INTO courses(title,description,image,level,status)
VALUES
    (
        N'English Basic',
        N'Khóa học tiếng Anh cơ bản cho người mới bắt đầu.',
        'https://images.unsplash.com/photo-1503676260728-1c00da094a0b',
        'BEGINNER',
        1
    ),
    (
        N'English Communication',
        N'Giao tiếp tiếng Anh hằng ngày.',
        'https://images.unsplash.com/photo-1513258496099-48168024aec0',
        'ELEMENTARY',
        1
    ),
    (
        N'English Grammar',
        N'Học ngữ pháp tiếng Anh cơ bản.',
        'https://images.unsplash.com/photo-1455390582262-044cdead277a',
        'INTERMEDIATE',
        1
    );
INSERT INTO lessons(course_id,title,description,lesson_order,duration,status)
VALUES

    (1,N'Alphabet',N'Học bảng chữ cái',1,20,1),
    (1,N'Greeting',N'Chào hỏi',2,25,1),
    (1,N'Number',N'Số đếm',3,30,1),

    (2,N'Introduce Yourself',N'Giới thiệu bản thân',1,30,1),
    (2,N'Daily Conversation',N'Giao tiếp hằng ngày',2,35,1),

    (3,N'Nouns',N'Danh từ',1,30,1),
    (3,N'Verbs',N'Động từ',2,35,1);
INSERT INTO vocabularies
(
    lesson_id,
    english_word,
    vietnamese_meaning,
    pronunciation,
    example_sentence
)
VALUES

    (1,'Apple',N'Quả táo','/ˈæpl/','I eat an apple.'),

    (1,'Book',N'Cuốn sách','/bʊk/','This is my book.'),

    (1,'Teacher',N'Giáo viên','/ˈtiːtʃər/','My teacher is kind.'),

    (2,'Hello',N'Xin chào','/həˈləʊ/','Hello everyone.'),

    (2,'Goodbye',N'Tạm biệt','/ɡʊdˈbaɪ/','Goodbye my friend.'),

    (3,'One',N'Một','/wʌn/','One apple.'),

    (3,'Two',N'Hai','/tuː/','Two books.');
INSERT INTO quizzes
(
    lesson_id,
    question,
    quiz_type,
    explanation
)
VALUES

    (
        1,
        N'Apple có nghĩa là gì?',
        'MULTIPLE_CHOICE',
        N'Apple nghĩa là quả táo.'
    ),

    (
        2,
        N'Hello có nghĩa là gì?',
        'MULTIPLE_CHOICE',
        N'Hello nghĩa là xin chào.'
    ),

    (
        3,
        N'One là số mấy?',
        'MULTIPLE_CHOICE',
        N'One nghĩa là số một.'
    );
INSERT INTO quiz_answers
(
    quiz_id,
    answer_content,
    is_correct
)
VALUES

    (1,N'Quả táo',1),
    (1,N'Quyển sách',0),
    (1,N'Con mèo',0),
    (1,N'Con chó',0),

    (2,N'Xin chào',1),
    (2,N'Tạm biệt',0),
    (2,N'Cảm ơn',0),
    (2,N'Xin lỗi',0),

    (3,N'1',1),
    (3,N'2',0),
    (3,N'3',0),
    (3,N'4',0);
INSERT INTO user_progress
(
    user_id,
    lesson_id,
    score,
    completed,
    completed_at
)
VALUES

    (2,1,100,1,GETDATE()),
    (2,2,90,1,GETDATE()),
    (2,3,80,0,NULL),

    (3,1,70,1,GETDATE());
INSERT INTO favorite_vocabularies
(
    user_id,
    vocabulary_id
)
VALUES

    (2,1),
    (2,2),
    (2,4),

    (3,1),
    (3,5);
INSERT INTO favorite_vocabularies
(
    user_id,
    vocabulary_id
)
VALUES

    (2,1),
    (2,2),
    (2,4),

    (3,1),
    (3,5);
INSERT INTO learning_history
(
    user_id,
    lesson_id,
    start_time,
    end_time,
    learning_time
)
VALUES

    (
        2,
        1,
        GETDATE(),
        DATEADD(MINUTE,20,GETDATE()),
        20
    ),

    (
        2,
        2,
        GETDATE(),
        DATEADD(MINUTE,25,GETDATE()),
        25
    );
INSERT INTO certificates
(
    user_id,
    course_id,
    certificate_code
)
VALUES

    (2,1,'CERT001'),
    (3,2,'CERT002');