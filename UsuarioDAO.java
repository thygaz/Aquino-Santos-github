
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {


    private String usuario = "Dono";
    private String senha = "Unidrink";


    public boolean autenticarUsuario(String username, String password) {
        return usuario.equals(username) && senha.equals(password);
    }
}

