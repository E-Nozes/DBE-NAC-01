package br.com.fiap.nac01.service;

import br.com.fiap.nac01.exception.InvalidDataException;
import br.com.fiap.nac01.exception.ResourceNotFoundException;
import br.com.fiap.nac01.model.Jogo;
import br.com.fiap.nac01.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    public List<Jogo> findAll() {
        return this.jogoRepository.findAll();
    }

    public Jogo findByCodigo(Integer codigo) throws ResourceNotFoundException {
        return this.verificarSeExiste(codigo);
    }

    public Jogo findByDescricao(String descricao) throws ResourceNotFoundException {
        return this.jogoRepository.findByDescricaoContainsIgnoreCase(descricao).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Jogo> findByAnoLancamento(String anoLancamento) {
        return this.jogoRepository.findByAnoLancamento(anoLancamento);
    }

    public Jogo create(Jogo jogo) throws InvalidDataException {
        if (this.jogoRepository.findByDescricao(jogo.getDescricao()).isPresent()) {
            throw new InvalidDataException("Jogo já está cadastrado");
        }

        return this.jogoRepository.save(jogo);
    }

    public Jogo update(Integer codigo, Jogo jogo) throws ResourceNotFoundException, InvalidDataException {
        Jogo jogoCadastrado = this.jogoRepository.findById(codigo).orElseThrow(ResourceNotFoundException::new);

        if (this.jogoRepository.findByDescricao(jogo.getDescricao()).isPresent()
                && !jogo.getDescricao().equals(jogoCadastrado.getDescricao())) {
            throw new InvalidDataException("Jogo já está cadastrado");
        }

        jogo.setCodigo(codigo);
        return this.jogoRepository.save(jogo);
    }

    public void remove(Integer codigo) throws ResourceNotFoundException {
        this.jogoRepository.delete(this.verificarSeExiste(codigo));
    }

    private Jogo verificarSeExiste(Integer codigo) throws ResourceNotFoundException {
        return this.jogoRepository.findById(codigo).orElseThrow(ResourceNotFoundException::new);
    }

}
