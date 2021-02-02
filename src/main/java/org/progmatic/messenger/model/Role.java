package org.progmatic.messenger.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<GercikeUser> gercikeUserList;

    public Role() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
