package libm.backend.lib.controller;

import libm.backend.lib.entity.Permission;
import libm.backend.lib.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    // Create permission with name, code, and description
    @PostMapping("/create")
    public Permission createPermission(
            @RequestParam String name,
            @RequestParam String code,
            @RequestParam String description
    ) {
        return permissionService.createPermission(name, code, description);
    }
}
