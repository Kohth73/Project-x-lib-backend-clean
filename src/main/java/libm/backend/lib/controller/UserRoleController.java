package libm.backend.lib.controller;

import libm.backend.lib.entity.Role;
import libm.backend.lib.entity.Permission;
import libm.backend.lib.service.RoleService;
import libm.backend.lib.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class UserRoleController {

    private final RoleService roleService;
    private final PermissionService permissionService;

    @Autowired
    public UserRoleController(RoleService roleService, PermissionService permissionService) {
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    // CREATE new role with optional permissions
    @PostMapping
    public ResponseEntity<Role> createRole(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam(required = false) Set<Long> permissionIds
    ) {
        Set<Permission> permissions = new HashSet<>();
        if (permissionIds != null) {
            for (Long pid : permissionIds) {
                permissions.add(permissionService.getPermissionById(pid));
            }
        }
        Role createdRole = roleService.createRole(name, description, permissions);
        return ResponseEntity.ok(createdRole);
    }

    // GET role by ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    // DELETE role
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}


