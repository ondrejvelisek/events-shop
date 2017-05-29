package cz.muni.fi.eventsshop.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class User extends AbstractEntity {

	@NotNull
    @Column(nullable = false)
	private String name;

	@NotNull
	@Column(nullable = false, unique = true)
	private String oAuthId;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(value= EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER, targetClass = Role.class)
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!getName().equals(user.getName())) return false;
        if (!getOAuthId().equals(user.getOAuthId())) return false;
        return getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getOAuthId().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }

    public enum Role {
	    ADMIN,
        USER
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOAuthId() {
        return oAuthId;
    }

    public void setOAuthId(String oAuthId) {
        this.oAuthId = oAuthId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
