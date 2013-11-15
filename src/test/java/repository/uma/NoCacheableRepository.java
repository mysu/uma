package repository.uma;

import repository.uma.AbstractRepository;

public class NoCacheableRepository extends AbstractRepository<NoCacheableClass, Integer> {

    @Override
    protected NoCacheableClass persistInDB(NoCacheableClass object) {
        return null;
    }

    @Override
    protected NoCacheableClass getFromDB(Integer id) {
        return null;
    }

    @Override
    protected Class<NoCacheableClass> getType() {
        return NoCacheableClass.class;
    }

}
