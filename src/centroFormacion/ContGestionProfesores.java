package centroFormacion;

import java.util.HashMap;
import java.util.Scanner;

public class ContGestionProfesores {

	public static void alta() {
		String dni = null, nombre = null, direc = null, telef = null;

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
		String dni = ModValidador.validarDNI(2);
		if (dni != null) {
			HashMap<String, ModProfesor> profesores = ModBinario.obtenerHashDeProfes();
			if (siNo("borrar", "", profesores.get(dni).getNombre(), "")) {
				profesores.remove(dni);
				ModFicherosDeTexto.borrarDeProfesoresEnCascade(profesores.get(dni));
				System.out.println("BAJA REALIZADA.");
				ModBinario.reEscribirFichero(profesores);
			}
		} else {
			if (dni == null) {
				System.out.println(
						"LA BAJA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DNI");
			}
		}
	}

	public static void modificar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DE MODIFICAR:");
		System.out.println("EL SIGUIENTE DNI VALDRA PARA IDENTIFICAR PROFESOR QUE DESEA MODIFICAR");
		String dni = ModValidador.validarDNI(2);
		if (dni != null) {
			HashMap<String, ModProfesor> profesores = ModBinario.obtenerHashDeProfes();
			if (profesores.get(dni) != null) {
				String elec = "";
				do {
					System.out.println(
							"INTRODUZCA 1 PARA MODIFICAR DNI\n2 PARA MODIFICAR NOMBRE\n3 PARA MODIFICAR DIRECCION\n4 PARA MODIFICAR TELEFONO\n0 PARA SALIR");
					elec = sc.nextLine();
					switch (elec) {
					case "1":
						String dniAux = ModValidador.validarDNI(1);
						if (dniAux != null) {
							if (ModBinario.comprobarDNI(dniAux)) {
								if (siNo("Modificar", "DNI", profesores.get(dni).getDni(), dniAux)) {
									profesores.get(dni).setDni(dniAux);
									System.out.println("MODIFICACION REALIZADA");
									dni = dniAux;
								} else {
									System.out.println("MODIFICACION CANCELADA");
								}
							} else {
								System.out.println(
										"DNI INVALIDO YA QUE PERTENECE A OTRO PROFESOR YA EXITENTE");
							}
						} else {
							System.out.println(
									"DNI INVALIDO POR DEMASIADOS VECES CONSECUTIVAS, VOLVIENDO A MENU DE MODIFICACION");
						}
						break;
					case "2":
						String nomb = ModValidador.validarNombre();
						if (nomb != null) {
							if (siNo("Modificar", "NOMBRE", profesores.get(dni).getNombre(), nomb)) {
								profesores.get(dni).setNombre(nomb);
								System.out.println("MODIFICACION REALIZADA");
							} else {
								System.out.println("MODIFICACION CANCELADA");
							}
						} else {
							System.out.println(
									"NOMBRE INVALIDO POR DEMASIADOS VECES CONSECUTIVAS, VOLVIENDO A MENU DE MODIFICACION");
						}
						break;
					case "3":
						String direc = ModValidador.validarDireccion();
						if (direc != null) {
							if (siNo("Modificar", "DIRECCION", profesores.get(dni).getDireccion(), direc)) {
								profesores.get(dni).setDireccion(direc);
								System.out.println("MODIFICACION REALIZADA");
							} else {
								System.out.println("MODIFICACION CANCELADA");
							}
						} else {
							System.out.println(
									"DIRECCION INVALIDA POR DEMASIADOS VECES CONSECUTIVAS, VOLVIENDO A MENU DE MODIFICACION");
						}
						break;
					case "4":
						String telef = ModValidador.validarTeledono();
						if (telef != null) {
							if (siNo("Modificar", "TELEFONO", profesores.get(dni).getTelefono(), telef)) {
								profesores.get(dni).setTelefono(telef);
								System.out.println("MODIFICACION REALIZADA");
							} else {
								System.out.println("MODIFICACION CANCELADA");
							}
						} else {
							System.out.println(
									"TELEFONO INVALIDO POR DEMASIADOS VECES CONSECUTIVAS, VOLVIENDO A MENU DE MODIFICACION");
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
			} else {
				System.out.println(
						"LA MODIFICACION NO SE PUDO REALIZAR DEBIDO A QUE EL DNI INTRODUCIDO NO SE CORRESPONDE CON EL DE NINGUN DOCENTE");
			}
		} else {
			System.out.println(
					"LA MODIFICACION NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DNI");
		}
	}

	public static void buscar() {
		System.out.println("MENU DE BUSQUEDA:");
		System.out.println("EL SIGUIENTE DNI VALDRA PARA IDENTIFICAR AL PROFESOR QUE DESEA BUSCAR");
		String dni = ModValidador.validarDNI(2);
		if (dni != null) {
			ModBinario.mostrarUnProfesor(dni);
		} else {
			if (dni == null) {
				System.out.println(
						"LA BUSQUEDA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DNI");
			}
		}
	}

	public static void mostrarTodosLosProfesores() {
		ModBinario.mostrarContenidoDelFichero();
	}

	public static void relacionarProfesoresConCursos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DE RELACIONAR:");
		System.out.println("EL SIGUIENTE DNI VALDRA PARA IDENTIFICAR PROFESOR QUE DESEA RELACIONAR");
		String dni = ModValidador.validarDNI(2);
		if (dni != null) {
			HashMap<String, ModProfesor> profesores = ModBinario.obtenerHashDeProfes();
			if (profesores.get(dni) != null) {
				String elec = "";
				do {
					System.out.println(
							"INTRODUZCA 1 PARA AGREGAR UNA DOCENCIA\n2 PARA ELIMINAR UNA DOCENCIA \n0 PARA SALIR");
					elec = sc.nextLine();
					switch (elec) {
					case "1":						
							String cod = ModValidador.validarCodigo();
							if (cod != null) {
								if (profesores.get(dni).getCursos().get(cod) != null) {
									if (relacionar(profesores.get(dni), profesores.get(dni).getCursos().get(cod))) {
										HashMap<String, ModCurso> cursos = ModFicherosDeTexto.obtenerTodosLosCursos();										
										profesores.get(dni).getCursos().put(cod, cursos.get(cod));
										cursos.get(cod).setProfesor(profesores.get(dni));
										System.out.println(profesores.get(dni).getNombre()+ "YA NO ES EL PROFESOR DE "+profesores.get(dni).getCursos().get(cod).getNombre());
									} else {										
										System.out.println(profesores.get(dni).getNombre()+ "SEGUIRA SIENDO ES EL PROFESOR DE "+profesores.get(dni).getCursos().get(cod).getNombre());
									}
								} else {
									System.out.println(
											"LA RELACION NO SE PUDO REALIZAR DEBIDO A QUE EL CODIGO INTRODUCIDO NO SE CORRESPONDE CON EL DE NINGUN CURSO IMPARTIDO POR EL DOCENTE");
								}
							} else {
								System.out.println(
										"LA RELACION NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: CODIGO");
							}
						break;
					case "2":
						if (!profesores.get(dni).getCursos().isEmpty()) {
							cod = ModValidador.validarCodigo();
							if (cod != null) {
								if (profesores.get(dni).getCursos().get(cod) != null) {
									if (desRelacionar(profesores.get(dni), profesores.get(dni).getCursos().get(cod))) {
										profesores.get(dni).getCursos().remove(cod);
										HashMap<String, ModCurso> cursos = ModFicherosDeTexto.obtenerTodosLosCursos();
										cursos.get(cod).setProfesor(null);
										cursos = null;
										System.out.println(profesores.get(dni).getNombre()+ "YA NO ES EL PROFESOR DE "+profesores.get(dni).getCursos().get(cod).getNombre());
									} else {										
										System.out.println(profesores.get(dni).getNombre()+ "SEGUIRA SIENDO ES EL PROFESOR DE "+profesores.get(dni).getCursos().get(cod).getNombre());
									}
								} else {
									System.out.println(
											"LA RELACION NO SE PUDO REALIZAR DEBIDO A QUE EL CODIGO INTRODUCIDO NO SE CORRESPONDE CON EL DE NINGUN CURSO IMPARTIDO POR EL DOCENTE");
								}
							} else {
								System.out.println(
										"LA RELACION NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: CODIGO");
							}
						} else {
							System.out
									.println("NO SE PUEDE ELIMINAR UN CURSO DE UN PROFESOR SI ESTE NO IMPARTE NINGUNO");
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
			} else {
				System.out.println(
						"LA RELACION NO SE PUDO REALIZAR DEBIDO A QUE EL DNI INTRODUCIDO NO SE CORRESPONDE CON EL DE NINGUN DOCENTE");
			}
		} else {
			System.out.println(
					"LA RELACION NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DNI");
		}
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
		} else if (modalidad.equalsIgnoreCase("borrarr")) {
			System.out.println("SEGURO QUE DESEA " + modalidad.toUpperCase() + " A " + antiguo.toUpperCase()
					+ " DE SU LISTA DE PROFESORES?");
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
	
	private static boolean relacionar(ModProfesor prof, ModCurso curs) {
		Scanner sc = new Scanner(System.in);
		String elec = null;
		System.out.println("SEGURO QUE QUIERE QUE "+prof.getNombre()+" IMPARTA "+curs.getNombre());
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
	
	private static boolean desRelacionar(ModProfesor prof, ModCurso curs) {
		Scanner sc = new Scanner(System.in);
		String elec = null;
		System.out.println("SEGURO QUE QUIERE QUE "+prof.getNombre()+" DEJE DE IMPARTIR "+curs.getNombre());
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
}
