package br.com.autogestao.repository;
import br.com.autogestao.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> { boolean existsByPlacaIgnoreCase(String placa); }
