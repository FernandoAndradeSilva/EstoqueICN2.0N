package br.com.estoque.model.embedables;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Endereco {

    private int numero;
    private String logradouro;
    private String complemento;
    private String brairro;
    private String localidade;
    private String UF;
    private String cep;

    @Transient
    private boolean emEdicao = false;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBrairro() {
        return brairro;
    }

    public void setBrairro(String brairro) {
        this.brairro = brairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isEmEdicao() {
        return emEdicao;
    }

    public void setEmEdicao(boolean emEdicao) {
        this.emEdicao = emEdicao;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
