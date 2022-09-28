package modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
    
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
    
    public void agregarProducto(String nombre, double precio){
        String sql = "INSERT INTO producto(nombre,precio) values ('%1','%2')";      
        sql = sql.replace("%1", nombre);
        sql = sql.replace("%2", Double.toString(precio));
        ejecutar(sql);
    }
    
    public void agregarProducto(Producto p){
        agregarProducto(p.getNombre(), p.getPrecio());
    }
    
    public void modificarProducto(int id, String nombre, double precio){
        String sql = "UPDATE producto set nombre = '%1', precio='%2' where id=" + id ;
        sql = sql.replace("%1", nombre);
        sql = sql.replace("%2", Double.toString(precio));
        ejecutar(sql);
    }
    public void modificarProducto(Producto p){
        modificarProducto(p.getId(), p.getNombre(), p.getPrecio());    
    }
    
    public void eliminarProducto(int id){
        String sql = "DELETE FROM producto WHERE id = %1";
        sql = sql.replace("%1", Integer.toString(id) );
        ejecutar(sql);    
    }
    
    public Producto buscar(int id){
        Producto p = new Producto();
        try{
            Connection c = getConnection();
            ResultSet res = 
                    c.createStatement()
                            .executeQuery("select * from producto where id = " + id);
            if (res.next()){
                p.setId(id);
                p.setNombre(res.getString("nombre"));
                p.setPrecio(res.getDouble("precio"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return p;
    }
    
    
    public ArrayList<Producto> getProductos(){
        ArrayList<Producto> productos = new ArrayList<>();
        try{
            Connection c = getConnection();
            ResultSet res = 
                    c.createStatement()
                            .executeQuery("select * from producto");
            while (res.next()){
                Producto p = new Producto();
                p.setId(res.getInt("id"));
                p.setNombre(res.getString("nombre"));
                p.setPrecio(res.getDouble("precio"));
                productos.add(p);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return productos;
    }
    
    
    public int total(){ 
        int total = 0;
        try{
            Connection c = getConnection();
            String sql = "SELECT sum(precio) as total from producto ";
            ResultSet res = c.createStatement().executeQuery(sql);
            if (res.next()){
                if (res.wasNull()){
                    total = 0;
                }
                else{
                    total = res.getInt("total");
                }                
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return total;
    }
    
}

