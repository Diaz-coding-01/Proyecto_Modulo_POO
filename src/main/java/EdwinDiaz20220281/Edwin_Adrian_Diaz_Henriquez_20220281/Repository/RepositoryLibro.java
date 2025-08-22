package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Repository;

import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Entity.EntityLibro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryLibro extends JpaRepository<EntityLibro, Long> {
}
