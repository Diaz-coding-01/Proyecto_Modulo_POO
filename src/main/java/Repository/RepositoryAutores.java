package Repository;

import Entity.EntityAutores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAutores  extends JpaRepository<EntityAutores, Long> {
}
