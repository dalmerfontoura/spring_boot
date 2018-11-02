package pro.fontoura.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pro.fontoura.cursomc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estado_id ORDER BY obj.nome")
	List<Cidade> findCidades(@Param("estado_id") Integer estado);

}
