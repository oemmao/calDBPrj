CREATE TABLE tb_msg (
   id  int NOT NULL,
   cod  int NOT NULL,
   message  varchar(20) NOT NULL
);

ALTER TABLE tb_msg ADD PRIMARY KEY (id);

select * from tb_msg;

Create Sequence msg_log
start with 1
increment by 1
maxvalue 10000000;



