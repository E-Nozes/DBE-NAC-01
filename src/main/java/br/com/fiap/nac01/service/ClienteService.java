package br.com.fiap.nac01.service;

import br.com.fiap.nac01.exception.InvalidDataException;
import br.com.fiap.nac01.exception.ResourceNotFoundException;
import br.com.fiap.nac01.model.Cliente;
import br.com.fiap.nac01.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    public Cliente findByCodigo(Integer codigo) throws ResourceNotFoundException {
        return this.verificarSeExiste(codigo);
    }

    public Cliente findByEmail(String email) throws ResourceNotFoundException {
        return this.clienteRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }

    public Cliente create(Cliente cliente) throws InvalidDataException {
        if (this.clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new InvalidDataException("E-mail já está cadastrado");
        }

        return this.clienteRepository.save(cliente);
    }

    public Cliente update(Integer codigo, Cliente cliente) throws ResourceNotFoundException, InvalidDataException {
        Cliente clienteCadastrado = this.clienteRepository.findById(codigo).orElseThrow(ResourceNotFoundException::new);

        if (this.clienteRepository.findByEmail(cliente.getEmail()).isPresent()
                && !cliente.getEmail().equals(clienteCadastrado.getEmail())) {
            throw new InvalidDataException("E-mail já está cadastrado");
        }

        cliente.setCodigo(codigo);
        return this.clienteRepository.save(cliente);
    }

    public void remove(Integer codigo) throws ResourceNotFoundException {
        this.clienteRepository.delete(this.verificarSeExiste(codigo));
    }

    private Cliente verificarSeExiste(Integer codigo) throws ResourceNotFoundException {
        return this.clienteRepository.findById(codigo).orElseThrow(ResourceNotFoundException::new);
    }

}
