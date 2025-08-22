package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Repository;

import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Entity.EntityAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Indicamos que este es un repositorio de la API
@Repository //Esta interfaz es CLAVE
public interface RepositoryAutor  extends JpaRepository<EntityAutor, Long> { //Heredamos del repositorio JPA indicando la entidad a la que pertenece este repositorio (EntityAutor) Y el tipo de dato del ID (Long)
}

