create table Personal_infos(
  id int not null AUTO_INCREMENT PRIMARY KEY,
  first_name varchar(100) not null,
  last_name varchar(100) not null,
  email varchar(150) unique not null,
  address varchar(255),
  phone_number varchar(50)
);