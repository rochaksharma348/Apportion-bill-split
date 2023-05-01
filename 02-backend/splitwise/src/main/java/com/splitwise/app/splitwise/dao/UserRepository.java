package com.splitwise.app.splitwise.dao;


import com.splitwise.app.splitwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
    public User findByUsername(String username);

    public List<User> findByUsernameContainsIgnoreCase(String username);
    public List<User> findByEmailContainingIgnoreCase(String email);
    public List<User> findByPhoneNumberIgnoreCase(String username);
    public List<User> findByFullNameContainingIgnoreCase(String name);

//    @Query("SELECT u FROM User u WHERE CONCAT(upper(u.firstName), ' ', upper(u.lastName)) LIKE %:name%")
//    public List<User> findByName(@Param("name") String name);


}
