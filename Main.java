//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {


        BebidaDAO bebidaDAO = new BebidaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();


        new LoginGUI(usuarioDAO, bebidaDAO);


        Bebida bebida1 = new Bebida("Coca-Cola", 5.50, 10, "Não Alcoólica", "Refrigerante");
        Bebida bebida2 = new Bebida("Cerveja", 8.00, 20, "Alcoólica", "Cerveja Lata");
        Bebida bebida3 = new Bebida("Fanta",7.50,30,"Não Alcoólica", "Refrigerante");
        Bebida bebida4 = new Bebida("Ice Drink Blue",4.20,60,"Alcoólica","Bebida Energetica");


        bebidaDAO.adicionarBebida(bebida1);
        bebidaDAO.adicionarBebida(bebida2);
        bebidaDAO.adicionarBebida(bebida3);
        bebidaDAO.adicionarBebida(bebida4);
    }
}