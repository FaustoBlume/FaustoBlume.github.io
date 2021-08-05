package Entidades;

public class Cliente extends Usuario{
String domicilio,telefono,edad;


public String getDomicilio() {
	return domicilio;
}

public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
}

public String getTelefono() {
	return telefono;
}

public void setTelefono(String telefono) {
	this.telefono = telefono;
}

public String getEdad() {
	return edad;
}

public void setEdad(String edad) {
	this.edad = edad;
}

}
