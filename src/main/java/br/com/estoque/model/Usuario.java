package br.com.estoque.model;

import br.com.estoque.enums.TipoUsuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "usuario")
public class Usuario extends AbstractEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique=true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String usuario;

    private boolean ativo = false;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;



    public Usuario() {
    }



    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }



    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


}
