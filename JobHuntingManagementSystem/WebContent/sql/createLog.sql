create table log(
  log_id char(8) primary key,
  date_time datetime not null,
  user_id char(16) not null,
  operation_contents varchar(256) not null
);
