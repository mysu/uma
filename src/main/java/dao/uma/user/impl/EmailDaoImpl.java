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
