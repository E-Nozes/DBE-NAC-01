package br.com.fiap.nac01.controller;

import br.com.fiap.nac01.exception.InvalidDataException;
import br.com.fiap.nac01.exception.ResourceNotFoundException;
import br.com.fiap.nac01.model.Jogo;
import br.com.fiap.nac01.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("jogos")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @GetMapping
    public ResponseEntity<List<Jogo>> findAll() {
        return new ResponseEntity<>(this.jogoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<Jogo> findByCodigo(@PathVariable("codigo") final Integer codigo) {
        try {
            return new ResponseEntity<>(this.jogoService.findByCodigo(codigo), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity("Código não encontrado", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("descricao/{descricao}")
    public ResponseEntity<Jogo> findByDescricao(@PathVariable("descricao") final String descricao) {
        try {
            return new ResponseEntity<>(this.jogoService.findByDescricao(descricao), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity("Jogo não encontrado", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("anoLancamento/{anoLancamento}")
    public ResponseEntity<List<Jogo>> findByAnoLancamento(@PathVariable("anoLancamento") final String anoLancamento) {
        return new ResponseEntity<>(this.jogoService.findByAnoLancamento(anoLancamento), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Jogo> create(@RequestBody @Valid final Jogo jogo) {
        try {
            return new ResponseEntity<>(this.jogoService.create(jogo), HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{codigo}")
    public ResponseEntity<Jogo> update(@PathVariable("codigo") final Integer codigo, @RequestBody @Valid final Jogo jogo) {
        try {
            return new ResponseEntity<>(this.jogoService.update(codigo, jogo), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity("Código não encontrado", HttpStatus.BAD_REQUEST);
        } catch (InvalidDataException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity delete(@PathVariable("codigo") final Integer codigo) {
        try {
            this.jogoService.remove(codigo);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity("Código não encontrado", HttpStatus.BAD_REQUEST);
        }
    }

}
