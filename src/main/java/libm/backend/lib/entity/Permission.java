package libm.backend.lib.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String code;  // <-- stable reference

    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

    public Permission() {}
    public Permission(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
}





