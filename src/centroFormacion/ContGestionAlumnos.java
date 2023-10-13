package centroFormacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ContGestionAlumnos {

	public static ArrayList<ModAlumno> listaAlumnos = new ArrayList<>();

	public static void alta() {

		String dni = null, nombre = null, direc = null, telef = null;
		LocalDate naz = null;

		dni = ModValidador.validarDNI(1);
		if (dni != null) {
			nombre = ModValidador.validarNombre();
		}
		if (nombre != null && dni != null) {
			direc = ModValidador.validarDireccion();
		}
		if (direc != null && nombre != null && dni != null) {
			telef = ModValidador.validarTeledono();
		}
		if (telef != null && direc != null && nombre != null && dni != null) {
			naz = ModValidador.validarFechaNacimiento();
			if (naz != null && telef != null && direc != null && nombre != null && dni != null) {
				// Se procede al alta

				ArrayList<ModAlumno> nuevoAlumno = new ArrayList<>();
				nuevoAlumno.add(new ModAlumno(dni, nombre, direc, telef, naz, new HashMap<String, ModCurso>()));
				ModSerializado.guardar(nuevoAlumno);
			} else {
				if (dni == null) {
					System.out.println(
							"EL ALTA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DNI");
				} else if (nombre == null) {
					System.out.println(
							"EL ALTA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: NOMBRE");
				} else if (direc == null) {
					System.out.println(
							"EL ALTA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DIRECCION");
				} else if (telef == null) {
					System.out.println(
							"EL ALTA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: TELEFONO");
				} else if (naz == null) {
					System.out.println(
							"EL ALTA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: FECHA DE NACIMIENTO");
				}
			}
		}
	}

	public static void baja() {
		System.out.println("MENU DE BAJA:");
		System.out.println("EL SIGUIENTE NOMBRE VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA BORRAR");
		String nom = ModValidador.validarNombre();
		if (nom != null) {
			ArrayList<ModAlumno> listaAlumnos = ModSerializado.cargar();
			for (ModAlumno modAlumno : listaAlumnos) {
				if (modAlumno.getNombre().equals(nom)) {
					if (siNo("borrar", "", modAlumno.getNombre(), "")) {
					listaAlumnos.remove(modAlumno);
					System.out.println("BAJA REALIZADA.");
					ModSerializado.guardar(listaAlumnos);
					}
				}
				
			}

		} else {
			if (nom == null) {
				System.out.println(
						"LA BAJA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DNI");
			}
		}
	}

	public static void buscar() {
		System.out.println("MENU DE BUSQUEDA:");
		System.out.println("EL SIGUIENTE DNI VALDRA PARA IDENTIFICAR AL PROFESOR QUE DESEA BUSCAR");
		String nom = ModValidador.validarNombre();
		if (nom != null) {
			ModSerializado.cargarUnAlumno(nom);
		} else {
			if (nom == null) {
				System.out.println(
						"LA BUSQUEDA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DNI");
			}
		}
	}

	public static void mostrarTodosLosAlumnos() {
		ArrayList<ModAlumno> listaAlumnos = ModSerializado.cargar();
		for (ModAlumno modAlumno : listaAlumnos) {
			System.out.println(modAlumno.toString());
		}
	}

	private static boolean siNo(String modalidad, String campo, String antiguo, String nuevo) {
		Scanner sc = new Scanner(System.in);
		String elec = null;
		if (modalidad.equalsIgnoreCase("modificar")) {
			System.out
					.println("ESTA SEGURO DE QUERER " + modalidad.toUpperCase() + " EL " + campo.toUpperCase() + " DE " + antiguo + " A " + nuevo);
			do {
				System.out.println("PULSE 1 PARA SI, 2 PARA NO");
				elec = sc.nextLine();
				if (elec.equals("1")) {
					return true;
				} else if (elec.equals("2")) {
					return false;
				} else {
					System.out.println("RECUERDE QUE");
				}
			} while (!elec.equals("1") || !elec.equals("2"));
		} else {
			System.out.println("SEGURO QUE DESEA BORRAR A " + antiguo.toUpperCase() + " DE SU LISTA DE ALUMNOS?");
			do {
				System.out.println("PULSE 1 PARA SI, 2 PARA NO");
				elec = sc.nextLine();
				if (elec.equals("1")) {
					return true;
				} else if (elec.equals("2")) {
					return false;
				} else {
					System.out.println("RECUERDE QUE");
				}
			} while (!elec.equals("1") || !elec.equals("2"));
		}
		return false;
	}
}
