package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Consultas extends Conexion{
    
    public ArrayList<Producto> listadoProductos(){
    
             PreparedStatement pst =null;
             ResultSet rs = null;
             ArrayList<Producto> lista = new ArrayList<>();
        try {
            
            String consulta ="SELECT * FROM Producto ORDER BY nombre";
            pst = getConexion().prepareStatement(consulta);
            rs = pst.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                lista.add(producto);
                
            }
            
            rs.close();
            pst.close();
            
        } catch (Exception e) {
        }
        return lista;
    }
    
    public boolean registrarVenta(int cantidad, String nombreProducto, String mes){
    
        PreparedStatement pst=null;
        try {
            String consulta = "INSERT INTO Venta (cantidad,idProducto,idMesVenta) VALUES(?,(SELECT idProducto FROM Producto WHERE nombre=?),(SELECT idMesVenta FROM MesVenta WHERE nombreMes=?))"; //Codigo SQL Query
            pst = getConexion().prepareStatement(consulta);
            
            pst.setInt(1, cantidad);
            pst.setString(2, nombreProducto);
            pst.setString(3, mes);
            
            if (pst.executeUpdate() == 1) {
                return true;
            }
            
        } catch (Exception e) {
        }
        
        return false;
    }
    
    public ArrayList<VentaTabla> listadoVentas(){
    
             PreparedStatement pst =null;
             ResultSet rs = null;
             ArrayList<VentaTabla> lista = new ArrayList<>();
        try {
            
            String consulta ="select nombre, nombremes, cantidad\n" +
                             "from Venta\n" +
                             "inner join Producto on Producto.idProducto=Venta.idProducto\n" +
                             "inner join MesVenta on MesVenta.idMesVenta=Venta.idMesVenta\n" ;
                             
            pst = getConexion().prepareStatement(consulta);
            rs = pst.executeQuery();
            while (rs.next()) {
                VentaTabla ventaTabla = new VentaTabla();
                ventaTabla.setNombreProducto(rs.getString("nombre"));
                ventaTabla.setMes(rs.getString("nombremes"));
                ventaTabla.setCantidad(rs.getInt("cantidad"));
                lista.add(ventaTabla);
                
            }
            
            rs.close();
            pst.close();
            
        } catch (Exception e) {
        }
        return lista;
    }
    
    public ArrayList<MateriaPrimaTotal> listadoMateriaPrima(){
    
             PreparedStatement pst =null;
             ResultSet rs = null;
             ArrayList<MateriaPrimaTotal> lista = new ArrayList<>();
        try {
            
            String consulta ="select Producto.nombre, sum(cantidad) Total\n" +
            "from MateriaPrima\n" +
            "inner join Producto ON Producto.idProducto=MateriaPrima.idProducto \n" +
            "group by Producto.idProducto;";
                             
            pst = getConexion().prepareStatement(consulta);
           
            rs = pst.executeQuery();
            while (rs.next()) {
                MateriaPrimaTotal mp = new MateriaPrimaTotal();
                mp.setNombreProducto(rs.getString("nombre"));
                mp.setTotalMateriaPrimaEnProducto(rs.getFloat("Total"));
                lista.add(mp);
                
            }
            
            rs.close();
            pst.close();
            
        } catch (Exception e) {
        }
        return lista;
    }
    
     public ArrayList<MateriaPrimaProducto> listadoMateriaPrimaPorProducto(int idProducto){
    
             PreparedStatement pst =null;
             ResultSet rs = null;
             ArrayList<MateriaPrimaProducto> lista = new ArrayList<>();
        try {
            
            String consulta ="select nombre, cantidad\n" +
                            "from MateriaPrima\n" +
                            "where idProducto=?;";
                             
            pst = getConexion().prepareStatement(consulta);
             pst.setInt(1, idProducto);
            rs = pst.executeQuery();
            while (rs.next()) {
                MateriaPrimaProducto mpp = new MateriaPrimaProducto();
                mpp.setNombreMateriaPrima(rs.getString("nombre"));
                mpp.setCantidad(rs.getFloat("cantidad"));
                lista.add(mpp);
                
            }
            
            rs.close();
            pst.close();
            
        } catch (Exception e) {
        }
        return lista;
    }
    
    public static void main(String[] args) {
        
        Consultas c = new Consultas();
        ArrayList<MateriaPrimaProducto> listado = c.listadoMateriaPrimaPorProducto(1);
        for (int i = 0; i < listado.size(); i++) {
            MateriaPrimaProducto get = listado.get(i);
            System.out.println(get.getNombreMateriaPrima()+" ---- "+get.getCantidad());
            
        }
        
        
        
    }
    
}
