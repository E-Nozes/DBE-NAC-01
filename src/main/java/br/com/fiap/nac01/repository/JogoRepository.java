package br.com.fiap.nac01.repository;

import br.com.fiap.nac01.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {

    Optional<Jogo> findByDescricaoContainsIgnoreCase(final String descricao);

    Optional<Jogo> findByDescricao(final String descricao);

    List<Jogo> findByAnoLancamento(final String anoLancamento);

}
