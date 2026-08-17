package br.com.autogestao.repository;
import br.com.autogestao.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
 @Query("select distinct c from Cliente c left join fetch c.veiculos order by c.nome") List<Cliente> listarComVeiculos();
}
