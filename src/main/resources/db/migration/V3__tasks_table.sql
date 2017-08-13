CREATE TABLE TASKS(
    TASK_ID BIGINT NOT NULL AUTO_INCREMENT,
    TASK_NAME VARCHAR(255) not null,
    TASK_DESCRIPTION VARCHAR(255) not null,
    ESTIMATE_TIME int not null,
    ESTIMATE_PRICE int not null,
    PRIMARY KEY (TASK_ID)
 );


INSERT INTO FEATURES(NAME, DESCRIPTION, AUTHOR, IMAGE_URL)
VALUES('TaskManager', 'Insert task, describe it and predict how much time/money it ll take','Lukasz','https://s-media-cache-ak0.pinimg.com/736x/4e/b0/08/4eb008aaa35567b952344f2206a032bb.jpg');

