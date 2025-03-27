import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco();
        banco.setNome("Meu banco");
        List<Cliente> clientes = new ArrayList<>();
        List<Conta> contas = new ArrayList<>();
        int opcao = -1;

        do {
            System.out.println("===== MENU =====");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Imprimir extrato");
            System.out.println("6 - Sair");
            System.out.println("Escolha uma opção");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                        criarClienteEConta(scanner, clientes, contas);
                    break;
                }
                case 6 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                }
                default -> {
                    System.out.println("Opção inválida");
                }
            }
        } while (opcao <= 0);







        /*
        Cliente murilo = new Cliente();

        Conta contaCorrente = new ContaCorrente(murilo);
        Conta contaPoupanca = new ContaPoupanca(murilo);
        murilo.setNome("Murilo");
        contaCorrente.depositar(10000);
        contaCorrente.transferir(3400, contaPoupanca);
        contaPoupanca.imprimirExtrato();
        contaCorrente.imprimirExtrato();
       */
    }

    private static void criarClienteEConta(Scanner scanner, List<Cliente> clientes, List<Conta> contas) {
        System.out.println("Digite o nome do cliente");
        String nome = scanner.next();

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        clientes.add(cliente);

        System.out.println("Escolha o tipo de conta: ");
        System.out.println("1 - Conta corrente");
        System.out.println("2 - Conta poupança");
        int tipoConta = scanner.nextInt();

        Conta conta;
        if (tipoConta == 1) {
            conta = new ContaCorrente(cliente);
        } else if (tipoConta == 2) {
            conta = new ContaPoupanca(cliente);
        } else {
            System.out.println("Tipo de conta inválido");
            return;
        }
        contas.add(conta);
        System.out.println("Conta criada com sucesso");
    }
    }
