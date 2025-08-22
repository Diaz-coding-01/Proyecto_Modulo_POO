package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Repository;

import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Entity.EntityAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAutor  extends JpaRepository<EntityAutor, Long> {
}

