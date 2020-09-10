package br.com.fiap.nac01.repository;

import br.com.fiap.nac01.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {

    List<Jogo> findByDescricaoContainsIgnoreCaseOrderByDescricao(final String descricao);

    List<Jogo> findByAnoLancamento(final LocalDate anoLancamento);

}
