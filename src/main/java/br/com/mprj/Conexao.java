package br.com.mprj;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static String url = "jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=on)(ADDRESS=(PROTOCOL=TCP)" +
            "(HOST=host-server) (PORT=1521))(CONNECT_DATA=(SERVICE_NAME=nome_banco)))";
    private static String password = "TJRJ_webservice_des";
    private static String user = "TJRJ_webservice_des";
    private static Connection connection = null;

    private static void conectar() {
        try {

            if(connection == null) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
//                System.out.println("Conectou com sucesso");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public Conexao() {
        conectar();
    }


    public static Connection getConnection() {
        return connection;
    }

    static  {
        conectar();
    }
}
