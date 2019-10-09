package br.com.estoque.model;

import br.com.estoque.enums.TipoUsuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name= "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private List<Grupo> grupos = new ArrayList<>();

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

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
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
