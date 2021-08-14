
package br.hilab.domain;

import java.time.LocalDate;

public class Usuario {
    private int id;
    private String nome;
    private LocalDate dataNasc;
    private String email;
    private Categoria categoria;

    public Usuario(int id, String nome, LocalDate dataNasc, String email, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.email = email;
        this.categoria = categoria;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
}
