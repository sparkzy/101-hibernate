/*******************************************************************************
   101 Database - Version 1.0
   Script: 101-creation.sql
   Description: Creates and populates the 101 database.
   DB Server: Oracle
   Author: Bobby McGetrick
********************************************************************************/

/*******************************************************************************
   Drop database if it exists
***x*****************************************************************************/
DROP USER "101" CASCADE;


/*******************************************************************************
   Create database
********************************************************************************/
CREATE USER "101"
IDENTIFIED BY p4ssw0rd
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to "101";
GRANT resource to "101";
GRANT create session TO "101";
GRANT create table TO "101";
GRANT create view TO "101";
GRANT create job TO "101";



conn "101"/p4ssw0rd


/*******************************************************************************
   Create Tables
********************************************************************************/
CREATE TABLE users
(
    user_id NUMBER PRIMARY KEY,
    username VARCHAR2(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR2(100) NOT NULL,
    first_name VARCHAR2(50) NOT NULL,
    last_name VARCHAR2(50) NOT NULL,
    role_id NUMBER
);

CREATE TABLE role
(
    role_id NUMBER PRIMARY KEY,
    role VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE post
(
    post_id NUMBER PRIMARY KEY,
    title VARCHAR2(50) NOT NULL,
    author_id NUMBER,
    body VARCHAR2(2000),
--    content BLOB,
    likes NUMBER,
    status_id NUMBER
--    first_comment NUMBER
);

CREATE TABLE subject
(
    subject_id NUMBER PRIMARY KEY,
    subject_name VARCHAR2(50)
);

CREATE TABLE status
(
    status_id NUMBER PRIMARY KEY,
    status_name VARCHAR2(50)
);

CREATE TABLE quiz
(
    quiz_id NUMBER PRIMARY KEY,
    subject_id NUMBER,
--    avg_score/diffculty NUMBER
    likes NUMBER,
    title VARCHAR2(30) NOT NULL,
    author_id NUMBER
);

CREATE TABLE question
(
    question_id NuMBER PRIMARY KEY,
    quiz_id NUMBER,
    correct_answer VARCHAR(200) NOT NULL,
    wrong_answer_0 VARCHAR2(200) NOT NULL,
    wrong_answer_1 VARCHAR2(200) NOT NULL,
    wrong_answer_2 VARCHAR2(200) NOT NULL
);

CREATE TABLE fc_set
(
    fc_set_id NUMBER PRIMARY KEY,
    title VARCHAR2(30) NOT NULL,
    subject_id NUMBER,
    author_id NUMBER,
    likes NUMBER
);

CREATE TABLE flashcard
(
    flashcard_id NUMBER PRIMARY KEY,
    fc_set_id NUMBER,
    question VARCHAR2(200) NOT NULL,
    answer VARCHAR2(200) NOT NULL,
    author_id NUMBER
);

--CREATE TABLE comment
--(
--    
--);

/*******************************************************************************
   Create Junction Tables
********************************************************************************/
CREATE TABLE post_to_subject
(
    post_id NUMBER,
    subject_id NUMBER,
    CONSTRAINT pk_composite_p2s_key PRIMARY KEY (post_id, subject_id)
);

CREATE TABLE fc_to_set
(
    flashcard_id NUMBER,
    fc_set_id NUMBER,
    CONSTRAINT pk_composite_fc2s_key PRIMARY KEY (flashcard_id, fc_set_id)
);


/*******************************************************************************
   Create Primary Key Unique Indexes
********************************************************************************/


/*******************************************************************************
   Create Foreign Keys
********************************************************************************/
/**
* users
**/
ALTER TABLE users ADD CONSTRAINT role_id_fk_auth
    FOREIGN KEY (role_id) REFERENCES role (role_id) ON DELETE CASCADE;
    
/**
* post
**/
ALTER TABLE post ADD CONSTRAINT post_author_id_fk_auth
    FOREIGN KEY (author_id) REFERENCES users (user_id) ON DELETE CASCADE;
    
/**
* quiz
**/
ALTER TABLE quiz ADD CONSTRAINT quiz_author_id_fk_auth
    FOREIGN KEY (author_id) REFERENCES users (user_id) ON DELETE CASCADE;
    
/**
* question
**/
ALTER TABLE question ADD CONSTRAINT quiz_id_fk_auth
    FOREIGN KEY (quiz_id) REFERENCES quiz (quiz_id) ON DELETE CASCADE;
    
/**
* fc_set
**/
ALTER TABLE fc_set ADD CONSTRAINT subject_id_fk_auth
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id) ON DELETE CASCADE;
    
ALTER TABLE fc_set ADD CONSTRAINT set_author_id_fk_auth
    FOREIGN KEY (author_id) REFERENCES users (user_id) ON DELETE CASCADE;

/**
* flashcard
**/
ALTER TABLE flashcard ADD CONSTRAINT fc_set_id_fk_auth
    FOREIGN KEY (fc_set_id) REFERENCES subject (subject_id) ON DELETE CASCADE;

ALTER TABLE flashcard ADD CONSTRAINT fc_author_id_fk_auth
    FOREIGN KEY (author_id) REFERENCES users (user_id) ON DELETE CASCADE;

/*******************************************************************************
   Create Sequences
********************************************************************************/
CREATE SEQUENCE user_id_seq;

CREATE SEQUENCE role_id_seq;

CREATE SEQUENCE post_id_seq;

CREATE SEQUENCE subject_id_seq;

CREATE SEQUENCE status_id_seq;

CREATE SEQUENCE quiz_id_seq;

CREATE SEQUENCE question_id_seq;

CREATE SEQUENCE fc_set_id_seq;

CREATE SEQUENCE flashcard_id_seq;

--CREATE SEQUENCE comment_id_seq;

/*******************************************************************************
   Create Triggers
********************************************************************************/
CREATE OR REPLACE TRIGGER user_id_trig
    BEFORE INSERT OR UPDATE ON users
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT user_id_seq.nextVal INTO :new.user_id FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.user_id INTO :new.user_id FROM DUAL;
        END IF;
    END;
    /
    
CREATE OR REPLACE TRIGGER role_id_trig
    BEFORE INSERT OR UPDATE ON role
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT role_id_seq.nextVal INTO :new.role_id FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.role_id INTO :new.role_id FROM DUAL;
        END IF;
    END;
    /

CREATE OR REPLACE TRIGGER post_id_trig
    BEFORE INSERT OR UPDATE ON post
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT post_id_seq.nextVal INTO :new.post_id FROM DUAL;
            SELECT 0 INTO :new.likes FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.post_id INTO :new.post_id FROM DUAL;
        END IF;
    END;
    /

CREATE OR REPLACE TRIGGER subject_id_trig
    BEFORE INSERT OR UPDATE ON subject
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT subject_id_seq.nextVal INTO :new.subject_id FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.subject_id INTO :new.subject_id FROM DUAL;
        END IF;
    END;
    /
    
CREATE OR REPLACE TRIGGER status_id_trig
    BEFORE INSERT OR UPDATE ON status
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT status_id_seq.nextVal INTO :new.status_id FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.status_id INTO :new.status_id FROM DUAL;
        END IF;
    END;
    /
    
CREATE OR REPLACE TRIGGER quiz_id_trig
    BEFORE INSERT OR UPDATE ON quiz
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT quiz_id_seq.nextVal INTO :new.quiz_id FROM DUAL;
            SELECT 0 INTO :new.likes FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.quiz_id INTO :new.quiz_id FROM DUAL;
        END IF;
    END;
    /
    
CREATE OR REPLACE TRIGGER question_id_trig
    BEFORE INSERT OR UPDATE ON question
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT question_id_seq.nextVal INTO :new.question_id FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.question_id INTO :new.question_id FROM DUAL;
        END IF;
    END;
    /
    
CREATE OR REPLACE TRIGGER fc_set_id_trig
    BEFORE INSERT OR UPDATE ON fc_set
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT fc_set_id_seq.nextVal INTO :new.fc_set_id FROM DUAL;
            SELECT 0 INTO :new.likes FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.fc_set_id INTO :new.fc_set_id FROM DUAL;
        END IF;
    END;
    /
    
CREATE OR REPLACE TRIGGER flashcard_id_trig
    BEFORE INSERT OR UPDATE ON flashcard
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT flashcard_id_seq.nextVal INTO :new.flashcard_id FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.flashcard_id INTO :new.flashcard_id FROM DUAL;
        END IF;
    END;
    /
    
--CREATE OR REPLACE TRIGGER comment_id_trig
--    BEFORE INSERT OR UPDATE ON comment
--    FOR EACH ROW
--    BEGIN
--        IF INSERTING THEN
--            SELECT comment_id_seq.nextVal INTO :new.comment_id FROM DUAL;
--        ELSIF UPDATING THEN
--            SELECT :old.comment_id INTO :new.comment_id FROM DUAL;
--        END IF;
--    END;
--    /

/*******************************************************************************
   Create Stored Procedures
********************************************************************************/


/*******************************************************************************
   Create Functions
********************************************************************************/

/*******************************************************************************
   Create Jobs
********************************************************************************/

/*******************************************************************************
   Initialize Tables
********************************************************************************/

/*******************************************************************************
   Commit changes and exit
*******************************************************************************/
COMMIT;
EXIT;