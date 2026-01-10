package libm.backend.lib.service.service.impl;

import libm.backend.lib.entity.Permission;
import libm.backend.lib.repository.PermissionRepository;
import libm.backend.lib.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission createPermission(String name, String code, String description) {
        // Use the constructor we just added
        Permission permission = new Permission(name, code, description);
        return permissionRepository.save(permission);
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with ID: " + id));
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
