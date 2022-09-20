package javaapplication80;
import controladores.ControladorProducto;
import modelo.*;
import vista.VentanaProducto;

public class JavaApplication80 {

    public static void main(String[] args) {
        
        //DB db = new DB();
        /*db.agregarProducto("Coca cola",100);
        Producto p = new Producto();
        p.setNombre("Fanta"); p.setPrecio(200);
        db.agregarProducto(p);*/
        //db.eliminarProducto(9);
        //Producto p = db.buscar(3);
        //System.out.println(p);
        /*
        Producto p1 = db.buscar(3);
        p1.setPrecio(777);
        db.modificarProducto(p1);
        
        for (Producto p : db.getProductos() ) {
            System.out.println(p);
        }
        */
        
        ControladorProducto.mostrar();     

    }    
}
