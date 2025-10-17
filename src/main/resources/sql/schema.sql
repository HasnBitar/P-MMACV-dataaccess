create table PersonalInfos(
  id int not null AUTO_INCREMENT PRIMARY KEY,
  firstName varchar(100) not null,
  lastName varchar(100) not null,
  email varchar(150) unique not null,
  address varchar(255),
  phoneNumber varchar(50),
);