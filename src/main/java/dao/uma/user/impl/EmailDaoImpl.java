package dao.uma.user.impl;

import javax.persistence.Query;

import models.uma.Email;
import dao.AbstractDao;
import dao.uma.user.EmailDao;

public class EmailDaoImpl extends AbstractDao<Email> implements EmailDao {
    private static final String getByEmail = "select em from Email em where em.email = :email";

    @Override
    public Email getByEmail(String email) {
        Query query = super.createQuery(getByEmail);
        query.setParameter("email", email);
        return (Email) getSingleResult(query);
    }

    @Override
    protected Class<Email> getEntityClass() {
        return Email.class;
    }

}
