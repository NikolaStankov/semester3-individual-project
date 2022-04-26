CREATE TABLE user
(
    id       int         NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    role     varchar(20) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username)
);