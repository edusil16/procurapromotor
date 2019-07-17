package br.com.mprj;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PedeDados {

    Scanner ler;

    public PedeDados() {
        ler = new Scanner(System.in);
    }

    public String validarString(String nome) throws Exception {
        if(nome.equals("") || nome == null){
            throw new Exception("Nome não pode ser vazio");
        }else{
            Pattern pattern = Pattern.compile("[0-9]");
            Matcher matcher = pattern.matcher(nome);
            if(matcher.find()){
                System.out.println("Não deve conter números!");
                pedirDadosFuncionario();
            }
        }
        return nome;
    }

    public ArrayList<Funcionario> pedirDadosFuncionario() throws SQLException {
        System.out.println("Digite o nome da busca:");
        String nome = ler.nextLine();
        try {
            validarString(nome);
        }catch (Exception e){
            pedirDadosFuncionario();
        }
        return buscarFuncionario(nome);
    }

    public ArrayList<Promotoria> pedirNomePromotoria() throws SQLException {
        System.out.println("Digite o primeiro nome da promotoria para busca:");
        String primeiroNome = ler.nextLine();
        System.out.println("Digite o segundo nome da promotoria para busca:");
        String segundoNome = ler.nextLine();
        return buscarPromotoria(primeiroNome, segundoNome);
    }


    // Método usa o nome do funcionário da busca como parâmetro, e retorna uma lista com os dados do BD.
    public ArrayList<Funcionario> buscarFuncionario(String nome) throws SQLException {

        Conexao conection = new Conexao();
        ArrayList<Funcionario> list = new ArrayList<>();


        String sql = "select *\n" +
                "from MPRJ.MPRJ_VW_FUNCIONARIO vf\n" +
                "where VF.CARGO in ('PROMOTOR','PROCURADOR') and VF.CDSITUACAOFUNC = '1'\n" +
                "and \n" +
                "regexp_like(\n" +
                "   translate (upper(VF.NMFUNCIONARIO ),'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕÜÇÖÑ','AEIOUAEIOUAEIOUAOUCON') ,\n" +
                "   translate (upper('%s' ),'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕÜÇÖÑ','AEIOUAEIOUAEIOUAOUCON')\n" +
                ") ORDER BY VF.NMFUNCIONARIO ASC";
        sql = String.format(sql, nome);


        PreparedStatement stmt = conection.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Funcionario retornaBusca = new Funcionario();
            retornaBusca.setName(rs.getString("NMFUNCIONARIO"));
            retornaBusca.setCargo(rs.getString("CARGO"));
            retornaBusca.setMatricula(rs.getString("CDMATRICULA"));

            list.add(retornaBusca);
        }
        return list;
    }

    // Método usa o nome da promotoria para buscar e retornar uma lista com o nome das promotorias.
    public ArrayList<Promotoria> buscarPromotoria(String primeiroNome, String segundoNome) throws SQLException {

        Conexao conection = new Conexao();
        ArrayList<Promotoria> list = new ArrayList<>();

        String sql = "SELECT OO.ORGI_NM_ORGAO,OO.ORGI_DK,OO.ORGI_DT_FIM\n" +
                "FROM ORGI.ORGI_ORGAO oo\n" +
                "WHERE 1=1\n" +
                "and OO.ORGI_TPOR_DK IN (1,3)\n" +
                "and    translate (upper(OO.ORGI_NM_ORGAO ),'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕÜÇÖÑ','AEIOUAEIOUAEIOUAOUCON')  like \n" +
                "           '%'||translate (upper('" + primeiroNome + "'),'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕÜÇÖÑ','AEIOUAEIOUAEIOUAOUCON')||'%'\n" +
                "and    translate (upper(OO.ORGI_NM_ORGAO ),'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕÜÇÖÑ','AEIOUAEIOUAEIOUAOUCON')  LIKE\n" +
                "           '%'||translate (upper('" + segundoNome + "'),'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕÜÇÖÑ','AEIOUAEIOUAEIOUAOUCON')||'%'"
                + "ORDER BY OO.ORGI_NM_ORGAO ASC";


        PreparedStatement stmt = conection.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Promotoria retornaBusca = new Promotoria();
            retornaBusca.setNomePromotoria(rs.getString("ORGI_NM_ORGAO"));
            list.add(retornaBusca);
        }
        return list;
    }
}
