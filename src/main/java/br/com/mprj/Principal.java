package br.com.mprj;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        int op = 1;

        do {
            ListaBusca listaBusca = new ListaBusca();

            System.out.println("Começe a busca pelo Funcionário");
            listaBusca.funcionarios();

            System.out.println("Agora digite o nome da Procuradoria ou Promotoria...");
            listaBusca.promotorias();

            System.out.println("Deseja refazer a Busca? 1-NÃO 2-SIM");
            op = ler.nextInt();

        } while (op != 1);
    }
}

