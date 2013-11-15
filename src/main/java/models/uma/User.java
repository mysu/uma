package models.uma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    private Gender gender;

    //TODO test it
    @OneToOne(fetch = FetchType.EAGER)
    private Email defaultEmail;

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

    public Email getDefaultEmail() {
        return defaultEmail;
    }

    public User setDefaultEmail(Email defaultEmail) {
        this.defaultEmail = defaultEmail;
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
