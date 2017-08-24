CREATE TABLE WORK_EMPLOYEES (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  NAME VARCHAR (255) not null,
  HOURLY_WAGE FLOAT not null,
  TAX_RATE FLOAT not null,
  PRIMARY KEY (ID)
);

CREATE TABLE WORK_ENTRIES (
  ENTRY_ID BIGINT NOT NULL AUTO_INCREMENT,
  EMPLOYEE_ID BIGINT not null,
  START_TIME Datetime,
  FINISH_TIME Datetime,
  PRIMARY KEY (ENTRY_ID),
  FOREIGN KEY (EMPLOYEE_ID) REFERENCES WORK_EMPLOYEES(ID)
);

INSERT INTO FEATURES VALUES(default, 'WorkTime', 'Track your hours at work and calculate your salary.', 'Marek', 'https://res-4.cloudinary.com/the-watch-gallery/image/upload/c_pad,dpr_1.0,f_auto,h_391,q_auto,w_391/media/catalog/product/j/a/jaeger-lecoultre_q8012520_sku_577786_usp_35656.jpg');
