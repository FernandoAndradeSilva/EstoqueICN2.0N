package br.com.estoque.model;

import br.com.estoque.util.MessageUtil;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Estoque implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int ENTRADA = 1;
    public static final int SAIDA = 2;


    @EmbeddedId
    private EstoquePK id = new EstoquePK();

    private int entradas;
    private int saidas;
    private int saldo;
    private float custo;

    public Estoque(Item item, Unidade unidade) {
        this.id.setItem(item);
        this.id.setUnidade(unidade);
    }

    public Estoque() {
    }

    public void atualizaSaldo(int quantidade, int tipo) {

        if (tipo == 1) {

            entradas += quantidade;
            saldo += quantidade;
            MessageUtil.addMessageTicket("Movimentação realizada", MessageUtil.INFO, MessageUtil.NOREDIRECT);

        } else if (tipo == 2) {
            if (quantidade > saldo) {
                MessageUtil.addMessageTicket("Saldo insuficiente", MessageUtil.ERROR, MessageUtil.NOREDIRECT);
            } else {
                saidas += quantidade;
                saldo -= quantidade;
                MessageUtil.addMessageTicket("Movimentação realizada", MessageUtil.INFO, MessageUtil.NOREDIRECT);
            }
        }
    }


    @Override
    public String toString() {
        return "Estoque{" +
                "id=" + id +
                '}';
    }


    public EstoquePK getId() {
        return id;
    }

    public void setId(EstoquePK id) {
        this.id = id;
    }


    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public int getSaidas() {
        return saidas;
    }

    public void setSaidas(int saidas) {
        this.saidas = saidas;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }


}
