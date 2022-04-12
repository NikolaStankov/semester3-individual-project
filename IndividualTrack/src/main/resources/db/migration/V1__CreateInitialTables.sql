CREATE TABLE team
(
    id           int         NOT NULL AUTO_INCREMENT,
    abbreviation varchar(4)  NOT NULL,
    city         varchar(50) NOT NULL,
    conference   varchar(50) NOT NULL,
    division     varchar(50) NOT NULL,
    full_name    varchar(50) NOT NULL,
    name         varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (abbreviation),
    UNIQUE (full_name)
);

CREATE TABLE player
(
    id         int         NOT NULL AUTO_INCREMENT,
    first_name varchar(50) NOT NULL,
    last_name  varchar(50) NOT NULL,
    position   varchar(50) NOT NULL,
    team_id    int,
    PRIMARY KEY (id),
    FOREIGN KEY (team_id) REFERENCES team (id)
);

CREATE TABLE game
(
    id              int          NOT NULL AUTO_INCREMENT,
    date            varchar(100) NOT NULL,
    season          int          NOT NULL,
    home_team_id    int          NOT NULL,
    visitor_team_id int          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (home_team_id) REFERENCES team (id),
    FOREIGN KEY (visitor_team_id) REFERENCES team (id)
);

CREATE TABLE ticket
(
    id             int    NOT NULL AUTO_INCREMENT,
    price          double NOT NULL,
    purchased_date DATE   NOT NULL,
    game_id        int    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES game (id)
);

