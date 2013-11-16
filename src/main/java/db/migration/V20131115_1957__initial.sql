create table uma_user
(
  usr_id serial not null primary key,
  usr_username varchar(255) not null unique,
  usr_password varchar not null,
  usr_firstname varchar(255) null,
  usr_lastname varchar(255) null,
  usr_gender varchar(6) null
);
  
create table uma_email(
  eml_id serial not null primary key,
  eml_email text not null unique,
  eml_user_id integer not null,
  
  constraint eml_user_id_usr_id_fgn_key foreign key (eml_user_id)
  references uma_user (usr_id) match SIMPLE
    on update no action on delete no action
);

create table uma_default_email(
  dfeml_id serial not null primary key,
  dfeml_user_id integer not null unique,
  dfeml_email_id integer not null unique,
  
  constraint dfeml_user_id_usr_id_fgn_key foreign key (dfeml_user_id)
  references uma_user (usr_id) match SIMPLE
    on update no action on delete no action,
    
  constraint dfeml_email_id_eml_id_fgn_key foreign key (dfeml_email_id)
  references uma_email (eml_id) match SIMPLE
    on update no action on delete no action
);