package com.bonappetit.repo;

import com.bonappetit.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserNameAndEmail(String username, String password);

    User findByUserName(String username);

    User findUserById(Long ID);
}
