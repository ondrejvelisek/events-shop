package cz.muni.fi.eventsshop.service.DTO;

import cz.muni.fi.eventsshop.model.User;

import java.util.Set;

/**
 * Created by pato on 1.6.2017.
 */
public class UserDTO {

    private long id;
    private String name;
    private String oAuthId;
    private String email;
    private Set<User.Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getoAuthId() {
        return oAuthId;
    }

    public void setoAuthId(String oAuthId) {
        this.oAuthId = oAuthId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<User.Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<User.Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", oAuthId='" + oAuthId + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
