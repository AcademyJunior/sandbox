CREATE TABLE FEATURES (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  NAME VARCHAR (255) not null,
  DESCRIPTION VARCHAR(255) not null,
  AUTHOR VARCHAR(255) not null,
  IMAGE_URL VARCHAR(255) not null,
  PRIMARY KEY (ID)
);

INSERT INTO FEATURES(NAME, DESCRIPTION, AUTHOR, IMAGE_URL)
VALUES('TaskManager', 'Insert task, describe it and predict how much time/money it ll take','Lukasz','https://s-media-cache-ak0.pinimg.com/736x/4e/b0/08/4eb008aaa35567b952344f2206a032bb.jpg');
