
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Menu {
    private BebidaDAO bebidaDAO;

    public Menu(BebidaDAO bebidaDAO, UsuarioDAO usuarioDAO) {
        this.bebidaDAO = bebidaDAO;

        JFrame frame = new JFrame("Deposito Unidrink´s");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JLabel titleLabel = new JLabel("Gestão de Bebidas");
        titleLabel.setBounds(150, 20, 200, 30);
        panel.add(titleLabel);

        JButton btnAdicionar = new JButton("Adicionar Bebida");
        btnAdicionar.setBounds(100, 60, 195, 30);
        panel.add(btnAdicionar);

        JButton btnListar = new JButton("Listar Bebidas");
        btnListar.setBounds(100, 100, 195, 30);
        panel.add(btnListar);

        JButton btnCalcularTotalBebida = new JButton("Calcular Valor Total Bebida");
        btnCalcularTotalBebida.setBounds(100, 140, 195, 30);
        panel.add(btnCalcularTotalBebida);

        JButton btnCalcularTotalEstoque = new JButton("Calcular Valor Total Estoque");
        btnCalcularTotalEstoque.setBounds(100, 180, 195, 30);
        panel.add(btnCalcularTotalEstoque);

        JButton btnLancarNota = new JButton("Lançar Nota");
        btnLancarNota.setBounds(100, 220, 195, 30);
        panel.add(btnLancarNota);

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nomeField = new JTextField(20);
                JTextField tipoField = new JTextField(20);
                JTextField precoField = new JTextField(20);
                JTextField quantidadeField = new JTextField(20);
                JTextField descricaoField = new JTextField(20);

                JPanel addPanel = new JPanel();
                addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
                addPanel.add(new JLabel("Nome:"));
                addPanel.add(nomeField);
                addPanel.add(new JLabel("Tipo:"));
                addPanel.add(tipoField);
                addPanel.add(new JLabel("Preço:"));
                addPanel.add(precoField);
                addPanel.add(new JLabel("Quantidade:"));
                addPanel.add(quantidadeField);
                addPanel.add(new JLabel("Descrição:"));
                addPanel.add(descricaoField);

                int option = JOptionPane.showConfirmDialog(frame, addPanel, "Adicionar Bebida", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    String nome = nomeField.getText();
                    String tipo = tipoField.getText();
                    double preco = Double.parseDouble(precoField.getText());
                    int quantidade = Integer.parseInt(quantidadeField.getText());
                    String descricao = descricaoField.getText();

                    Bebida bebida = new Bebida(nome, preco, quantidade, tipo, descricao);
                    bebidaDAO.adicionarBebida(bebida);
                    JOptionPane.showMessageDialog(frame, "Bebida adicionada com sucesso!");
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame listarFrame = new JFrame("Listar Bebidas");
                listarFrame.setSize(600, 300);
                listarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                String[] columnNames = {"Nome", "Tipo", "Preço", "Quantidade", "Descrição"};
                DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                JTable table = new JTable(tableModel);
                JScrollPane scrollPane = new JScrollPane(table);
                listarFrame.add(scrollPane);

                for (Bebida bebida : bebidaDAO.listarBebidas()) {
                    Object[] row = new Object[]{
                            bebida.getNome(),
                            bebida.getTipo(),
                            bebida.getPreco(),
                            bebida.getQuantidade(),
                            bebida.getDescricao()
                    };
                    tableModel.addRow(row);
                }

                listarFrame.setVisible(true);
            }
        });

        btnCalcularTotalBebida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeBebida = JOptionPane.showInputDialog("Digite o nome da bebida:");
                Bebida bebida = bebidaDAO.buscarBebida(nomeBebida);
                if (bebida != null) {
                    double valorTotal = bebida.calcularValorTotal();
                    JOptionPane.showMessageDialog(frame, "O valor total de " + bebida.getNome() + " é: R$ " + valorTotal);
                } else {
                    JOptionPane.showMessageDialog(frame, "Bebida não encontrada!");
                }
            }
        });

        btnCalcularTotalEstoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valorTotalEstoque = bebidaDAO.calcularValorTotalEstoque();
                JOptionPane.showMessageDialog(frame, "O valor total de todas as bebidas no estoque é: R$ " + valorTotalEstoque);
            }
        });

        btnLancarNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame lancarNotaFrame = new JFrame("Lançar Nota");
                lancarNotaFrame.setSize(400, 300);

                JComboBox<String> bebidaComboBox = new JComboBox<>();
                for (Bebida bebida : bebidaDAO.listarBebidas()) {
                    bebidaComboBox.addItem(bebida.getNome());
                }

                JTextField quantidadeField = new JTextField(20);

                JPanel lancarPanel = new JPanel();
                lancarPanel.setLayout(new BoxLayout(lancarPanel, BoxLayout.Y_AXIS));
                lancarPanel.add(new JLabel("Escolha a bebida:"));
                lancarPanel.add(bebidaComboBox);
                lancarPanel.add(new JLabel("Quantidade:"));
                lancarPanel.add(quantidadeField);

                int option = JOptionPane.showConfirmDialog(lancarNotaFrame, lancarPanel, "Lançar Venda", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    String nomeBebida = (String) bebidaComboBox.getSelectedItem();
                    int quantidadeVenda = Integer.parseInt(quantidadeField.getText());

                    Bebida bebida = bebidaDAO.buscarBebida(nomeBebida);
                    if (bebida != null && bebida.getQuantidade() >= quantidadeVenda) {

                        bebida.setQuantidade(bebida.getQuantidade() - quantidadeVenda);
                        bebidaDAO.atualizarBebida(bebida);
                        JOptionPane.showMessageDialog(lancarNotaFrame, "Bebida vendida com sucesso!");


                        lancarNotaFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(lancarNotaFrame, "Quantidade insuficiente no estoque!");
                    }
                }


                lancarNotaFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
    }
}
