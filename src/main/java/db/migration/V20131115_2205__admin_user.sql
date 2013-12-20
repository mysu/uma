--
--

INSERT INTO uma_user(usr_username, usr_password)
    VALUES ('admin', 'admin');
            
INSERT INTO uma_email(eml_email, eml_user_id)
    VALUES ('admin@uma.mysu.com', 1);

INSERT INTO uma_default_email(dfeml_user_id, dfeml_email_id)
    VALUES (1, 1);
