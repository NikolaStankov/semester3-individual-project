ALTER TABLE purchase ADD user_id int NOT NULL ;

ALTER TABLE purchase ADD FOREIGN KEY (user_id) REFERENCES user (id);