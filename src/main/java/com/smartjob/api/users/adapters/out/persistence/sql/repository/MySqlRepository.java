package com.smartjob.api.users.adapters.out.persistence.sql.repository;

import com.smartjob.api.users.adapters.out.persistence.sql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MySqlRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
}
