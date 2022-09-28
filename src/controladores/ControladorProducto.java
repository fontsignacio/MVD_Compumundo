package controladores;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;


public class ControladorProducto {
    static DB db = new DB();
    static VentanaProducto ventana = new VentanaProducto();
    
    public static void mostrar(){
        ventana.setVisible(true);
        cargarTabla();
    }
    
    public static void ocultar(){
        ventana.setVisible(false);
        cargarTabla();
    }
   
    public static void cargarTabla(){
        DefaultTableModel datos = (DefaultTableModel) ventana.getjTable1().getModel();
        datos.setNumRows(0);
        for (Producto p : db.getProductos() ) {
            Object[] fila = new Object[3];
            fila[0] = p.getId();
            fila[1] = p.getNombre();
            fila[2] = p.getPrecio();
            datos.addRow(fila);
        }
    }
    
    public static void botonBuscar(){
        int codigo = Integer.parseInt(ventana.getjTextField1().getText());
        Producto p = db.buscar(codigo);        
        ventana.getjTextField2().setText( p.getNombre() );
        ventana.getjTextField3().setText( Double.toString(p.getPrecio()));
    }

    public static void botonAgregar() {        
        String nombre = ventana.getjTextField2().getText();
        double precio = Double.parseDouble(ventana.getjTextField3().getText());
        db.agregarProducto(nombre, precio);
        cargarTabla();
    }
    
    public static void botonModificar() {
        int codigo = Integer.parseInt(ventana.getjTextField1().getText());
        String nombre = ventana.getjTextField2().getText();
        double precio = Double.parseDouble(ventana.getjTextField3().getText());
        db.modificarProducto(codigo,nombre, precio);
        cargarTabla();
    }
    
    
    public static void botonEliminar() {
        int codigo = Integer.parseInt(ventana.getjTextField1().getText());    
        db.eliminarProducto(codigo);
        cargarTabla(); 
    }
    
    public static void botonTotal(){
        int total = db.total();
        ventana.getTotal().setText(Integer.toString(total));
        
    }
        
}
