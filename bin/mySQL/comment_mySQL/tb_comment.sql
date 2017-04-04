CREATE TABLE tb_comment (
   id  int NOT NULL,
   name  varchar(15) NOT NULL,
   content varchar(30) NOT NULL
);

ALTER TABLE tb_comment ADD PRIMARY KEY (id);