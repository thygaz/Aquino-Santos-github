import javax.swing.JOptionPane;
public class Validador {


    public boolean validarPreco(double preco) {
        return preco > 0;
    }


    public boolean validarQuantidade(int quantidade) {
        return quantidade >= 0;
    }


    public boolean validarNomeBebida(String nome, BebidaDAO bebidaDAO) {
        Bebida bebida = bebidaDAO.buscarBebida(nome);
        return bebida == null; // Se a bebida for nula, o nome não existe
    }


    public boolean validarDadosBebida(String nome, double preco, int quantidade, BebidaDAO bebidaDAO) {
        if (nome.isEmpty() || nome.length() < 3) {
            JOptionPane.showMessageDialog(null, "O nome da bebida deve ter pelo menos 3 caracteres.");
            return false;
        }
        if (!validarPreco(preco)) {
            JOptionPane.showMessageDialog(null, "O preço deve ser maior que zero.");
            return false;
        }
        if (!validarQuantidade(quantidade)) {
            JOptionPane.showMessageDialog(null, "A quantidade não pode ser negativa.");
            return false;
        }
        if (!validarNomeBebida(nome, bebidaDAO)) {
            JOptionPane.showMessageDialog(null, "Já existe uma bebida com esse nome.");
            return false;
        }
        return true;
    }
}

