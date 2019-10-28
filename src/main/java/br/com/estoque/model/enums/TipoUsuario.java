package br.com.estoque.model.enums;

public enum TipoUsuario {

    ADMIN("Admin"),
    COLABORADOR("Colaborador"),
    DIRETORIA("Diretoria"),
    MASTER("Master"),
    VISITANTE("Visitante");


    private String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }


}
