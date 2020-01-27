package com.mft.securwa.repositories;

import com.mft.securwa.entities.User;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
}
