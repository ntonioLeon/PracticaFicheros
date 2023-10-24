
package centroFormacion;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class ModAlumno implements Serializable{ //Serializado
	private static int cont = 0;;
	private String numExpediente;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
	private LocalDate fechaNacimiento;
	private HashMap<String, ModCurso> cursos;

	public ModAlumno(String nombre, String apellido, String telefono, String direccion, LocalDate fechaNacimiento,
			HashMap<String, ModCurso> cursos) {
		cont++;
		this.numExpediente = "Expediente: " + cont;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.cursos = cursos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModAlumno other = (ModAlumno) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(nombre, other.nombre);
	}

	public String getNumExpediente() {
		return numExpediente;
	}

	public void setNumExpediente(String numExpediente) {
		this.numExpediente = numExpediente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public HashMap<String, ModCurso> getCursos() {
		return cursos;
	}

	public void setCursos(HashMap<String, ModCurso> cursos) {
		this.cursos = cursos;
	}

	@Override
	public String toString() {
		if (!this.cursos.isEmpty()) {
			return "ModAlumno [numExpediente=" + numExpediente + ", nombre=" + nombre + ", apellido=" + apellido
					+ ", telefono=" + telefono + ", direccion=" + direccion + ", fechaNacimiento=" + fechaNacimiento
					+ ", cursos=" + verCursos() + "]";
		} else {
			return "ModAlumno [numExpediente=" + numExpediente + ", nombre=" + nombre + ", apellido=" + apellido
					+ ", telefono=" + telefono + ", direccion=" + direccion + ", fechaNacimiento=" + fechaNacimiento
					+ "]";
		}
	}

	private String verCursos() {
		String aux = "";
		for (String a : this.cursos.keySet()) {
			aux = this.cursos.get(a).getNombre() + ", " + aux;
		}
		return aux;
	}
}
