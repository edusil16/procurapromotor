package br.com.mprj;

public class Promotoria {
    String nomePromotoria;

    public void setNomePromotoria(String nomePromotoria) {
        this.nomePromotoria = nomePromotoria;
    }

    @Override
    public String toString() {
        return "\n" + "[ Nome = " + nomePromotoria;
    }
}
