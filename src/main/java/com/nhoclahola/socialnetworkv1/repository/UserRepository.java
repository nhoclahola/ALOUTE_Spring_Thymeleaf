package com.nhoclahola.socialnetworkv1.repository;

import com.nhoclahola.socialnetworkv1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
    public abstract Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:query% " +
            "OR u.lastName LIKE %:query% " +
            "OR u.email LIKE %:query%")
    public abstract List<User> searchUser(@Param("query") String query);
}
