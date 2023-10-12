package centroFormacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ContGestionAlumnos {

	private final static Scanner SCR = new Scanner(System.in);
	
	public static ArrayList<ModAlumno> listaAlumnos = new ArrayList<>();
	
	public static void alta() {
		
		System.out.println("-- Introduce un Nombre");
		String nom = SCR.nextLine();
		System.out.println("-- Introduce un Apellido");
		String ape = SCR.nextLine();
		System.out.println("-- Introduce un Teléfono");
		String tel = SCR.nextLine();
		System.out.println("-- Introduce una Dirección");
		String dir = SCR.nextLine();
		System.out.println("-- Introduce la Fecha de nacimiento (formato dia-mes-año)");
		String fecnaz_texto = SCR.nextLine();
		String[] aux = fecnaz_texto.split("-");
		
		LocalDate fecNaz = LocalDate.of(Integer.valueOf(aux[2]),Integer.valueOf(aux[1]),Integer.valueOf(aux[0]));
		
		ModAlumno alumno = new ModAlumno(nom,ape,tel,dir,fecNaz,new HashMap<String, ModCurso>());
		
		listaAlumnos.add(alumno);
		
		ModSerializado.guardar(listaAlumnos);
	}
	
	public static void baja() {
		
		
		
	}
	
	
}
