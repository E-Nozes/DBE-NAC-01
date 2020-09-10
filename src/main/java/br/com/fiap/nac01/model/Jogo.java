package br.com.fiap.nac01.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "jogo", sequenceName = "SQ_TB_JOGO", allocationSize = 1)
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jogo")
    private int codigo;

    @Size(max = 120)
    @NotEmpty
    @Column(length = 120, nullable = false, unique = true)
    private String descricao;

    @Size(max = 80)
    @NotEmpty
    @Column(length = 80, nullable = false)
    private String desenvolvedora;

    @NotNull
    @Column(nullable = false)
    private LocalDate anoLancamento;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public LocalDate getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(LocalDate anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", desenvolvedora='" + desenvolvedora + '\'' +
                ", anoLancamento=" + anoLancamento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jogo)) return false;
        Jogo jogo = (Jogo) o;
        return getCodigo() == jogo.getCodigo() &&
                getDescricao().equals(jogo.getDescricao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo(), getDescricao());
    }

}
