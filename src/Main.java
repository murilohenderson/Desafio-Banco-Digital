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
            System.out.println("6 - Lista de contas");
            System.out.println("7 - Sair");
            System.out.println("Escolha uma opção");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criarClienteEConta(scanner, clientes, contas);
                case 2 -> realizarDeposito(scanner, contas);
                case 3 -> realizarSaque(scanner, contas);
                case 4 -> realizarTransferencia(scanner, contas);
                case 5 -> imprimirExtrato(scanner, contas);
                case 6 -> listarContas(contas);
                case 7 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                }
                default -> {
                    System.out.println("Opção inválida");
                }
            }
        } while (opcao != 0 && opcao != 7);
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
    private static void realizarDeposito(Scanner scanner, List<Conta> contas) {
        Conta conta = buscarContaPorNumero(scanner, contas);
        if (conta == null) {
            System.out.println("Conta não encontrada");
        } else {
            System.out.println("Digite o valor a depositar:");
            double valor = scanner.nextDouble();
            conta.depositar(valor);
        }
    }
    private static void realizarSaque(Scanner scanner, List<Conta> contas) {
        Conta conta = buscarContaPorNumero(scanner, contas);
        if (conta == null) {
            System.out.println("Conta não encontrada");
        } else {
            System.out.println("Digite o valor a sacar");
            double valor = scanner.nextDouble();
            conta.sacar(valor);
        }
    }
    private static void realizarTransferencia(Scanner scanner, List<Conta> contas) {
        System.out.println("Digite o número da conta de origem: ");
        int numeroOrigem = scanner.nextInt();
        Conta contaOrigem = buscarContaPorNumero(numeroOrigem, contas);

        System.out.println("Digite o número da conta de destino: ");
        int numeroDestino = scanner.nextInt();
        Conta contaDestino = buscarContaPorNumero(numeroDestino, contas);

        if (contaOrigem == null || contaDestino == null) {
            System.out.println("Conta origem ou destino não encontrada");
        } else {
            System.out.println("Digite o valor a transferir: ");
            double valor = scanner.nextDouble();
            contaOrigem.transferir(valor, contaDestino);
            System.out.println("Transferência realizada com sucesso");
        }
    }

    private static void imprimirExtrato(Scanner scanner, List<Conta> contas) {
        Conta conta = buscarContaPorNumero(scanner, contas);
        if (conta == null) {
            System.out.println("Conta não encontrada");
        } else {
            conta.imprimirExtrato();
        }
    }
    private static void listarContas(List<Conta> contas) {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada");
        } else {
            System.out.println("===== Lista de Contas =====");
            for (Conta conta : contas) {
                System.out.println("Agência: " + conta.getAgencia() + " | Número: " + conta.getNumero() + " | Titular: " + conta.getCliente().getNome() + " | Saldo: R$" + conta.getSaldo());
            }
        }
    }
    private static Conta buscarContaPorNumero(Scanner scanner, List<Conta> contas) {
        System.out.println("Digite o número da conta: ");
        int numero = scanner.nextInt();
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }
        return null;
    }
    private static Conta buscarContaPorNumero(int numero, List<Conta> contas) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }
        return null;
    }
}
