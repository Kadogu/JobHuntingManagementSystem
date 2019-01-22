create database gradwork default character set utf8 collate utf8_general_ci;

grant all on gradwork.* to jyobi@"10.0.2.10" identified by "jyobijyobi";

flush privileges;
