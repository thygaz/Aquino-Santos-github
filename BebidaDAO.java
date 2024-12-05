
import java.util.ArrayList;
import java.util.List;
public class BebidaDAO{

    private List<Bebida> bebidas;

    public BebidaDAO() {
        bebidas = new ArrayList<>();
    }


    public void adicionarBebida(Bebida bebida) {
        bebidas.add(bebida);
    }


    public List<Bebida> listarBebidas() {
        return bebidas;
    }


    public Bebida buscarBebida(String nome) {
        for (Bebida bebida : bebidas) {
            if (bebida.getNome().equals(nome)) {
                return bebida;
            }
        }
        return null;
    }


    public double calcularValorTotalEstoque() {
        double valorTotal = 0;
        for (Bebida bebida : bebidas) {
            valorTotal += bebida.calcularValorTotal();
        }
        return valorTotal;
    }


    public void excluirBebida(String nome) {
        Bebida bebida = buscarBebida(nome);
        if (bebida != null) {
            bebidas.remove(bebida);
        }
    }


    public void atualizarBebida(Bebida bebidaAtualizada) {
        for (int i = 0; i < bebidas.size(); i++) {
            Bebida bebida = bebidas.get(i);
            if (bebida.getNome().equals(bebidaAtualizada.getNome())) {

                bebidas.set(i, bebidaAtualizada);
                break;
            }
        }
    }
}

