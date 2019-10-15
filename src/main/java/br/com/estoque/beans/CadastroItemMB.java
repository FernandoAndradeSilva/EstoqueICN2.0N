package br.com.estoque.beans;

import br.com.estoque.service.*;

import javax.inject.Inject;
import java.io.Serializable;

public class CadastroItemMB implements Serializable {

    private static final long serialVersionUID = 1L;


    //Injects//
    @Inject
    private ItemService itemService;
    @Inject
    private EstoqueService estoqueService;
    @Inject
    private UnidadeDeMedidaService unidadeDeMedidaService;
    @Inject
    private GrupoService grupoService;
    @Inject
    private ClasseService classeService;
    //fim Injects//


    //Cadastro de Grupos//


    //fin Cadastro de Grupos//

}
