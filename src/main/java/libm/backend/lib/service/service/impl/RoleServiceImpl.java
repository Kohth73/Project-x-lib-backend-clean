package libm.backend.lib.service.service.impl;

import libm.backend.lib.entity.Role;
import libm.backend.lib.entity.Permission;
import libm.backend.lib.repository.RoleRepository;
import libm.backend.lib.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role createRole(String name, String description, Set<Permission> permissions) {
		Role role = new Role();
		role.setName(name);
		role.setDescription(description);
		if (permissions != null) {
			role.setPermissions(permissions);
		} else {
			role.setPermissions(new HashSet<>());
		}
		return roleRepository.save(role);
	}

	@Override
	public Role getRoleById(Long id) {
		return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
	}

	@Override
	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
	}
}
