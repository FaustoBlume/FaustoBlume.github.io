package Entidades;
public class Compra {
int id;
double total;
String fecha;
Cliente usu;
public Cliente getUsu() {
	return usu;
}
public void setUsu(Cliente usu) {
	this.usu = usu;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
public String getFecha() {
	return fecha;
}
public void setFecha(String s) {
	this.fecha = s;
}
}
