package br.com.estoque.model;

import br.com.estoque.enums.TipoUsuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "item")
public class Item extends AbstractEntity {

    private String codigo;
    private String descricao;
    private String especificacao;
    private int quantidadeMinima;
    private String observacoes;


    @ManyToOne
    @JoinColumn(name = "classe", nullable = false)
    private Classe classe = new Classe();

    @OneToMany(mappedBy = "id.item", fetch = FetchType.EAGER)
    private List<Estoque> estoques = new ArrayList<>();

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
