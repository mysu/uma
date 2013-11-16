package models.uma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import services.uma.Cacheable;

@Entity()
@Table(name="uma_user")
public class User implements Cacheable<Long> {

    /**
     * 
     */
    private static final long serialVersionUID = 8536322154189025203L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    
    //TODO add default email

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

}
