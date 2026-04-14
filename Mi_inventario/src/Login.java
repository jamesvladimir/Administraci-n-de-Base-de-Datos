import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    public Login() {
        // Configuración de la ventana
        setTitle("Acceso al Sistema - Mi Inventario");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Elementos visuales
        add(new JLabel("  Usuario:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("  Contraseña:"));
        txtPassword = new JPasswordField();
        add(txtPassword);

        JButton btnEntrar = new JButton("Entrar");
        add(new JLabel("")); 
        add(btnEntrar);

        // Acción del botón
        btnEntrar.addActionListener(e -> validarAcceso());
    }

    private void validarAcceso() {
        String user = txtUsuario.getText();
        String pass = new String(txtPassword.getPassword());

        // Usamos la clase Conexion 
        try (Connection conn = Conexion.getConexion()) {
            if (conn != null) {
                // Consulta a tu tabla EMPLEADOS
                String sql = "SELECT * FROM EMPLEADOS WHERE USUARIO = ? AND CONTRASENA = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, user);
                ps.setString(2, pass);
                
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "¡Bienvenido " + rs.getString("NOMBRE_EMP") + "!");
                    // Aquí llamaremos al menú 
                    // Abrir el menú principal 
                   new MenuPrincipal(rs.getString("NOMBRE_EMP")).setVisible(true);
                    this.dispose(); // Cierra la ventana de Login
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario o contraseña no válidos.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
