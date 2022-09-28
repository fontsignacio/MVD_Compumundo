package controladores;
import modelo.*;
import vista.VentanaError;
import vista.VentanaUsuario;


public class ControladorUsuario {
    static UserDB db = new UserDB();
    static VentanaUsuario ventana = new VentanaUsuario();
    static VentanaError error = new VentanaError ();
    
    public static void mostrar(){
        ventana.setVisible(true);
    }
    
    public static void ocultar(){
        ventana.setVisible(false);
    }
    
    public static void botonInicio () {
        Usuario usr = new Usuario(ventana.getUsuario(), ventana.getContraseña());
        
        if (db.validarUsuario(usr)){
            ocultar();
            ControladorProducto.mostrar();
        }else{
            error.setVisible(true);
        }
    }
}
