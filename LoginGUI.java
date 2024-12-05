import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginGUI {
    private UsuarioDAO usuarioDAO;
    private BebidaDAO bebidaDAO;

    public LoginGUI(UsuarioDAO usuarioDAO, BebidaDAO bebidaDAO) {
        this.usuarioDAO = usuarioDAO;
        this.bebidaDAO = bebidaDAO;


        JFrame frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);


        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);


        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 90, 80, 25);
        panel.add(loginButton);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());


                if (usuarioDAO.autenticarUsuario(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login bem-sucedido!");
                    frame.dispose();
                    new Menu(bebidaDAO, usuarioDAO);  // Passando os parâmetros para o Menu
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário ou senha inválidos!");
                }
            }
        });


        frame.setVisible(true);
    }
}