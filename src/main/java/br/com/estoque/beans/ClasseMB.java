package br.com.estoque.beans;



import br.com.estoque.model.Classe;
import br.com.estoque.service.ClasseService;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


@Named
@ViewScoped
public class ClasseMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ClasseService classeService;

    private Classe novaClasse = new Classe();


    public void salvarNovoClasse() {
        classeService.salvar(novaClasse);

    }


    public Classe getNovoClasse() {
        return novaClasse;
    }

    public void setNovoClasse(Classe novaClasse) {
        this.novaClasse = novaClasse;
    }
}

