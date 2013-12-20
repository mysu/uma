--
-- removing not null in eml_user_id
--


alter table uma_email alter column eml_user_id drop not null;
