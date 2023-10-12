package centroFormacion;

import java.util.ArrayList;

public class ModCurso {
	private static int cont = 0;
	private String codigo;
	private String nombre;
	private String descripcion;

	public ModCurso(String nombre, String descripcion) { 	//Para cuando se construye un curso por primera vez.
		cont++;
		this.codigo = "AE"+cont;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public ModCurso(String cod, String nombre, String descripcion) {		//Para cuando se reconstruye.
		this.codigo = cod;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "ModCurso [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
}
