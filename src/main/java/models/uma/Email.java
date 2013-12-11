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

package models.uma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import services.uma.Cacheable;

@Entity
@Table(name="uma_email")
public class Email implements Cacheable<Long> {
    /**
     * 
     */
    private static final long serialVersionUID = 1725587779039132112L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="eml_id")
    private Long id;

    @Column(name="eml_email", unique = true, nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY,optional=false)
    private User user;

    public Long getId() {
        return id;
    }

    public Email setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Email setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public Long getKey() {
        return getId();
    }

    @Override
    public Class<?> getType() {
        return Email.class;
    }

    public User getUser() {
        return user;
    }

    public Email setUser(User user) {
        this.user = user;
        return this;
    }

}
