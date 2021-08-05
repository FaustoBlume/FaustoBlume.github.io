package Entidades;
public class Producto {
int id,stock;

String foto;
String descripcion;
double precio;

public String getFoto() {
	return foto;
}
public void setFoto(String foto) {
	this.foto = foto;
}

private Marca marca;
private Tipo_Producto tp;
public Tipo_Producto getTp() {
	return tp;
}
public void setTp(Tipo_Producto tp) {
	this.tp = tp;
}
public Marca getMarca() {
	return marca;
}
public void setMarca(Marca marca) {
	this.marca = marca;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public double getPrecio() {
	return precio;
}
public void setPrecio(double precio) {
	this.precio = precio;
}

}
