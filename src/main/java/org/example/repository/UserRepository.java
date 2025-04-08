package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.entity.UserEntity;
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
        hibernateTemplate.execute(session -> {
            session.createNativeQuery("INSERT INTO user_role (userEmail, roleName) VALUES (:email, :role)")
                    .setParameter("email", email)
                    .setParameter("role", role)
                    .executeUpdate();
            return null;
        });
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
