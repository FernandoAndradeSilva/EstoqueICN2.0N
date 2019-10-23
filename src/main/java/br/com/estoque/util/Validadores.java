package br.com.estoque.util;

import br.com.estoque.model.Item;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Named
@ApplicationScoped
public class Validadores {

    public void validaEmail(FacesContext context, UIComponent toValidate, Object value) {

            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(value.toString());

            if (!matcher.matches()) {
                FacesMessage msg = new FacesMessage("Preencha com um 'E-MAIL' válido", "Preencha com um 'E-MAIL' válido");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
    }


}
