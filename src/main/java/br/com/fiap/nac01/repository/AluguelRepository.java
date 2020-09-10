package br.com.fiap.nac01.repository;

import br.com.fiap.nac01.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {

    List<Aluguel> findAllByCliente_Codigo(final Integer codigoCliente);

    List<Aluguel> findAllByPrecoAndCliente_Codigo(final Double preco, final Integer codigoCliente);

}
