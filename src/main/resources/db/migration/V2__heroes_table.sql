CREATE TABLE HEROES (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  NAME VARCHAR (255) not null,
  STRENGTH INT not null,
  AGILITY INT not null,
  INTELLIGENCE INT not null,
  PRIMARY KEY (ID)
);

INSERT INTO FEATURES VALUES(default, 'HeroMaker', 'Choose your name and spread 10 skill points.', 'Marek', 'https://psmedia.playstation.com/is/image/psmedia/fallout-two-column-01-ps4-eu-05Jun15?$2ColExpand_Image$');