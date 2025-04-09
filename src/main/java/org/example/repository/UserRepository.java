package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.entity.UserEntity;
import org.example.entity.UserRole;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final HibernateTemplate hibernateTemplate;

    public UserRepository(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Transactional
    public void saveUser(UserEntity user) {
        hibernateTemplate.save(user);
    }

    @Transactional
    public void assignRole(String email, String role) {
        UserRole userRole = new UserRole();
        userRole.setUserEmail(email);
        userRole.setRoleName(role);
        hibernateTemplate.save(userRole);
    }

    public UserEntity getUserByEmail(String email) {
        List<UserEntity> users = (List<UserEntity>) hibernateTemplate.findByNamedParam(
                "from UserEntity where email = :email",
                new String[]{"email"},
                new Object[]{email}
        );
        return users.isEmpty() ? null : users.get(0);
    }

}
