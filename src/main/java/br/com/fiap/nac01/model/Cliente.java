package br.com.fiap.nac01.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "cliente", sequenceName = "SQ_TB_CLIENTE", allocationSize = 1)
public class Cliente {

    @Id
    @GeneratedValue(generator = "cliente", strategy = GenerationType.SEQUENCE)
    private int codigo;

    @Size(max = 60)
    @NotEmpty
    @Column(length = 60, nullable = false)
    private String nome;

    private LocalDate dataNascimento;

    @Email
    @NotEmpty
    @Size(max = 120)
    @Column(length = 120, nullable = false)
    private String email;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return getCodigo() == cliente.getCodigo() &&
                getEmail().equals(cliente.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo(), getEmail());
    }

}
