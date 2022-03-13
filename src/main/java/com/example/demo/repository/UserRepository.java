/*
 */
package com.example.demo.repository;

import com.example.demo.component.UserType;
import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ianur
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    boolean existsByUserId(Long user_id);

    boolean existsByUserIdAndType(Long user_id, UserType user_role);
}
