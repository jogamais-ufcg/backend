package com.jogamais.ufcg.repositories;

import com.jogamais.ufcg.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByDescription(String description);
}

