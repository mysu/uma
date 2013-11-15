package dao;

import models.uma.Email;

public class TestDao extends AbstractDao<Email> {

    @Override
    protected Class<Email> getEntityClass() {
        return Email.class;
    }
}