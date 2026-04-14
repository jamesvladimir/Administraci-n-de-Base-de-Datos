import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FormularioProducto extends JFrame {
    private JTextField txtId, txtCat, txtProv, txtVenta, txtCosto, txtNombre, txtFecha;

    public FormularioProducto() {
        setTitle("Registrar Nuevo Producto");
        setSize(400, 350);
        setLayout(new GridLayout(7, 2, 10, 10));
        setLocationRelativeTo(null);

        // Campos basados en tu tabla PRODUCTO
        add(new JLabel(" ID Producto:")); txtId = new JTextField(); add(txtId);
        add(new JLabel(" ID Categoría:")); txtCat = new JTextField(); add(txtCat);
        add(new JLabel(" ID Proveedor:")); txtProv = new JTextField(); add(txtProv);
        add(new JLabel(" Nombre:")); txtNombre = new JTextField(); add(txtNombre);
        add(new JLabel(" Precio Venta:")); txtVenta = new JTextField(); add(txtVenta);
        add(new JLabel(" Precio Prov:")); txtCosto = new JTextField(); add(txtCosto);
        add(new JLabel(" Caducidad (AAAA-MM-DD):")); txtFecha = new JTextField(); add(txtFecha);

        JButton btnGuardar = new JButton("Guardar en BD");
        add(new JLabel("")); add(btnGuardar);

        btnGuardar.addActionListener(e -> guardarProducto());
    }

    private void guardarProducto() {
        String sql = "INSERT INTO PRODUCTO (ID_PRODUCTO, ID_CATEGORIA, ID_PROVEEDORES, PRECIO_VENTA, PRECIO_PROV, NOMBRE_PRODUCTO, CADUCIDAD) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, Integer.parseInt(txtId.getText()));
            ps.setInt(2, Integer.parseInt(txtCat.getText()));
            ps.setInt(3, Integer.parseInt(txtProv.getText()));
            ps.setDouble(4, Double.parseDouble(txtVenta.getText()));
            ps.setDouble(5, Double.parseDouble(txtCosto.getText()));
            ps.setString(6, txtNombre.getText());
            ps.setString(7, txtFecha.getText());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "¡Producto guardado exitosamente!");
            this.dispose();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }
}