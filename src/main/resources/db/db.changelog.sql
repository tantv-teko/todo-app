create table note (
    note_id int primary key AUTO_INCREMENT,
    title varchar(255),
    content varchar(255),
    color_id int,
    create_at DateTime,
    edited_at DateTime
);
create table color (
  	color_id int primary key AUTO_INCREMENT,
 	color_name varchar(255),
  	code varchar(255),
    is_default char(20)
);
create table label (
  	label_id int primary key AUTO_INCREMENT,
 	label_name varchar(255)
);
create table label_in_note (
  	label_in_note_id int primary key AUTO_INCREMENT,
    label_id int,
 	note_id int
);