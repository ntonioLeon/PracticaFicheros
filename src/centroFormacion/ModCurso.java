package centroFormacion;

import java.util.HashMap;

public class ModCurso {
	private static int cont = 0;
	private String codigo;
	private String nombre;
	private String descripcion;
	private ModProfesor profesor;
	private HashMap<String, ModAlumno> alumnos;

	public ModCurso(String nomb, String descrip) { // Para cuando se construye un curso por primera vez.
		cont++;
		this.codigo = "AE" + cont;
		this.nombre = nomb;
		this.descripcion = descrip;
		this.alumnos = new HashMap<String, ModAlumno>();
	}

	public ModCurso(String cod, String nomb, String descrip) { // Para cuando se reconstruye.
		this.codigo = cod;
		this.nombre = nomb;
		this.descripcion = descrip;
		this.alumnos = new HashMap<String, ModAlumno>();
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

	public ModProfesor getProfesor() {
		return profesor;
	}

	public void setProfesor(ModProfesor profesor) {
		this.profesor = profesor;
	}

	public HashMap<String, ModAlumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(HashMap<String, ModAlumno> alumnos) {
		this.alumnos = alumnos;
	}

	@Override
	public String toString() {
		if (this.profesor == null && this.alumnos.isEmpty()) {
			return this.codigo + "," + this.nombre + "," + this.descripcion;
		} else if (this.profesor == null && !this.alumnos.isEmpty()) {
			return this.codigo + "," + this.nombre + "," + this.descripcion+","+printearAlumnos();
		} else if (this.profesor != null && this.alumnos.isEmpty()) {
			return this.codigo + "," + this.nombre + "," + this.descripcion+","+this.profesor.getNombre()+";"+this.profesor.getDni();
		} else {
			return this.codigo + "," + this.nombre + "," + this.descripcion+","+this.profesor.getNombre()+";"+this.profesor.getDni()+","+printearAlumnos();
		}
	}
	
	private String printearAlumnos() {
		String aux = "";
		for (String a : this.alumnos.keySet()) {
			aux = a+";"+aux;
		}
		return aux;
	}
}
