package br.com.estoque.model;

import br.com.estoque.model.enums.TipoUsuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "usuario")
public class Usuario extends AbstractEntity {


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

    @ManyToOne
    @JoinColumn(name = "setor" , nullable = false)
    private Setor setor = new Setor();

    @OneToMany(mappedBy = "id.grupo", fetch = FetchType.EAGER)
    private List<GrupoUsuario> grupos = new ArrayList<>();

    public Usuario() {
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    public List<GrupoUsuario> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoUsuario> grupos) {
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
