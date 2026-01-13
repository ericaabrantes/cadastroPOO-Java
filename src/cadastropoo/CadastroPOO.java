package cadastropoo; // Verifique se o nome do seu pacote principal é esse mesmo

import model.*; // Importa suas classes do pacote model
import java.io.IOException;

public class CadastroPOO {

    public static void main(String[] args) {
        try {
            System.out.println("--- Teste Pessoa Física ---");
            PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
            repo1.inserir(new PessoaFisica(1, "Ana", "111.111.111-11", 25));
            repo1.inserir(new PessoaFisica(2, "Carlos", "222.222.222-22", 52));

            repo1.persistir("pessoas-fisicas.bin");
            System.out.println("Dados de Pessoa Fisica Armazenados.");

            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
            repo2.recuperar("pessoas-fisicas.bin");
            System.out.println("Dados de Pessoa Fisica Recuperados.");

            for (PessoaFisica p : repo2.obterTodos()) {
                p.exibir();
                System.out.println(); // Pula linha
            }

            System.out.println("--- Teste Pessoa Jurídica ---");

            PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
            repo3.inserir(new PessoaJuridica(3, "XPTO Sales", "33.333.333/0001-33"));
            repo3.inserir(new PessoaJuridica(4, "XPTO Solutions", "44.444.444/0001-44"));


            repo3.persistir("pessoas-juridicas.bin");
            System.out.println("Dados de Pessoa Juridica Armazenados.");


            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
            repo4.recuperar("pessoas-juridicas.bin");
            System.out.println("Dados de Pessoa Juridica Recuperados.");


            for (PessoaJuridica p : repo4.obterTodos()) {
                p.exibir();
                System.out.println();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}