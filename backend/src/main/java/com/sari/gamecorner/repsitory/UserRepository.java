package com.sari.gamecorner.repsitory;

import com.sari.gamecorner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
}
