package com.spring.ecommerce.repository;

import com.spring.ecommerce.model.Role;
import com.spring.ecommerce.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleType(RoleType roleType);
}
