package br.com.estoque.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "unidade")
public class Unidade extends AbstractEntity {

    private String nome;
    private String estado;
    private String cidade;

    @OneToMany( mappedBy = "unidade", fetch = FetchType.EAGER)
    private List<Setor> setores = new ArrayList<>();

    @OneToMany(mappedBy = "id.unidade")
    private List<Estoque> estoques = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<Setor> getSetores() {
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }


    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

    @Override
    public String toString() {
        return nome ;
    }
}
