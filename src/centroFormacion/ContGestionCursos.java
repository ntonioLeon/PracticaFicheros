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
					ModBinario.borradoDeCursoEnCascada(cod);
					ModSerializado.borrarDeCursoEnCascada(cod);
					cursos.remove(cod);
					System.out.println("CURSO BORRADO");
					ModFicherosDeTexto.reEscribirTrasBajaOMod(cursos);
				} else {
					System.out.println("BAJA CANCELADA");
				}
			} else {
				System.out.println(
						"EL BORRADO NO SE PUDO REALIZAR DEBIDO ANO EXISTE NINGUN CURSO CON EL CODIGO QUE SE PROPORCIONO");
			}
		} else {
			System.out.println(
					"EL BORRADO NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: CODIGO");
		}
	}

	public static void modificar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DE MODIFICAR:");
		System.out.println("EL SIGUIENTE CODIGO VALDRA PARA IDENTIFICAR CURSO QUE DESEA RMODIFICARRAR");
		String cod = ModValidador.validarCodigo();
		if (cod != null) {
			HashMap<String, ModCurso> cursos = ModFicherosDeTexto.obtenerTodosLosCursos();
			System.out.println(cursos.size());
			if (cursos.get(cod) != null) {
				String elec = "";
				do {
					System.out.println("INTODUZCA 1 PARA MODIFICAR NOMBRE\n2 PARA MODIFICAR DESCRIPCION\n0 PARA SALIR");
					elec = sc.nextLine();
					switch (elec) {
					case "1":
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
						// Para que no salte el default con la salida
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
			System.out.println(
					"LA MODIFICACION NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: CODIGO");
		}
	}

	public static void buscar() {
		System.out.println("MENU DE BUSQUEDA:");
		System.out.println("EL SIGUIENTE CODIGO VALDRA PARA IDENTIFICAR CURSO QUE DESEA BUSCAR");
		String cod = ModValidador.validarCodigo();
		if (cod != null) {
			ModFicherosDeTexto.buscarPorCodigo(cod);
		} else {
			System.out.println(
					"LA BUSQUEDA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: CODIGO");
		}
	}

	public static void mostrarTodosLosProfesores() {
		ModFicherosDeTexto.mostrarTodos();
	}

	public static void relacionarCursos() {
		System.out.println("MENU DE RELACION DE CURSOS");
		System.out.println("EL SIGUIENTE CODIGO VALDRA PARA IDENTIFICAR CURSO QUE DESEA RELACUIONAR");
		String cod = ModValidador.validarCodigo();
		if (cod != null) {
			HashMap<String, ModCurso> cursos = ModFicherosDeTexto.obtenerTodosLosCursos();
			if (cursos.get(cod) != null) {
				Scanner sc = new Scanner(System.in);
				String selec = "";
				do {
					System.out.println(
							"PULSE 1 PARA RELACIONAR CURSOS CON PORFESORES\n PULSE 2 PARA RELACIONAR CURSOS CON ALUMNOS\n PULSE 0 PARA SALIR");
					selec = sc.nextLine();
					switch (selec) {
					case "1":
						relacionarCursosYProfesores(cod);
						break;
					case "2":
						relacionarCursosYAlumno(cod);
						break;
					case "0":
						break;
					default:
						System.out.println("OPCION NO VALIDA");
						break;
					}
				} while (!selec.equals("0"));
			} else {
				System.out.println(
						"LA RELACION NO SE PUDO REALIZAR DEBIDO A QUE EL CODIGO INTRODUCIDO NO SE CORRESPONDE CON EL DE NINGUN CURSO EXISTENTE");
			}
		} else {
			System.out.println(
					"LA RELACION NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: CODIGO");
		}
	}

	private static void relacionarCursosYProfesores(String cod) {
		HashMap<String, ModCurso> cursos = ModFicherosDeTexto.obtenerTodosLosCursos();
		HashMap<String, ModProfesor> profesores = ModBinario.obtenerHashDeProfes();
		Scanner sc = new Scanner(System.in);
		String selec = "";
		String dni = "";
		if (!profesores.isEmpty()) {
			do {
				System.out.println(
						"PULSE 1 PARA ASIGNAR UN DOCENTE AL CURSO\n PULSE 2 RETIRAR EL DOCENTE DE ESTE CURSO\n PULSE 0 PARA SALIR");
				selec = sc.nextLine();
				switch (selec) {
				case "1":
					System.out.println(
							"INTRODUZCA EL DNI DEL PROFESOR QUE DESEA ASGINAR A " + cursos.get(cod).getNombre());
					dni = ModValidador.validarDNI(2);
					if (profesores.get(dni) != null) { // Si el dni esta bien.
						if (cursos.get(cod).getProfesor() == null) { // No tiene profesor
							if (relacionar(profesores.get(dni).getNombre(), cursos.get(cod).getNombre())) {
								profesores.get(dni).getCursos().put(cursos.get(cod).getCodigo(), cursos.get(cod));
								cursos.get(cod).setProfesor(profesores.get(dni));
								System.out.println(
										profesores.get(dni).getNombre()+" ES EL PROFESOR DE "+cursos.get(cod).getNombre());
							} else {
								System.out.println("ASIGNACION CANCELADA.");
							}
						}
						if (cursos.get(cod).getProfesor() != null
								&& !dni.equals(cursos.get(cod).getProfesor().getDni())) { // Tiene profesor y es diferente al introducido
							System.out.println("EL CURSO "+cursos.get(cod)+" YA POSEE UN PROFESOR; "+cursos.get(cod).getProfesor().getNombre()+", SABIENDO ESTO");
							if (relacionar(profesores.get(dni).getNombre(), cursos.get(cod).getNombre())) {
								profesores.get(dni).getCursos().put(cursos.get(cod).getCodigo(), cursos.get(cod));
								cursos.get(cod).setProfesor(profesores.get(dni));
								System.out.println(
										"AHORA "+profesores.get(dni).getNombre()+" ES EL NUEVO PROFESOR DE "+cursos.get(cod).getNombre());
							} else {
								System.out.println("ASIGNACION CANCELADA.");
							}
						}
						if (cursos.get(cod).getProfesor() != null
								&& dni.equals(cursos.get(cod).getProfesor().getDni())) { // Tiene profesor y es el mismo al introducido
							System.out.println(
									"RELACION SUSPENDIDA DEBIDO A QUE EL DNI INTRODUCIDO CORRESPONDE AL DEL ACTUAL PROFESOR DEL CURSO");
						}
					} else {
						System.out.println(
								"RELACION SUSPENDIDA DEBIDO A QUE EL DNI INTRODUCIDO NO CORRESPONDE AL DE NINGUN PROFESOR EXISTENTE");
					}
					ModBinario.reEscribirFichero(profesores); // Reset de listas.
					ModFicherosDeTexto.reEscribirTrasBajaOMod(cursos);
					cursos = ModFicherosDeTexto.obtenerTodosLosCursos();
					profesores = ModBinario.obtenerHashDeProfes();
					break;
				case "2":
					if (cursos.get(cod).getProfesor() != null) {
						if (desRelacionar(cursos.get(cod).getProfesor().getNombre(), cursos.get(cod).getNombre())) {
							profesores.get(cursos.get(cod).getProfesor().getDni()).getCursos().remove(cod); // Quitar el curso de la lista del profesor.
							cursos.get(cod).setProfesor(null); // quitar el profesor del curso.
							System.out.println("DESASIGNACION REALIZADA CON EXITO " + cursos.get(cod).getNombre()
									+ ", YA NO TIENE PROFEOSR");

							ModBinario.reEscribirFichero(profesores); // Reset de listas.
							ModFicherosDeTexto.reEscribirTrasBajaOMod(cursos);
							cursos = ModFicherosDeTexto.obtenerTodosLosCursos();
							profesores = ModBinario.obtenerHashDeProfes();
						} else {
							System.out.println("DESASIGNACION CANCELADA.");
						}
					} else {
						System.out.println(
								"NO SE PUEDE DESASIGNAR EL PROFESOR DE ESTE CURSO YA QUE NO DISPONE DE NINGUNO");
					}
					break;
				case "0":
					break;
				default:
					System.out.println("OPCION NO VALIDA");
					break;
				}
			} while (!selec.equals("0"));
		} else {
			System.out
					.println("RELACION SUSPENDIDA DEBIDO A QUE NO HAY PROFESORES DADOS DE ALTA EN EL CENTRO EDUCATIVO");
		}
	}

	private static void relacionarCursosYAlumno(String cod) {
		Scanner sc = new Scanner(System.in);
		String selec = "";
		do {
			System.out.println(
					"PULSE 1 PARA MATRICULAR UN ALUMNO AL CURSO\n PULSE 2 DESMATRIUCLAR UN ALUMNO DE ESTE CURSO\n PULSE 0 PARA SALIR");
			selec = sc.nextLine();
			switch (selec) {
			case "1":
				;
				break;
			case "2":

				break;
			case "0":

				break;
			default:
				System.out.println("OPCION NO VALIDA");
				break;
			}
		} while (!selec.equals("0"));
	}

	private static boolean relacionar(String prof, String curs) {
		Scanner sc = new Scanner(System.in);
		String elec = null;
		System.out.println("SEGURO QUE QUIERE QUE " + prof + " IMPARTA " + curs);
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
		return false;
	}

	private static boolean desRelacionar(String persona, String curs) {
		Scanner sc = new Scanner(System.in);
		String elec = null;
		System.out.println("SEGURO QUE QUIERE QUE " + persona + " DEJE DE IMPARTIR " + curs);
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
		return false;
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
