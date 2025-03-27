import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco();
        banco.setNome("Meu banco");
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
}
