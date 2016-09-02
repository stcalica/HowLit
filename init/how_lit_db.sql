CREATE DATABASE how_lit_db;
USE how_lit_db;
CREATE TABLE artist_rankings(
    artist_id INT NOT NULL AUTO_INCREMENT,
   artist_name VARCHAR(50) NOT NULL,
   artist_rank VARCHAR(100) NOT NULL,
   updated_date DATE,
   PRIMARY KEY ( artist_id )
   );
