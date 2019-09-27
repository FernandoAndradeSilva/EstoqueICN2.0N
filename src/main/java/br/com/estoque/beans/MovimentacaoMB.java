package br.com.estoque.beans;

import br.com.estoque.model.Usuario;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;


@Named
@SessionScoped
public class MovimentacaoMB implements Serializable {

    private static final long serialVersionUID = 1L;


    public String imprimeEntrada() {
        System.out.println("Entradas");
        return "Entradas";
    }

    public String imprimeSaida() {
        System.out.println("Saidas");
        return "Saidas";
    }

    public String imprimeEstorno() {
        System.out.println("Estornos");
        return "Estornos";
    }
}
