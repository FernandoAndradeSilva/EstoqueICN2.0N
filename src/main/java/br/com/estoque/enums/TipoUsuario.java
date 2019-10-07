package br.com.estoque.enums;

public enum TipoUsuario {

    MASTER(1 , "Pessoa Física"),
    ADMIN(2 , "Pessoa Jurídica"),
    COLABORADOR(3 , "Pessoa Física"),
    VISITANTE(4 , "Pessoa Física"),
    DIRETORIA(5 , "Pessoa Física");

    private int cod;
    private String descricao;

    private TipoUsuario(int cod , String descricao) {

    }

    public int getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static TipoUsuario toEnum(Integer cod) {

        if(cod == null) {
            return null;
        }

        for(TipoUsuario tc : TipoUsuario.values()) {
            if(cod.equals(tc.getCod())) {
                return tc;
            }
        }
        throw new IllegalArgumentException("Id inválido" + cod);
    }


}
