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

    @ManyToOne(fetch = FetchType.LAZY)
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

}
