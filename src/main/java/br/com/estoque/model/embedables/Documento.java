package br.com.estoque.model.embedables;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Documento {


    private String nome;
    private String nomeFantasia;
    private String tel;
    private String cel;
    private String email;
    private String contato;
    private String cnpj;
    private String cpf;

    @Transient
    private boolean emEdicao = false;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato() {
        return contato;
    }

    public boolean isEmEdicao() {
        return emEdicao;
    }

    public void setEmEdicao(boolean emEdicao) {
        this.emEdicao = emEdicao;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
