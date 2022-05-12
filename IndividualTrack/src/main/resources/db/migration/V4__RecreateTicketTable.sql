CREATE TABLE ticket
(
    id            int          NOT NULL AUTO_INCREMENT,
    ticket_type   varchar(50)  NOT NULL,
    price         varchar(100) NOT NULL,
    specification varchar(500) NOT NULL,
    PRIMARY KEY (id)
);
