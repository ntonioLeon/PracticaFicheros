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
		//probarDni();
		//prueba de data stream
		//pruebaDataStream();

	}
	
	private static void pruebasSerializado() {
		ContFicherializador fich = new ContFicherializador();
		ArrayList<ModAlumno> alumnos = new ArrayList<ModAlumno>();
		for (int i = 0; i < 3; i++) {
			alumnos.add(
					new ModAlumno("" + i, "" + i, "" + i, "" + i, LocalDate.now(), new HashMap<String, ModCurso>()));
		}
		fich.getSer().guardar(alumnos);
		alumnos = null;

		alumnos = fich.getSer().cargar();
		for (ModAlumno a : alumnos) {
			System.out.println(a);
		}
	}
	
	private static void pruebaDataStream() {
		ModBinario bin = new ModBinario();
		bin.mostrarContenidoDelFichero();
		for (int i = 0; i < 3; i++) {
			ModProfesor a = new ModProfesor(""+i,""+i,""+i,""+i,new HashMap<String, ModCurso>());
			bin.guardar(a);
		}
		bin.mostrarContenidoDelFichero();
		bin.mostrarUnProfesor("2");
	}
	
	private static void probarLocalDate() {
		ModValidador val = new ModValidador();
		LocalDate prueba = val.validarFechaNacimiento();
		System.out.println(prueba);
	}
	
	private static void probarDni() {
		ModValidador val = new ModValidador();
		String dni = val.validarDNI(1);
		System.out.println(dni);
	}
}
