DROP TABLE IF EXISTS blog;
CREATE TABLE blog(id BIGINT PRIMARY KEY, theme VARCHAR(255) NOT NULL, summary TEXT NOT NULL, content TEXT NOT NULL, like_vol BIGINT NOT NULL, comment_vol BIGINT NOT NULL, rt_vol BIGINT NOT NULL, date TIMESTAMP NOT NULL);