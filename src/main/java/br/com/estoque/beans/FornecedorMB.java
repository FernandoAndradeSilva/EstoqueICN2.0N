package br.com.estoque.beans;


import br.com.estoque.model.Classe;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Grupo;
import br.com.estoque.model.enums.TipoFornecedor;
import br.com.estoque.service.ClasseService;
import br.com.estoque.service.FornecedorService;
import br.com.estoque.service.GrupoService;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;
import org.primefaces.event.RowEditEvent;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@ViewScoped
public class FornecedorMB implements Serializable {

    private static final long serialVersionUID = 1L;

    //--------------------------INJECTS----------------------------//

    @Inject
    private FornecedorService fornecedorService;



    //--------------------------------------------------------------//



    //--------------------------MODELS-----------------------------//

    private Fornecedor fornecedor = new Fornecedor();

    private List<Fornecedor> fornecedores = new ArrayList<>();

    //--------------------------------------------------------------//


    //----------------------OUTROS ATRIBUTOS------------------------//




    //--------------------------------------------------------------//



    //--------------------------MÉTODOS---------------------------//


    @Transacional
    public void atualizar() {
        this.limpaEdicao(this.fornecedor);
        fornecedorService.salvar(fornecedor);
        MessageUtil.addMessageTicket("Informações atualizados" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
    }

    @Transacional
    public void salva() {
        fornecedorService.salvar(fornecedor);
        MessageUtil.addMessageTicket("Salvo com sucessos " , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.fornecedor = new Fornecedor();
    }


    @Transacional
    public void excluir() {
        fornecedorService.excluir(fornecedor);
        MessageUtil.addMessageTicket("Removido com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.fornecedor = new Fornecedor();
        this.carregaFornecedores();
    }

    @Transacional
    public void editarRow(RowEditEvent event) {
        fornecedorService.salvar((Fornecedor) event.getObject());
        MessageUtil.addMessageTicket("Informações atualizados" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
}


    public void limpaEdicao(Fornecedor fornecedor) {
        fornecedor.getDocumento().setEmEdicao(false);
        fornecedor.getEndereco().setEmEdicao(false);

    }

    public void selecionaTipoFornecedor(int num) {
            if (num == 1) {
                this.fornecedor.setTipoFornecedor(TipoFornecedor.PessoaJuridica);
            } else if (num == 2) {
                this.fornecedor.setTipoFornecedor(TipoFornecedor.PessoaFisica);
            } else if (num == 3) {
                this.fornecedor.setTipoFornecedor(TipoFornecedor.Outros);
            }
    }

    public void carregaFornecedores() {
        this.fornecedores = fornecedorService.listar();
    }

    public void ativaEdicaoEndereco(Fornecedor fornecedor) {
        fornecedor.getEndereco().setEmEdicao(true);
    }

    public void ativaEdicaoDocumentos(Fornecedor fornecedor) {
        fornecedor.getDocumento().setEmEdicao(true);
    }


    //--------------------------------------------------------------//



    //---------------------GETTERS E SETTERS------------------------//


    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
}
