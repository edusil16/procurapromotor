package br.com.mprj;

public class Funcionario{
    String name;
    String cargo;
    String matricula;


    public void setName(String name) {
        this.name = name;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "\n"+"[Nome = " + name + ", Cargo = " + cargo + ", Matricula = " + matricula;
    }
}

