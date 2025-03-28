import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco(); // Cria uma instância da classe Banco
        banco.setNome("Meu banco"); // Define o nome do banco
        List<Cliente> clientes = new ArrayList<>(); // Lista para armazenar os clientes cadastrados
        List<Conta> contas = new ArrayList<>(); // Lista para armazenar as contas criadas
        int opcao = -1; // Variável para armazenar a opção escolhida no menu

        // Cria um menu interativo para o usuário
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
            scanner.nextLine(); // Limpa o buffer do Scanner

            // Executa a operação correspondente à opção escolhida
            switch (opcao) {
                case 1 -> criarClienteEConta(scanner, clientes, contas);
                case 2 -> realizarDeposito(scanner, contas);
                case 3 -> realizarSaque(scanner, contas);
                case 4 -> realizarTransferencia(scanner, contas);
                case 5 -> imprimirExtrato(scanner, contas);
                case 6 -> listarContas(contas); // Apenas para fins de melhor experiência
                case 7 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida"); // Trata opções inválidas
            }
        } while (opcao != 0 && opcao != 7); // Continua o loop até que o usuário escolha sair
    }

    /**
     * Método para criar um cliente e associá-lo a uma conta.
     * O usuário informa o nome do cliente e escolhe o tipo de conta (corrente ou poupança).
     */
    private static void criarClienteEConta(Scanner scanner, List<Cliente> clientes, List<Conta> contas) {
        System.out.println("Digite o nome do cliente");
        String nome = scanner.next();

        Cliente cliente = new Cliente(); // Cria uma instância de Cliente
        cliente.setNome(nome); // Define o nome do cliente
        clientes.add(cliente); // Adiciona o cliente à lista de clientes

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
        contas.add(conta); // Adiciona a conta à lista de contas
        System.out.println("Conta criada com sucesso");
    }

    /**
     * Método para realizar um depósito em uma conta específica.
     * O usuário informa o número da conta e o valor a ser depositado.
     */
    private static void realizarDeposito(Scanner scanner, List<Conta> contas) {
        Conta conta = buscarContaPorNumero(scanner, contas); // Busca a conta pelo número
        if (conta == null) {
            System.out.println("Conta não encontrada");
        } else {
            System.out.println("Digite o valor a depositar:");
            double valor = scanner.nextDouble();
            conta.depositar(valor);
        }
    }

    /**
     * Método para realizar um saque em uma conta específica.
     * O usuário informa o número da conta e o valor a ser sacado.
     */
    private static void realizarSaque(Scanner scanner, List<Conta> contas) {
        Conta conta = buscarContaPorNumero(scanner, contas); // Busca a conta pelo número
        if (conta == null) {
            System.out.println("Conta não encontrada");
        } else {
            System.out.println("Digite o valor a sacar");
            double valor = scanner.nextDouble();
            conta.sacar(valor);
        }
    }

    /**
     * Método para realizar uma transferência entre duas contas.
     * O usuário informa o número da conta de origem, o número da conta de destino e o valor a ser transferido.
     */
    private static void realizarTransferencia(Scanner scanner, List<Conta> contas) {
        System.out.println("Digite o número da conta de origem: ");
        int numeroOrigem = scanner.nextInt();
        Conta contaOrigem = buscarContaPorNumero(numeroOrigem, contas); // Busca a conta de origem

        System.out.println("Digite o número da conta de destino: ");
        int numeroDestino = scanner.nextInt(); // Lê o número da conta de destino
        Conta contaDestino = buscarContaPorNumero(numeroDestino, contas); // Busca a conta de destino

        if (contaOrigem == null || contaDestino == null) {
            System.out.println("Conta origem ou destino não encontrada");
        } else {
            System.out.println("Digite o valor a transferir: ");
            double valor = scanner.nextDouble();
            contaOrigem.transferir(valor, contaDestino);
            System.out.println("Transferência realizada com sucesso");
        }
    }

    /**
     * Método para imprimir o extrato de uma conta específica.
     * O usuário informa o número da conta.
     */
    private static void imprimirExtrato(Scanner scanner, List<Conta> contas) {
        Conta conta = buscarContaPorNumero(scanner, contas); // Busca a conta pelo número
        if (conta == null) {
            System.out.println("Conta não encontrada");
        } else {
            conta.imprimirExtrato();
        }
    }

    /**
     * Método para listar todas as contas cadastradas.
     * Exibe informações como agência, número, titular e saldo de cada conta.
     */
    private static void listarContas(List<Conta> contas) {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada");
        } else {
            System.out.println("===== Lista de Contas =====");
            for (Conta conta : contas) {
                System.out.println("Agência: " + conta.getAgencia() + " | Número: " + conta.getNumero() +
                        " | Titular: " + conta.getCliente().getNome() + " | Saldo: R$" + conta.getSaldo());
            }
        }
    }

    /**
     * Método para buscar uma conta pelo número.
     * Recebe o número da conta e retorna a conta correspondente, ou null se não encontrar.
     */
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

    /**
     * Sobrecarga do método para buscar uma conta pelo número diretamente.
     * Útil para métodos que já possuem o número da conta.
     */
    private static Conta buscarContaPorNumero(int numero, List<Conta> contas) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }
        return null;
    }
}