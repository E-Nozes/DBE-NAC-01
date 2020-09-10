package br.com.fiap.nac01.controller;

import br.com.fiap.nac01.exception.InvalidDataException;
import br.com.fiap.nac01.exception.ResourceNotFoundException;
import br.com.fiap.nac01.model.Aluguel;
import br.com.fiap.nac01.service.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("alugueis")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    public ResponseEntity<List<Aluguel>> findAll() {
        return new ResponseEntity<>(this.aluguelService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<Aluguel> findByCodigo(@PathVariable("codigo") final Integer codigo) {
        try {
            return new ResponseEntity<>(this.aluguelService.findByCodigo(codigo), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity("Código não encontrado", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("cliente/{codigo}")
    public ResponseEntity<List<Aluguel>> findAllByClienteCodigo(@PathVariable("codigo") final Integer codigo) {
        return new ResponseEntity<>(this.aluguelService.findAllByClienteCodigo(codigo), HttpStatus.OK);
    }

    @GetMapping("preco/{preco}/cliente/{codigo}")
    public ResponseEntity<List<Aluguel>> findAllByPrecoAndClienteCodigo(@PathVariable("preco") final Double preco, @PathVariable("codigo") final Integer codigo) {
        return new ResponseEntity<>(this.aluguelService.findAllByPrecoAndClienteCodigo(preco, codigo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Aluguel> create(@RequestBody @Valid final Aluguel aluguel) {
        try {
            return new ResponseEntity<>(this.aluguelService.create(aluguel), HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{codigo}")
    public ResponseEntity<Aluguel> update(@PathVariable("codigo") final Integer codigo, @RequestBody @Valid final Aluguel aluguel) {
        try {
            return new ResponseEntity<>(this.aluguelService.update(codigo, aluguel), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity("Código não encontrado", HttpStatus.BAD_REQUEST);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity delete(@PathVariable("codigo") final Integer codigo) {
        try {
            this.aluguelService.remove(codigo);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity("Código não encontrado", HttpStatus.BAD_REQUEST);
        }
    }

}
