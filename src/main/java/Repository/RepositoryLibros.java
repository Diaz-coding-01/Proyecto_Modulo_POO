package Repository;

import Entity.EntityLibros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryLibros extends JpaRepository<EntityLibros, Long> {
}
