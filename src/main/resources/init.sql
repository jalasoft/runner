CREATE TABLE IF NOT EXISTS runner (
  id BIGINT AUTO_INCREMENT,
  nickname VARCHAR(20) NOT NULL UNIQUE,
  birthday DATE NOT NULL,
  first_name VARCHAR(20) NOT NULL ,
  last_name VARCHAR(30) NOT NULL ,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS run (
  id BIGINT AUTO_INCREMENT,
  run_id UUID,
  nickname VARCHAR(20) NOT NULL,
  date DATE NOT NULL,
  distance_unit INT NOT NULL,
  distance_value INT NOT NULL,
  PRIMARY KEY (id)
);