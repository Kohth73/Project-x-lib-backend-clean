package libm.backend.lib.service;

import libm.backend.lib.entity.Permission;

public interface PermissionService {

    // Create a permission with all required fields
    Permission createPermission(String name, String code, String description);

    Permission getPermissionById(Long id);

    void deletePermission(Long id);
}


