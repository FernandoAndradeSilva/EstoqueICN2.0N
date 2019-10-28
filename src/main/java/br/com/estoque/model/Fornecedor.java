package br.com.estoque.model;

import br.com.estoque.model.embedables.Documento;
import br.com.estoque.model.embedables.Endereco;
import br.com.estoque.model.enums.TipoFornecedor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "fornecedor")
public class Fornecedor extends AbstractEntity{


    @Enumerated(EnumType.STRING)
    private TipoFornecedor tipoFornecedor;

    @Embedded
    private Documento documento = new Documento();

    @Embedded
    private Endereco endereco = new Endereco();

    private String observacao;

    public TipoFornecedor getTipoFornecedor() {
        return tipoFornecedor;
    }

    public void setTipoFornecedor(TipoFornecedor tipoFornecedor) {
        this.tipoFornecedor = tipoFornecedor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}