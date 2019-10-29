package br.com.estoque.model.enums;

public enum TipoFornecedor {

    PessoaFisica("Pessoa-Física"),
    PessoaJuridica("Pessoa-Juridica"),
    Outros("Outros");


    private String descricao;

    TipoFornecedor(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoFornecedor() {
        return descricao;
    }


}
