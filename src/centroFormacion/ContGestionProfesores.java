package centroFormacion;

import java.util.HashMap;
import java.util.Scanner;

public class ContGestionProfesores {

	public static void alta() {
		String dni = null, nombre = null, direc = null, telef = null;

		dni = ModValidador.validarDNI();
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
			// Se procede al alta
			ModBinario.guardar(new ModProfesor(dni, nombre, direc, telef, new HashMap<String, ModCurso>()));
			System.out.println("ALTA REALIZADA.");
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
			}
		}
	}

	public static void baja() {
		System.out.println("MENU DE BAJA:");
		System.out.println("EL SIGUIENTE DNI VALDRA PARA IDENTIFICAR AL PROFESOR QUE DESEA BORRAR");
		String dni = ModValidador.validarDNI();
		if (dni != null) {
			HashMap<String, ModProfesor> profesores = ModBinario.obtenerHashDeProfes();
			if (siNo("borrar","",profesores.get(dni).getNombre(),"")) {
				profesores.remove(dni);
				System.out.println("BAJA REALIZADA.");
				ModBinario.reEscribirFichero(profesores);
			}
		} else {
			if (dni == null) {
				System.out.println("LA BAJA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DNI");
			}
		}
	}
	
	public static void buscar() {
		System.out.println("MENU DE BUSQUEDA:");
		System.out.println("EL SIGUIENTE DNI VALDRA PARA IDENTIFICAR AL PROFESOR QUE DESEA BUSCAR");
		String dni = ModValidador.validarDNI();
		if (dni != null) {
			ModBinario.mostrarUnProfesor(dni);
		} else {
			if (dni == null) {
				System.out.println("LA BUSQUEDA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DNI");
			}
		}
	}
	
	public static void mostrarTodosLosProfesores() {
		ModBinario.mostrarContenidoDelFichero();
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
			System.out.println("SEGURO QUE DESEA BORRAR A " + antiguo.toUpperCase() + " DE SU LISTA DE PROFESORES?");
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
