package libm.backend.lib.service;

import libm.backend.lib.entity.Role;
import libm.backend.lib.entity.Permission;

import java.util.Set;

public interface RoleService {

    // Create role with name, description, and optional permissions
    Role createRole(String name, String description, Set<Permission> permissions);

    Role getRoleById(Long id);

    void deleteRole(Long id);
}







