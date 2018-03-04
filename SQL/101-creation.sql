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
CREATE TABLE "101_user"
(
    user_id NUMBER PRIMARY KEY,
    username VARCHAR2(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR2(100) NOT NULL,
    first_name VARCHAR2(50) NOT NULL,
    last_name VARCHAR2(50) NOT NULL,
    role_id NUMBER
);

CREATE TABLE "101_role"
(
    role_id NUMBER PRIMARY KEY,
    role VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE "101_post"
(
    post_id NUMBER PRIMARY KEY,
    title VARCHAR2(50) NOT NULL,
    user_id NUMBER,
    body VARCHAR2(2000),
--    content BLOB,
    likes NUMBER,
    status_id NUMBER
--    first_comment NUMBER
);

CREATE TABLE "101_subject"
(
    subject_id NUMBER PRIMARY KEY,
    subject_name VARCHAR2(50)
);

CREATE TABLE "101_status"
(
    status_id NUMBER PRIMARY KEY,
    status_name VARCHAR2(50)
);

CREATE TABLE "101_quiz"
(
    quiz_id NUMBER PRIMARY KEY,
    subject_id NUMBER,
--    avg_score/diffculty NUMBER
    likes NUMBER,
    title VARCHAR2(30) NOT NULL,
    author_id NUMBER
);

CREATE TABLE "101_question"
(
    question_id NuMBER PRIMARY KEY,
    quiz_id NUMBER,
    correct_answer VARCHAR(200) NOT NULL,
    wrong_answer_0 VARCHAR2(200) NOT NULL,
    wrong_answer_1 VARCHAR2(200) NOT NULL,
    wrong_answer_2 VARCHAR2(200) NOT NULL
);

CREATE TABLE "101_fc_sect"
(
    
);

CREATE TABLE "101_flashcard"
(
    
);

--CREATE TABLE "101_comment"
--(
--    
--);

/*******************************************************************************
   Create Junction Tables
********************************************************************************/
CREATE TABLE "101_post_to_subject"
(
    
);

CREATE TABLE "101_fc_to_set"
(
    
);


/*******************************************************************************
   Create Primary Key Unique Indexes
********************************************************************************/


/*******************************************************************************
   Create Foreign Keys
********************************************************************************/
/**
* 101_user
**/
ALTER TABLE "101_user" ADD CONSTRAINT "101_role_id_fk_auth"
    FOREIGN KEY (role_id) REFERENCES "101_role" (role_id) ON DELETE CASCADE;
    
/**
* 101_post
**/
ALTER TABLE "101_psot" ADD CONSTRAINT "101_user_id_fk_auth"
    FOREIGN KEY (user_id) REFERENCES "101_user" (user_id) ON DELETE CASCADE;
    
/**
* 101_quiz
**/
ALTER TABLE "101_quiz" ADD CONSTRAINT "101_author_id_fk_auth"
    FOREIGN KEY (author_id) REFERENCES "101_user" (user_id) ON DELETE CASCADE;


/*******************************************************************************
   Create Sequences
********************************************************************************/
CREATE SEQUENCE "101_user_id_seq";

CREATE SEQUENCE "101_role_id_seq";

CREATE SEQUENCE "101_post_id_seq";

CREATE SEQUENCE "101_subject_id_seq";

CREATE SEQUENCE "101_status_id_seq";

CREATE SEQUENCE "101_quiz_id_seq";

CREATE SEQUENCE "101_question_id_seq";

CREATE SEQUENCE "101_fc_set_id_seq";

CREATE SEQUENCE "101_flashcard_id_seq";

--CREATE SEQUENCE "101_comment_id_seq";

/*******************************************************************************
   Create Triggers
********************************************************************************/
CREATE OR REPLACE TRIGGER user_id_trig
    BEFORE INSERT OR UPDATE ON "101_user"
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
    BEFORE INSERT OR UPDATE ON "101_role"
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
    BEFORE INSERT OR UPDATE ON "101_post"
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT post_id_seq.nextVal INTO :new.post_id FROM DUAL;
            SELECT 0 INTO :new.like FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.post_id INTO :new.post_id FROM DUAL;
        END IF;
    END;
    /

CREATE OR REPLACE TRIGGER subject_id_trig
    BEFORE INSERT OR UPDATE ON "101_subject"
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
    BEFORE INSERT OR UPDATE ON "101_status"
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
    BEFORE INSERT OR UPDATE ON "101_quiz"
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT quiz_id_seq.nextVal INTO :new.quiz_id FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.quiz_id INTO :new.quiz_id FROM DUAL;
        END IF;
    END;
    /
    
CREATE OR REPLACE TRIGGER question_id_trig
    BEFORE INSERT OR UPDATE ON "101_question"
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
    BEFORE INSERT OR UPDATE ON "101_user"
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT fc_set_seq.nextVal INTO :new.fc_set_id FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.fc_set_id INTO :new.fc_set_id FROM DUAL;
        END IF;
    END;
    /
    
CREATE OR REPLACE TRIGGER flashcard_id_trig
    BEFORE INSERT OR UPDATE ON "101_user"
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT flashcard_id_seq.nextVal INTO :new.flashcard_id FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.flashcard_id INTO :new.flashcard_id FROM DUAL;
        END IF;
    END;
    /
    
CREATE OR REPLACE TRIGGER comment_id_trig
    BEFORE INSERT OR UPDATE ON "101_comment"
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT comment_id_seq.nextVal INTO :new.comment_id FROM DUAL;
        ELSIF UPDATING THEN
            SELECT :old.comment_id INTO :new.comment_id FROM DUAL;
        END IF;
    END;
    /

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
--INSERT INTO ers_user_roles (user_role) VALUES ('Employee');

/*******************************************************************************
   Commit changes and exit
********************************************************************************/
COMMIT;
EXIT;



















