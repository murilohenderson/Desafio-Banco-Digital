import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String nome;
    private List<Conta> contas = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }
    public void listarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println("Cliente: " + cliente.getNome());
        }
    }
    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
    }
    public void listarContas() {
        for (Conta conta : contas) {
            System.out.println("Agência: " + conta.getAgencia() + " | Número: " + conta.getNumero() + " | Titular: " + conta.getCliente());
        }
    }
    public Conta buscarContaPorNumero(int numero) {
        for(Conta conta : contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }
        return null;
    }
}
