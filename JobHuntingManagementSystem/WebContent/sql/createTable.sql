create table account(
  user_id char(16) primary key,
  pw char(255) not null
);

create table department(
  department_id char(5) primary key,
  department_name text not null,
  number int(1) not null
);

create table belongs(
  belongs_id char(1) primary key,
  belongs_name text not null,
  sort int(1) not null unique auto_increment
);

create table course(
  course_id char(5) primary key,
  course_name text not null,
  year int(1) not null default 2,
  number int(1) not null,
  sort int(2) not null unique auto_increment,
  department_id char(5) not null,
  belongs_id char(1) not null,
  foreign key (department_id) references department(department_id),
  foreign key (belongs_id) references belongs(belongs_id)
);

create table student(
  student_id int(7) primary key,
  name text,
  mail_address varchar(255) unique,
  school_year int(1) not null default 1,
  course_id char(5) not null,
  graduation_flg boolean not null default false,
  user_id char(16),
  foreign key (course_id) references course(course_id),
  foreign key (user_id) references account(user_id)
);

create table repetition_list(
  student_id int(7) primary key,
  foreign key (student_id) references student(student_id)
);

create table teacher(
  teacher_id int(2) primary key auto_increment,
  name text not null,
  belongs_id char(1) not null,
  mail_address varchar(255) not null unique,
  admin_flg boolean not null default false,
  user_id char(16) not null,
  foreign key (belongs_id) references belongs(belongs_id),
  foreign key (user_id) references account(user_id)
);

create table charge_class(
  teacher_id int(2),
  course_id char(5),
  school_year int(1),
  primary key(teacher_id,course_id,school_year),
  foreign key (teacher_id) references teacher(teacher_id),
  foreign key (course_id) references course(course_id)
);

create table company (
  company_id char(8) primary key,
  company_name text not null,
  postal_code varchar(8) not null,
  address text not null,
  phone_number varchar(14) not null
);

create table type_of_industry(
  industry_code char(1) primary key,
  industry_name text not null
);

create table occupations(
  occupations_code int(2) primary key auto_increment,
  occupations_name text not null
);

create table pdf (
  pdf_id char(8) primary key,
  file_name text,
  student_id int(7) not null,
  filing_date date,
  sorf text,
  sfdate date,
  company_id char(8) not null,
  type_of_industry char(1),
  occupations int(2),
  application_form text,
  content_of_test text,
  advice_to_junior text,
  cc_flg boolean not null default false,
  tc_flg boolean not null default false,
  edc_flg boolean not null default false,
  foreign key (student_id) references student(student_id),
  foreign key (company_id) references company(company_id),
  foreign key (type_of_industry) references type_of_industry(industry_code),
  foreign key (occupations) references occupations(occupations_code)
);

create table contents_test (
  contents_test_id int primary key auto_increment,
  pdf_id char(8) not null,
  n int(1) not null,
  start_date date,
  start_hour int(2),
  start_minute int(2),
  last_hour int(2),
  last_minute int(2),
  test_category text,
  writing text,
  viewer_no int(2),
  view_time int(3),
  view_content text,
  groop_no int(2),
  discussion_no int(2),
  foreign key (pdf_id) references pdf(pdf_id)
);

create table document_application(
  document_application_id char(8) primary key,
  application_date date not null,
  student_id int(7) not null,
  company_id char(8) not null,
  bring_mailing boolean not null,
  deadline date not null,
  resume_flg int(1) not null default 0,
  graduation_certificate_flg int(1) not null default 0,
  record_certificate_flg int(1) not null default 0,
  health_certificate_flg int(1) not null default 0,
  nomination_form_flg int(1) not null default 0,
  other_flg int(1) not null default 0,
  issue_fee int(4) as((graduation_certificate_flg + record_certificate_flg + health_certificate_flg + nomination_form_flg) * 100) not null,
  issue_flg boolean not null default false,
  destination int(1),
  foreign key (student_id) references student(student_id),
  foreign key (company_id) references company(company_id)
);

create table destination(
  document_application_id char(8) primary key,
  postal_code varchar(8),
  address text,
  individual text,
  foreign key (document_application_id) references document_application(document_application_id)
);

create table document_contents(
  document_contents_id int(1) primary key auto_increment,
  document text not null
);

create table document_other_contents(
  document_application_id char(8) primary key,
  contents text not null,
  foreign key (document_application_id) references document_application(document_application_id)
);

create table log(
  log_id int primary key auto_increment,
  log_datetime datetime not null,
  login_id char(16) not null,
  login_pw char(255) not null,
  login_sorf boolean not null default false
);
