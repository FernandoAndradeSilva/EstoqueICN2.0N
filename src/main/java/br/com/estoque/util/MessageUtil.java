package br.com.estoque.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



public class MessageUtil {

    public static final int INFO = 1;
    public static final int WARN = 2;
    public static final int ERROR = 3;
    public static final int FATAL = 4;


    public static final boolean REDIRECT = true;
    public static final boolean NOREDIRECT = false;

    // Adiciona MESSAGE BARRA
    public static void addMessage(String texto, int tipo , boolean redirect) {

        if(redirect == true) {
            if (FacesContext.getCurrentInstance().getExternalContext().getFlash().isKeepMessages() == false) {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            }
        }

        FacesMessage message = new FacesMessage();
        switch (tipo) {
            case 1:
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                break;
            case 2:
                message.setSeverity(FacesMessage.SEVERITY_WARN);
                break;
            case 3:
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                break;
            case 4:
                message.setSeverity(FacesMessage.SEVERITY_FATAL);

        }
        message.setDetail(texto);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //Adiciona MESSAGE TICKET
    public static void addMessageTicket(String texto, int tipo , boolean redirect) {

        if(redirect == true) {
            if (FacesContext.getCurrentInstance().getExternalContext().getFlash().isKeepMessages() == false) {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            }
        }

        FacesMessage message = new FacesMessage();
        switch (tipo) {
            case 1:
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                break;
            case 2:
                message.setSeverity(FacesMessage.SEVERITY_WARN);
                break;
            case 3:
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                break;
            case 4:
                message.setSeverity(FacesMessage.SEVERITY_FATAL);

        }
        message.setSummary(texto);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}