package br.com.mprj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ListaBusca {

    Scanner ler = new Scanner(System.in);
    PedeDados pedeDados = new PedeDados();

    // Inicia os métodos buscar separadamente de acordo com a chamada.
    public void funcionarios() {
        try {
            ArrayList<Funcionario> funcionarios = pedeDados.pedirDadosFuncionario();
            if (funcionarios != null && funcionarios.size() > 0) {
                System.out.println("Funcionario Encontrado = " + funcionarios);
                if(funcionarios.size() > 1){
                    funcionarios();
                }
            } else {
                System.out.println("Funcionário não encontrado! Digite Novamente.");
                funcionarios();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void promotorias() {
        try {
            ArrayList<Promotoria> promotorias = pedeDados.pedirNomePromotoria();
            if (promotorias != null && promotorias.size() > 0) {
                System.out.println("Promotoria encontradas = " + promotorias);
            } else {
                System.out.println("Promotoria não encontrada! Digite Novamente.");
                promotorias();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}