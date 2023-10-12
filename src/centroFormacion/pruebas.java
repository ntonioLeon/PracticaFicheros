package centroFormacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class pruebas {
	public static void main(String[] args) {
		// Pruebas de serializador
		//pruebasSerializado();
		//Pruebas LocalDate
		//probarLocalDate();
		//pruebaDni
		probarDni();

	}
	
	private static void pruebasSerializado() {
		ModSerializado ser = new ModSerializado();
		ArrayList<ModAlumno> alumnos = new ArrayList<ModAlumno>();
		for (int i = 0; i < 3; i++) {
			alumnos.add(
					new ModAlumno("" + i, "" + i, "" + i, "" + i, LocalDate.now(), new HashMap<String, ModCurso>()));
		}
		ser.escribir(alumnos);
		alumnos = null;

		alumnos = ser.deserializar();
		for (ModAlumno a : alumnos) {
			System.out.println(a);
		}
	}
	
	private static void probarLocalDate() {
		LocalDate prueba = ModValidador.validarFechaNacimiento();
		System.out.println(prueba);
	}
	
	private static void probarDni() {
		String dni = ModValidador.validarDNI();
		System.out.println(dni);
	}
}
