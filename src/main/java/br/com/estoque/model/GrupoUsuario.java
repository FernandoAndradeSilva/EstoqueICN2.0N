package br.com.estoque.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class GrupoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private GrupoUsuarioPK id = new GrupoUsuarioPK();


    public GrupoUsuarioPK getId() {
        return id;
    }

    public void setId(GrupoUsuarioPK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GrupoUsuario{" +
                "id=" + id +
                '}';
    }
}
