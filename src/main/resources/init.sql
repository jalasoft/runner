DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS run;

CREATE TABLE IF NOT EXISTS user (
  id BIGINT AUTO_INCREMENT,
  domain_id UUID,
  version BIGINT,
  first_name VARCHAR(20) NOT NULL ,
  last_name VARCHAR(30) NOT NULL ,
  birthday DATE NOT NULL,
  email VARCHAR(20) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS run (
  id BIGINT AUTO_INCREMENT,
  domain_id UUID,
  user_id UUID,
  version BIGINT,
  date DATE NOT NULL,
  distance_meters BIGINT NOT NULL,
  duration VARCHAR NOT NULL,

  PRIMARY KEY (id)
);