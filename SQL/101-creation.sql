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
--
--CREATE TABLE ers_reimbursement_status
--(
--    reimb_status_id NUMBER PRIMARY KEY NOT NULL,
--    reimb_status VARCHAR2(10) NOT NULL
--);
--
--CREATE TABLE ers_reimbursement_type
--(
--    reimb_type_id NUMBER PRIMARY KEY NOT NULL,
--    reimb_type VARCHAR2(10) NOT NULL
--);
--
--CREATE TABLE ers_user_roles
--(
--    ers_user_role_id NUMBER PRIMARY KEY NOT NULL,
--    user_role VARCHAR2(30) NOT NULL
--);

/*******************************************************************************
   Create Junction Tables
********************************************************************************/


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
--    
--ALTER TABLE ers_reimbursement ADD CONSTRAINT ers_user_fk_reslvr
--    FOREIGN KEY (reimb_resolver) REFERENCES ers_users (ers_user_id) ON DELETE CASCADE;
--
--ALTER TABLE ers_reimbursement ADD CONSTRAINT ers_reimbursement_status_fk
--    FOREIGN KEY (reimb_status_id) REFERENCES ers_reimbursement_status (reimb_status_id) ON DELETE CASCADE;
--    
--ALTER TABLE ers_reimbursement ADD CONSTRAINT ers_reimbursement_type_fk
--    FOREIGN KEY (reimb_type_id) REFERENCES ers_reimbursement_type (reimb_type_id) ON DELETE CASCADE;
    
/**
* ers_users
**/
--ALTER TABLE ers_users ADD CONSTRAINT ers_user_roles_fk
--    FOREIGN KEY (user_role_id) REFERENCES ers_user_roles (ers_user_role_id) ON DELETE CASCADE;

/*******************************************************************************
   Create Sequences
********************************************************************************/
CREATE SEQUENCE "101_user_id_seq";

CREATE SEQUENCE "101_role_id_seq";
--
--CREATE SEQUENCE reimb_status_id_seq;
--
--CREATE SEQUENCE reimb_type_id_seq;
--
--CREATE SEQUENCE ers_user_role_id_seq;

/*******************************************************************************
   Create Triggers
********************************************************************************/
CREATE OR REPLACE TRIGGER user_id_trig
    BEFORE INSERT OR UPDATE ON "101_user"
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN
            SELECT useer_id_seq.nextVal INTO :new.user_id FROM DUAL;
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
--    
--CREATE OR REPLACE TRIGGER reimb_status_id_trig
--    BEFORE INSERT OR UPDATE ON ers_reimbursement_status
--    FOR EACH ROW
--    BEGIN
--        IF INSERTING THEN
--            SELECT reimb_status_id_seq.nextVal INTO :new.reimb_status_id FROM DUAL;
--        ELSIF UPDATING THEN
--            SELECT :old.reimb_status_id INTO :new.reimb_status_id FROM DUAL;
--        END IF;
--    END;
--    /
--    
--CREATE OR REPLACE TRIGGER reimb_type_id_trig
--    BEFORE INSERT OR UPDATE ON ers_reimbursement_type
--    FOR EACH ROW
--    BEGIN
--        IF INSERTING THEN
--            SELECT reimb_type_id_seq.nextVal INTO :new.reimb_type_id FROM DUAL;
--        ELSIF UPDATING THEN
--            SELECT :old.reimb_type_id INTO :new.reimb_type_id FROM DUAL;
--        END IF;
--    END;
--    /
--    
--CREATE OR REPLACE TRIGGER ers_user_role_id_trig
--    BEFORE INSERT OR UPDATE ON ers_user_roles
--    FOR EACH ROW
--    BEGIN
--        IF INSERTING THEN
--            SELECT ers_user_role_id_seq.nextVal INTO :new.ers_user_role_id FROM DUAL;
--        ELSIF UPDATING THEN
--            SELECT :old.ers_user_role_id INTO :new.ers_user_role_id FROM DUAL;
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
--INSERT INTO ers_user_roles (user_role) VALUES ('Employee');
--INSERT INTO ers_user_roles (user_role) VALUES ('Finance Manager');
--
--INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) 
--    VALUES ('admin', 'adminPass', 'admin', 'admin', 'admin@mail.com', 2);    
--INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) 
--    VALUES ('emp', 'empPass', 'emp', 'ployee', 'emp@mail.com', 1);
--INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) 
--    VALUES ('bman2000', 'coolObjects', 'Blake', 'Kruppa', 'blake@revature.com', 2);
--INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) 
--    VALUES ('sparkzy', 'beer', 'Bobby', 'McGetrick', 'bobby@revature.com', 1);
--
--INSERT INTO ers_reimbursement_status (reimb_status) VALUES ('Pending');
--INSERT INTO ers_reimbursement_status (reimb_status) VALUES ('Declined');
--INSERT INTO ers_reimbursement_status (reimb_status) VALUES ('Approved');
--
--INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('Food');
--INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('Lodging');
--INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('Travel');
--INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('Other');

/*******************************************************************************
   Commit changes and exit
********************************************************************************/
COMMIT;
EXIT;



















