--liquibase formatted sql
--changeset ozehospital:create-tables
CREATE TABLE patients (
                            id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                            age INT(2) NOT NULL,
                            last_visit_date datetime DEFAULT NULL,
                            name varchar(255) NOT NULL,
                            created_at datetime NOT NULL,
                            updated_at datetime DEFAULT NULL,
                            PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `staffs` (
                          id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                          name varchar(255) NOT NULL,
                          uuid varchar(36) NOT NULL,
                          registration_date datetime DEFAULT NULL,
                          created_at datetime NOT NULL,
                          updated_at datetime DEFAULT NULL,
                          PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;