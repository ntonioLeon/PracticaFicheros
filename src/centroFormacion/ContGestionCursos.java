package centroFormacion;

import java.util.HashMap;
import java.util.Scanner;

public class ContGestionCursos {

	public static void alta() {
		String nomb = null, descrip = null;
		nomb = ModValidador.validarNombre();
		if (nomb != null) {
			descrip = ModValidador.validarDescripcion();
		}
		if (nomb != null && descrip != null) {
			ModFicherosDeTexto.escribirCurso(new ModCurso(nomb, descrip));
			System.out.println("ALTA REALIZADA");
		} else {
			if (nomb == null) {
				System.out.println(
						"EL ALTA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: NOMBRE");
			} else if (descrip == null) {
				System.out.println(
						"EL ALTA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DESCRIPCION");
			}
		}
	}

	public static void baja() {
		System.out.println("MENU DE BAJA:");
		System.out.println("EL SIGUIENTE CODIGO VALDRA PARA IDENTIFICAR CURSO QUE DESEA BORRAR");
		String cod = ModValidador.validarCodigo();
		if (cod != null) {
			HashMap<String, ModCurso> cursos = ModFicherosDeTexto.obtenerTodosLosCursos();
			if (cursos.get(cod) != null) {
				if (siNo("borrar", "", cursos.get(cod).getNombre() + " CON CODIGO: " + cursos.get(cod).getCodigo(),
						"")) {
					cursos.remove(cod);
					System.out.println("CURSO BORRADO");
					ModFicherosDeTexto.reEscribirTrasBajaOMod(cursos);
				}
			} else {
				System.out.println(
						"EL BORRADO NO SE PUDO REALIZAR DEBIDO ANO EXISTE NINGUN CURSO CON EL CODIGO QUE SE PROPORCIONO");
			}
		} else {
			if (cod == null) {
				System.out.println(
						"EL BORRADO NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: CODIGO");
			}
		}
	}

	// if (siNo("MODIFICAR", "", cursos.get(cod).getNombre() + " CON CODIGO: " +
	// cursos.get(cod).getCodigo(),"")) {
	public static void modificar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DE MODIFICAR:");
		System.out.println("EL SIGUIENTE CODIGO VALDRA PARA IDENTIFICAR CURSO QUE DESEA BORMODIFICARRAR");
		String cod = ModValidador.validarCodigo();
		if (cod != null) {
			HashMap<String, ModCurso> cursos = ModFicherosDeTexto.obtenerTodosLosCursos();
			if (cursos.get(cod) != null) {
				String elec = "";
				do {
					elec = sc.nextLine();
					switch (elec) {
					case "1":
						System.out.println("INTODUZCA 1 PARA MODIFICAR NOMBRE\n2 PARA MODIFICAR DESCRIPCION");
						String nomb = ModValidador.validarNombre();
						if (nomb != null) {
							if (siNo("modificar", "NOMBRE", cursos.get(cod).getNombre(), nomb)) {
								cursos.get(cod).setNombre(nomb);
								System.out.println("MODIFICACION DE NOMBRE REALIZADA");
							}
						} else {
							System.out.println(
									"LA MODIFICACION NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: NOMBRE");
						}
						break;
					case "2":
						String desc = ModValidador.validarDescripcion();
						if (desc != null) {
							if (siNo("modificar", "DESCIPCION", cursos.get(cod).getDescripcion(), desc)) {
								cursos.get(cod).setDescripcion(desc);
								System.out.println("MODIFICACION DE DESCRIPCION REALIZADA");
							}
						} else {
							System.out.println(
									"LA MODIFICACION NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DESCIPCION");
						}
						break;
					case "0":
						//Para que no salte el default con la salida
						break;
					default:
						System.out.println("INTRODUZCA SOLO OPCIONES VALIDAS");
						break;
					}
				} while (!elec.equals("0"));
				ModFicherosDeTexto.reEscribirTrasBajaOMod(cursos);
			} else {
				System.out.println(
						"LA MODIFICACION NO SE PUDO REALIZAR DEBIDO A QUE NO EXISTE NINGUN CURSO CON EL CODIGO QUE SE PROPORCIONO");
			}
		} else {
			if (cod == null) {
				System.out.println(
						"LA MODIFICACION NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: CODIGO");
			}
		}
	}

	public static void buscar() {
		System.out.println("MENU DE BUSQUEDA:");
		System.out.println("EL SIGUIENTE CODIGO VALDRA PARA IDENTIFICAR CURSO QUE DESEA BUSCAR");
		String cod = ModValidador.validarCodigo();
		if (cod != null) {
			ModFicherosDeTexto.buscarPorCodigo(cod);
		} else {
			if (cod == null) {
				System.out.println(
						"LA BUSQUEDA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: CODIGO");
			}
		}
	}

	public static void mostrarTodosLosProfesores() {
		ModFicherosDeTexto.mostrarTodos();
	}

	private static boolean siNo(String modalidad, String campo, String antiguo, String nuevo) {
		Scanner sc = new Scanner(System.in);
		String elec = null;
		if (modalidad.equalsIgnoreCase("modificar")) {
			System.out.println("ESTA SEGURO DE QUERER " + modalidad.toUpperCase() + " EL " + campo.toUpperCase()
					+ " DE " + antiguo + " A " + nuevo);
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
			System.out.println("SEGURO QUE DESEA BORRAR A " + antiguo.toUpperCase() + " DE SU LISTA DE CURSOS?");
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
