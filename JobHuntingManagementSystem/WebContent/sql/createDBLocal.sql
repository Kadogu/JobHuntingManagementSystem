create database gradwork default character set utf8 collate utf8_general_ci;

grant all on gradwork.* to jyobi@"localhost" identified by "jyobijyobi";

flush privileges;
