package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Repository;

import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Entity.EntityLibro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Se indica que es un repositorio en la API
public interface RepositoryLibro extends JpaRepository<EntityLibro, Long> { //Se hereda el repositorio de la JPA indicando que pertenece a la Entidad de libro con el Tipo de dato del ID de la tabla, en este caso Long
    EntityLibro findByTitulo(String titulo); //Método creado manualmente para buscar por el campo título
}
