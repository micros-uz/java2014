package uz.micros.jstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.micros.jstore.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
    AppUser getUserByUserName(String name);
}
