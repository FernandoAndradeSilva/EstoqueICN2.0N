package br.com.estoque.model;

import br.com.estoque.enums.TipoUsuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "id.item")
    private Set<Estoque> estoques = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "unidadeMedida" , nullable = false)
    private UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();

    @ManyToOne
    @JoinColumn(name = "marca" , nullable = true)
    private Marca marca = new Marca();

    public void configuraCodigo(String cod) {
        this.codigo += cod;
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

    public UnidadeDeMedida getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public Set<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(Set<Estoque> estoques) {
        this.estoques = estoques;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Item{" +
                "codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", especificacao='" + especificacao + '\'' +
                '}';
    }
}
