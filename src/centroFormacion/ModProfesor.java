package centroFormacion;

import java.util.HashMap;
import java.util.Objects;

public class ModProfesor {
	private String dni;
	private String nombre;
	private String direccion;
	private String telefono;
	private HashMap<String, ModCurso> cursos;

	public ModProfesor(String dni, String nombre, String direccion, String telefono, HashMap<String, ModCurso> cursos) {
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cursos = cursos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModProfesor other = (ModProfesor) obj;
		return Objects.equals(dni, other.dni);
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public HashMap<String, ModCurso> getCursos() {
		return cursos;
	}

	public void setCursos(HashMap<String, ModCurso> cursos) {
		this.cursos = cursos;
	}

	@Override
	public String toString() {
		if (!cursos.isEmpty()) {
			return "Profesor [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono="
					+ telefono + ", cursos=" + cursosImpartidos() + "]";
		} else {
			return "Profesor [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono="
					+ telefono + ", cursos= Ninguno]";
		}
	}

	private String cursosImpartidos() {
		String aux = "";
		for (String a : this.cursos.keySet()) {
			aux = this.cursos.get(a).getNombre() +", "+aux;
		}
		return null;
	}
}
