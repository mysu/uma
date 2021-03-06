/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

public abstract class AbstractDao<T> {

    private final String GET_LIST;

    public AbstractDao() {

        GET_LIST = "select x from " + getEntityClass().getName() + " x";
    }

    @Inject
    Provider<EntityManager> entitiyManagerProvider;

    final protected EntityManager getEM() {
        return entitiyManagerProvider.get();
    }

    abstract protected Class<T> getEntityClass();

    @Transactional
    public Collection<T> getList(int offset, Integer limit) {
        Query query = createQuery(GET_LIST);
        query.setFirstResult(offset >= 0 ? offset : 0);
        if (limit != null && limit > 0)
            query.setMaxResults(limit);

        @SuppressWarnings("unchecked")
        Collection<T> result = query.getResultList();

        return result;
    }

    @Transactional
    public T get(long id) {
        return getEM().find(getEntityClass(), Long.valueOf(id));
    }

    @Transactional
    public T save(T entity) {
        EntityManager em = getEM();
        entity = em.merge(entity);
        em.flush();
        return entity;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    protected T getSingleResult(Query query){
        try {
            return (T)query.getSingleResult();            
        } catch (NoResultException e) {
            return null;
        }
    }

    public Query createQuery(String query) {
        return getEM().createQuery(query);
    }

}
