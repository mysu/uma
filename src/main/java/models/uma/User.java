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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;

import services.uma.Cacheable;

@Entity()
@Table(name="uma_user")
public class User implements Cacheable<Long> {

    /**
     * 
     */
    private static final long serialVersionUID = 8536322154189025203L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usr_id")
    private Long id;

    @Column(name="usr_username", unique = true, nullable = false)
    private String username;

    @Column(name="usr_password", nullable = false)
    private String password;

    @Column(name="usr_firstname")
    private String firstname;

    @Column(name="usr_lastname")
    private String lastname;

    @Column(name="usr_gender" )
    private Gender gender;
    
    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    private List<Email> emailList;
    
    public User() {
        emailList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public User setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public User setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public Long getKey() {
        return getId();
    }

    @Override
    public Class<?> getType() {
        return User.class;
    }

    public List<Email> getEmailList() {
        return Lists.newArrayList(emailList);
    }

    public void setEmailList(List<Email> emailList) {
        this.emailList.addAll(emailList);
    }

    public User addEmail(Email email) {
        emailList.add(email);
        return this;
    }

}
