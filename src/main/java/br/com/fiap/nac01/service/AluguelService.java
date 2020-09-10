package br.com.fiap.nac01.service;

import br.com.fiap.nac01.exception.InvalidDataException;
import br.com.fiap.nac01.exception.ResourceNotFoundException;
import br.com.fiap.nac01.model.Aluguel;
import br.com.fiap.nac01.repository.AluguelRepository;
import br.com.fiap.nac01.repository.ClienteRepository;
import br.com.fiap.nac01.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JogoRepository jogoRepository;

    public List<Aluguel> findAll() {
        return this.aluguelRepository.findAll();
    }

    public Aluguel findByCodigo(Integer codigo) throws ResourceNotFoundException {
        return this.verificarSeExiste(codigo);
    }

    public List<Aluguel> findAllByClienteCodigo(Integer codigo) {
        return this.aluguelRepository.findAllByCliente_Codigo(codigo);
    }

    public List<Aluguel> findAllByPrecoAndClienteCodigo(Double preco, Integer codigo) {
        return this.aluguelRepository.findAllByPrecoAndCliente_Codigo(preco, codigo);
    }

    public Aluguel create(Aluguel aluguel) throws InvalidDataException {
        this.verificarSeClienteJogoExistem(aluguel);

        if (aluguel.getDataAluguel().isAfter(LocalDate.now())
                || aluguel.getDataDevolucao().isBefore(LocalDate.now())
                || aluguel.getDataDevolucao().isBefore(aluguel.getDataAluguel())) {
            throw new InvalidDataException("Data inválida");
        }

        return this.aluguelRepository.save(aluguel);
    }

    public Aluguel update(Integer codigo, Aluguel aluguel) throws ResourceNotFoundException, InvalidDataException {
        this.aluguelRepository.findById(codigo).orElseThrow(ResourceNotFoundException::new);

        this.verificarSeClienteJogoExistem(aluguel);

        if (aluguel.getDataAluguel().isAfter(LocalDate.now())) {
            throw new InvalidDataException("Data inválida");
        }

        aluguel.setCodigo(codigo);
        return this.aluguelRepository.save(aluguel);
    }

    public void remove(Integer codigo) throws ResourceNotFoundException {
        this.aluguelRepository.delete(this.verificarSeExiste(codigo));
    }

    private Aluguel verificarSeExiste(Integer codigo) throws ResourceNotFoundException {
        return this.aluguelRepository.findById(codigo).orElseThrow(ResourceNotFoundException::new);
    }

    private void verificarSeClienteJogoExistem(Aluguel aluguel) throws InvalidDataException {
        if (!this.clienteRepository.findById(aluguel.getCliente().getCodigo()).isPresent()
                || !this.jogoRepository.findById(aluguel.getJogo().getCodigo()).isPresent()) {
            throw new InvalidDataException("Cliente/Jogo informado não existe");
        }
    }

}
