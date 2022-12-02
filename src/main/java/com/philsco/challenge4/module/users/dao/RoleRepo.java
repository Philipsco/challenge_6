package com.philsco.challenge4.module.users.dao;

import com.philsco.challenge4.enumeration.ERole;
import com.philsco.challenge4.module.users.model.RolesModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RolesModels, Integer> {
    Optional<RolesModels> findByName(ERole name);
}
