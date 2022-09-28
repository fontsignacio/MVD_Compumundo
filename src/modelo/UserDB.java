package modelo;

import java.sql.*;


public class UserDB {
    
    private String url = "jdbc:mariadb://127.0.0.1/compumundo";
    private String usr = "root";
    private String clave = "root";
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,usr,clave);
    }
    
    private void ejecutar(String sql){
        try {
            Connection c = getConnection();
            Statement s = c.createStatement();
            s.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(sql);
    }
    
    public boolean validarUsuario (Usuario user){
        boolean validarUsuario = false;
        try{
            Connection c = getConnection();
            ResultSet res = c.createStatement().executeQuery(
                    "select * from inicio where usuario = '" + user.getUsuario() + 
                     "' AND contraseña = '" + user.getContraseña() + "'");
            while(res.next()){
                if (!res.wasNull()){
                    validarUsuario = true;
                }
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return validarUsuario;
    }
    
    
}
