CREATE TABLE purchase
(
    id        int NOT NULL AUTO_INCREMENT,
    ticket_id int NOT NULL,
    game_id   int NOT NULL,
    quantity  int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (ticket_id) REFERENCES ticket (id),
    FOREIGN KEY (game_id) REFERENCES game (id)
);