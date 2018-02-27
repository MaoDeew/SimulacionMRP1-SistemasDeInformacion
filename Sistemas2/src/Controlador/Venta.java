package Controlador;


public class Venta {
    
    private int idVenta;
    private int cantidad;
    private int idProducto;
    private int idMesVenta;

    public int getIdVenta() {
        return idVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdMesVenta() {
        return idMesVenta;
    }

    public void setIdMesVenta(int idMesVenta) {
        this.idMesVenta = idMesVenta;
    }
    
    
}
