package br.com.fiap.nac01.controller;

import br.com.fiap.nac01.exception.InvalidDataException;
import br.com.fiap.nac01.exception.ResourceNotFoundException;
import br.com.fiap.nac01.model.Cliente;
import br.com.fiap.nac01.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        return new ResponseEntity<>(this.clienteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<Cliente> findByCodigo(@PathVariable("codigo") final Integer codigo) {
        try {
            return new ResponseEntity<>(this.clienteService.findByCodigo(codigo), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity("Codigo não encontrado", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("email/{email}")
    public ResponseEntity<Cliente> findByEmail(@PathVariable("email") final String email) {
        try {
            return new ResponseEntity<>(this.clienteService.findByEmail(email), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity("E-mail não encontrado", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody @Valid final Cliente cliente) {
        try {
            return new ResponseEntity<>(this.clienteService.create(cliente), HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{codigo}")
    public ResponseEntity<Cliente> update(@PathVariable("codigo") final Integer codigo, @RequestBody @Valid final Cliente cliente) {
        try {
            return new ResponseEntity<>(this.clienteService.update(codigo, cliente), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity("Codigo não encontrado", HttpStatus.BAD_REQUEST);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity delete(@PathVariable("codigo") final Integer codigo) {
        try {
            this.clienteService.remove(codigo);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity("Codigo não encontrado", HttpStatus.BAD_REQUEST);
        }
    }

}
