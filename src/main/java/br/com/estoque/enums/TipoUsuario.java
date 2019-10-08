package br.com.estoque.enums;

public enum TipoUsuario {

    MASTER( "Master"),
    ADMIN( "Admin"),
    COLABORADOR( "Colaborador"),
    VISITANTE( "Visitante"),
    DIRETORIA( "Diretoria");


    private String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }



}
