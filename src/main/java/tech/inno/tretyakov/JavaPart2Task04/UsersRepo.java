package tech.inno.tretyakov.JavaPart2Task04;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Integer> {
}
