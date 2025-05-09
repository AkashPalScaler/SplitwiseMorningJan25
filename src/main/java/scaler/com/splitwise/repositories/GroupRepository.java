package scaler.com.splitwise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scaler.com.splitwise.models.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
