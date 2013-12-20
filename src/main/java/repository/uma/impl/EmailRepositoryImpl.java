package repository.uma.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.uma.user.EmailDao;
import models.uma.Email;
import repository.uma.AbstractRepository;
import repository.uma.EmailRepository;

@Singleton
public class EmailRepositoryImpl extends AbstractRepository<Email, Long>
        implements EmailRepository {
    
    @Inject
    private EmailDao emailDao;

    @Override
    protected Email persistInDB(Email email) {
        return emailDao.save(email);
    }

    @Override
    protected Email getFromDB(Long id) {
        return emailDao.get(id);
    }

    @Override
    protected Class<Email> getType() {
        return Email.class;
    }

}
