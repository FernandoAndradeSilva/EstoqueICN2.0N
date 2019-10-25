package br.com.estoque.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "movimentacao")
public class Movimentacao extends AbstractEntity {

    public static final int ENTRADA = 1;
    public static final int SAIDA = 2;

    private Date data;
    private String notaFiscal;
    private String cupomFiscal;
    private String observacoes;
    private int quantidade;
    private int tipo;
    private int rm;
    private boolean status = true;
    private boolean ajusteDeEstoque = false;
    private float valor;

    @ManyToOne
    @JoinColumn(name="usuario",  nullable= false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="item",  nullable= true)
    @Cascade(CascadeType.ALL)
    private Item item = new Item();

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public String getCupomFiscal() {
        return cupomFiscal;
    }

    public void setCupomFiscal(String cupomFiscal) {
        this.cupomFiscal = cupomFiscal;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getRm() {
        return rm;
    }

    public void setRm(int rm) {
        this.rm = rm;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isAjusteDeEstoque() {
        return ajusteDeEstoque;
    }

    public void setAjusteDeEstoque(boolean ajusteDeEstoque) {
        this.ajusteDeEstoque = ajusteDeEstoque;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
