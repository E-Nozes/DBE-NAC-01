package br.com.fiap.nac01.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "aluguel", sequenceName = "SQ_TB_ALUGUEL", allocationSize = 1)
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aluguel")
    private int codigo;

    @NotNull
    @Column(nullable = false)
    private LocalDate dataAluguel;

    @NotNull
    @Column(nullable = false)
    private LocalDate dataDevolucao;

    @NotNull
    @Column(nullable = false)
    private Cliente cliente;

    @NotNull
    @Column(nullable = false)
    private Jogo jogo;

    @NotNull
    private Double preco;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Aluguel{" +
                "codigo=" + codigo +
                ", dataAluguel=" + dataAluguel +
                ", dataDevolucao=" + dataDevolucao +
                ", cliente=" + cliente +
                ", jogo=" + jogo +
                ", preco=" + preco +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aluguel)) return false;
        Aluguel aluguel = (Aluguel) o;
        return getCodigo() == aluguel.getCodigo() &&
                getCliente().equals(aluguel.getCliente()) &&
                getJogo().equals(aluguel.getJogo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo(), getCliente(), getJogo());
    }

}
