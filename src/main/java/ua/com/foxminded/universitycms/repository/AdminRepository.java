package ua.com.foxminded.universitycms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.universitycms.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
