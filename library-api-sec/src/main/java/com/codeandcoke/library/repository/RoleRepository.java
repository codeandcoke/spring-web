package com.codeandcoke.library.repository;

import com.codeandcoke.library.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);
}
