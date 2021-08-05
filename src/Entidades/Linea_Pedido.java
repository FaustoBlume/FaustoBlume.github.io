package Entidades;

public class Linea_Pedido {
int id, cantidad;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

private Producto prod;
private Compra compra;
public Compra getCompra() {
	return compra;
}
public void setCompra(Compra compra) {
	this.compra = compra;
}
public Producto getProd() {
	return prod;
}
public void setProd(Producto prod) {
	this.prod = prod;
}

double subtotal;

public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
public double getSubtotal() {
	return subtotal;
}
public void setSubtotal(double subtotal) {
	this.subtotal = subtotal;
}


}
