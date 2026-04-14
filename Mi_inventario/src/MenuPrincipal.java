import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MenuPrincipal extends JFrame {
    
    public MenuPrincipal(String nombreUsuario) {
        // Configuración básica de la ventana
        setTitle("Sistema de Inventario - Menú Principal");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana

        // 1. Crear la Barra de Menú
        JMenuBar menuBar = new JMenuBar();

        // 2. Menú de PRODUCTOS (con sus opciones)
        JMenu menuProductos = new JMenu("Productos");
        JMenuItem itemIngresar = new JMenuItem("Ingresar Producto");
        JMenuItem itemConsultar = new JMenuItem("Consultar Inventario");
        JMenuItem itemEliminar = new JMenuItem("Eliminar Producto");
        
        menuProductos.add(itemIngresar);
        itemIngresar.addActionListener(e -> new FormularioProducto().setVisible(true));
        menuProductos.add(itemConsultar);
        menuProductos.add(itemEliminar);

        // 3. Menú de PROVEEDORES
        JMenu menuProveedores = new JMenu("Proveedores");
        JMenuItem itemVerProv = new JMenuItem("Ver Proveedores");
        menuProveedores.add(itemVerProv);

        // Agregar los menús a la barra
        menuBar.add(menuProductos);
        menuBar.add(menuProveedores);
        setJMenuBar(menuBar);

        // 4. Panel Central de Bienvenida
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        
        JLabel lblBienvenida = new JLabel("Bienvenido, " + nombreUsuario, SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 22));
        panelCentral.add(lblBienvenida, BorderLayout.CENTER);

        JLabel lblInfo = new JLabel("Seleccione una opción del menú superior para comenzar.", SwingConstants.CENTER);
        panelCentral.add(lblInfo, BorderLayout.SOUTH);

        add(panelCentral);

        // --- LÓGICA TEMPORAL PARA PROBAR ---
        itemConsultar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Función de consulta en desarrollo...");
        });
    }
}