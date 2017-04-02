CREATE TABLE tb_cal_ex (
   id  int NOT NULL,
   op1  int NOT NULL,
   op  varchar(5) NOT NULL,
   op2  int NOT NULL,
   result  int NOT NULL
);

ALTER TABLE tb_cal_ex ADD PRIMARY KEY (id);

select * from tb_cal_ex;

create Sequence seq_log
start with 1
increment by 1
maxvalue 10000000;

truncate table tb_cal_ex;
