package centroFormacion;

import java.util.ArrayList;
import java.util.HashMap;

public class Prueba {

	public static void main(String[] args) {
		
		//pruebaSerializacionAlumnos();

	}
	
	private static void pruebaSerializacionAlumnos() {
		ArrayList<ModAlumno> alumnos = new ArrayList<>();
		
		for (int i = 0; i < 3; i++) {
			alumnos.add(new ModAlumno("nom_"+i,"ape_"+i,"tel_"+i,"dir_"+i,null,new HashMap<String, ModCurso>()));
		}
		
		ModSerializado.guardar(alumnos);
		
		alumnos = new ArrayList<>();
		
		alumnos = ModSerializado.cargar();
		
		for (ModAlumno modAlumno : alumnos) {
			System.out.println(modAlumno);
		}
	}

}
