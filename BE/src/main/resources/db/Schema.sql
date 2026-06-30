IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'EnglishLearning')
BEGIN
    CREATE DATABASE EnglishLearning;
END
GO

USE EnglishLearning;
GO

-- ==========================
-- USERS
-- ==========================
CREATE TABLE users (
                       id INT IDENTITY(1,1) PRIMARY KEY,
                       full_name NVARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       avatar VARCHAR(255),
                       role VARCHAR(20) NOT NULL
                           CHECK (role IN ('ADMIN','USER')),
                       status BIT NOT NULL DEFAULT 1,
                       created_at DATETIME DEFAULT GETDATE(),
                       updated_at DATETIME DEFAULT GETDATE()
);

-- ==========================
-- COURSES
-- ==========================
CREATE TABLE courses (
                         id INT IDENTITY(1,1) PRIMARY KEY,
                         title NVARCHAR(200) NOT NULL,
                         description NVARCHAR(MAX),
                         image VARCHAR(255),
                         level VARCHAR(20) NOT NULL
                             CHECK (level IN ('BEGINNER','ELEMENTARY','INTERMEDIATE')),
                         status BIT DEFAULT 1,
                         created_at DATETIME DEFAULT GETDATE()
);

-- ==========================
-- LESSONS
-- ==========================
CREATE TABLE lessons (
                         id INT IDENTITY(1,1) PRIMARY KEY,
                         course_id INT NOT NULL,
                         title NVARCHAR(200) NOT NULL,
                         description NVARCHAR(MAX),
                         video_url VARCHAR(255),
                         lesson_order INT,
                         duration INT,
                         status BIT DEFAULT 1,

                         CONSTRAINT FK_Lesson_Course
                             FOREIGN KEY(course_id)
                                 REFERENCES courses(id)
                                 ON DELETE CASCADE
);

-- ==========================
-- VOCABULARIES
-- ==========================
CREATE TABLE vocabularies (
                              id INT IDENTITY(1,1) PRIMARY KEY,
                              lesson_id INT NOT NULL,
                              english_word VARCHAR(100) NOT NULL,
                              vietnamese_meaning NVARCHAR(200) NOT NULL,
                              pronunciation VARCHAR(100),
                              example_sentence NVARCHAR(MAX),
                              image VARCHAR(255),
                              audio VARCHAR(255),

                              CONSTRAINT FK_Vocabulary_Lesson
                                  FOREIGN KEY(lesson_id)
                                      REFERENCES lessons(id)
                                      ON DELETE CASCADE
);

-- ==========================
-- QUIZZES
-- ==========================
CREATE TABLE quizzes (
                         id INT IDENTITY(1,1) PRIMARY KEY,
                         lesson_id INT NOT NULL,
                         question NVARCHAR(MAX) NOT NULL,
                         quiz_type VARCHAR(30)
                             CHECK (quiz_type IN ('MULTIPLE_CHOICE','LISTENING','IMAGE')),
                         explanation NVARCHAR(MAX),

                         CONSTRAINT FK_Quiz_Lesson
                             FOREIGN KEY(lesson_id)
                                 REFERENCES lessons(id)
                                 ON DELETE CASCADE
);

-- ==========================
-- QUIZ ANSWERS
-- ==========================
CREATE TABLE quiz_answers (
                              id INT IDENTITY(1,1) PRIMARY KEY,
                              quiz_id INT NOT NULL,
                              answer_content NVARCHAR(255) NOT NULL,
                              is_correct BIT DEFAULT 0,

                              CONSTRAINT FK_Answer_Quiz
                                  FOREIGN KEY(quiz_id)
                                      REFERENCES quizzes(id)
                                      ON DELETE CASCADE
);

-- ==========================
-- USER PROGRESS
-- ==========================
CREATE TABLE user_progress (
                               id INT IDENTITY(1,1) PRIMARY KEY,
                               user_id INT NOT NULL,
                               lesson_id INT NOT NULL,
                               score DECIMAL(5,2) DEFAULT 0,
                               completed BIT DEFAULT 0,
                               completed_at DATETIME,

                               CONSTRAINT FK_Progress_User
                                   FOREIGN KEY(user_id)
                                       REFERENCES users(id)
                                       ON DELETE CASCADE,

                               CONSTRAINT FK_Progress_Lesson
                                   FOREIGN KEY(lesson_id)
                                       REFERENCES lessons(id)
                                       ON DELETE CASCADE
);

-- ==========================
-- FAVORITE VOCABULARIES
-- ==========================
CREATE TABLE favorite_vocabularies (
                                       id INT IDENTITY(1,1) PRIMARY KEY,
                                       user_id INT NOT NULL,
                                       vocabulary_id INT NOT NULL,

                                       CONSTRAINT FK_Favorite_User
                                           FOREIGN KEY(user_id)
                                               REFERENCES users(id)
                                               ON DELETE CASCADE,

                                       CONSTRAINT FK_Favorite_Vocabulary
                                           FOREIGN KEY(vocabulary_id)
                                               REFERENCES vocabularies(id)
                                               ON DELETE CASCADE,

                                       CONSTRAINT UQ_Favorite UNIQUE(user_id, vocabulary_id)
);

-- ==========================
-- LEARNING HISTORY
-- ==========================
CREATE TABLE learning_history (
                                  id INT IDENTITY(1,1) PRIMARY KEY,
                                  user_id INT NOT NULL,
                                  lesson_id INT NOT NULL,

                                  start_time DATETIME,
                                  end_time DATETIME,

                                  learning_time INT,

                                  CONSTRAINT FK_History_User
                                      FOREIGN KEY(user_id)
                                          REFERENCES users(id)
                                          ON DELETE CASCADE,

                                  CONSTRAINT FK_History_Lesson
                                      FOREIGN KEY(lesson_id)
                                          REFERENCES lessons(id)
                                          ON DELETE CASCADE
);

-- ==========================
-- CERTIFICATES
-- ==========================
CREATE TABLE certificates (
                              id INT IDENTITY(1,1) PRIMARY KEY,
                              user_id INT NOT NULL,
                              course_id INT NOT NULL,
                              issue_date DATETIME DEFAULT GETDATE(),
                              certificate_code VARCHAR(100) UNIQUE,
                              CONSTRAINT FK_Certificate_User
                                  FOREIGN KEY(user_id)
                                      REFERENCES users(id)
                                      ON DELETE CASCADE,
                              CONSTRAINT FK_Certificate_Course
                                  FOREIGN KEY(course_id)
                                      REFERENCES courses(id)
                                      ON DELETE CASCADE
);