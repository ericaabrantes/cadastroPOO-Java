package cadastropoo;

import model.*;
import java.io.IOException;
import java.util.Scanner;

public class CadastroPOO {

    public static void main(String[] args) {
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
        Scanner scanner = new Scanner(System.in);
        
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("================================");
            System.out.print("Opção: ");
            
            opcao = scanner.nextInt();
            
            try {
                switch (opcao) {
                    case 1: // INCLUIR
                        System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                        String tipo1 = scanner.next().toUpperCase();
                        System.out.print("Digite o id da pessoa: ");
                        int id1 = scanner.nextInt();
                        System.out.println("Insira os dados...");
                        System.out.print("Nome: ");
                        String nome1 = scanner.next();
                        
                        if (tipo1.equals("F")) {
                            System.out.print("CPF: ");
                            String cpf = scanner.next();
                            System.out.print("Idade: ");
                            int idade = scanner.nextInt();
                            repoFisica.inserir(new PessoaFisica(id1, nome1, cpf, idade));
                        } else if (tipo1.equals("J")) {
                            System.out.print("CNPJ: ");
                            String cnpj = scanner.next();
                            repoJuridica.inserir(new PessoaJuridica(id1, nome1, cnpj));
                        }
                        break;

                    case 2: // ALTERAR
                        System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                        String tipo2 = scanner.next().toUpperCase();
                        System.out.print("Digite o id da pessoa: ");
                        int id2 = scanner.nextInt();
                        
                        if (tipo2.equals("F")) {
                            PessoaFisica p = repoFisica.obter(id2);
                            if (p != null) {
                                System.out.println("Dados atuais:"); p.exibir();
                                System.out.print("Novo Nome: "); String n = scanner.next();
                                System.out.print("Novo CPF: "); String c = scanner.next();
                                System.out.print("Nova Idade: "); int i = scanner.nextInt();
                                repoFisica.alterar(new PessoaFisica(id2, n, c, i));
                            }
                        } else if (tipo2.equals("J")) {
                            PessoaJuridica p = repoJuridica.obter(id2);
                            if (p != null) {
                                System.out.println("Dados atuais:"); p.exibir();
                                System.out.print("Novo Nome: "); String n = scanner.next();
                                System.out.print("Novo CNPJ: "); String c = scanner.next();
                                repoJuridica.alterar(new PessoaJuridica(id2, n, c));
                            }
                        }
                        break;

                    case 3: // EXCLUIR
                        System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                        String tipo3 = scanner.next().toUpperCase();
                        System.out.print("Digite o id da pessoa: ");
                        int id3 = scanner.nextInt();
                        if (tipo3.equals("F")) repoFisica.excluir(id3);
                        else if (tipo3.equals("J")) repoJuridica.excluir(id3);
                        break;

                    case 4: // BUSCAR
                        System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                        String tipo4 = scanner.next().toUpperCase();
                        System.out.print("Digite o id da pessoa: ");
                        int id4 = scanner.nextInt();
                        if (tipo4.equals("F")) {
                            PessoaFisica p = repoFisica.obter(id4);
                            if(p!=null) p.exibir();
                        } else if (tipo4.equals("J")) {
                            PessoaJuridica p = repoJuridica.obter(id4);
                            if(p!=null) p.exibir();
                        }
                        break;

                    case 5: // EXIBIR TODOS
                        System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                        String tipo5 = scanner.next().toUpperCase();
                        if (tipo5.equals("F")) for(PessoaFisica p : repoFisica.obterTodos()) { p.exibir(); System.out.println(); }
                        else if (tipo5.equals("J")) for(PessoaJuridica p : repoJuridica.obterTodos()) { p.exibir(); System.out.println(); }
                        break;

                    case 6: // SALVAR
                        System.out.print("Digite o prefixo dos arquivos: ");
                        String prefixoSalvar = scanner.next();
                        repoFisica.persistir(prefixoSalvar + ".fisica.bin");
                        repoJuridica.persistir(prefixoSalvar + ".juridica.bin");
                        System.out.println("Dados salvos.");
                        break;

                    case 7: // RECUPERAR
                        System.out.print("Digite o prefixo dos arquivos: ");
                        String prefixoRec = scanner.next();
                        repoFisica.recuperar(prefixoRec + ".fisica.bin");
                        repoJuridica.recuperar(prefixoRec + ".juridica.bin");
                        System.out.println("Dados recuperados.");
                        break;
                    
                    case 0:
                        System.out.println("Finalizando...");
                        break;
                        
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        scanner.close();
    }
}