package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Permission;

public interface PermissionRepository {
	
	List<Permission> all();
	Permission findById(Long id);
	Permission add(Permission permission);
	void remove(Permission permission);
}
