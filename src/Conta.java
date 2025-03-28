public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;

    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    private Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    @Override
    public void sacar(double valor) {
        if (valor > this.saldo) {
            System.out.println("Saldo insuficiente para saque");
        } else if (valor <= 0) {
            System.out.println("Valor inválido para saque");
        } else {
            this.saldo -= valor;
            System.out.println("Saque de " + valor + " realizado com sucesso.");
        }
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido para depósito");
            return;
        }
        this.saldo += valor;
        System.out.println("Depósito realizado com sucesso");
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (contaDestino == null || contaDestino.getNumero() <= 0) {
            System.out.println("Conta destino inválida");
            return;
        }
        if (valor > this.saldo) {
            System.out.println("Saldo insuficiente para transferência");
            return;
        }
        if (valor <= 0) {
            System.out.println("Valor inválido para transferência");
            return;
        }
        this.sacar(valor);
        contaDestino.depositar(valor);
        System.out.println("Transferência realizada com sucesso");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getAgencia() {
        return agencia;
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }
}