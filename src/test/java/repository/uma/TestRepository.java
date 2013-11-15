package repository.uma;

import repository.uma.AbstractRepository;
import models.uma.Email;

public class TestRepository extends AbstractRepository<Email, Long> {

    @Override
    protected Email persistInDB(Email object) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Email getFromDB(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Class<Email> getType() {
        return Email.class;
    }

}
