package centroFormacion;

import java.util.HashMap;
import java.util.Scanner;

public class ContGestionProfesores {

	private ContFicherializador fich = new ContFicherializador();
	private ModValidador val = new ModValidador();

	public void alta() {
		String dni = null, nombre = null, direc = null, telef = null;

		dni = val.validarDNI(1);
		if (dni != null) {
			nombre = val.validarNombre();
		}
		if (nombre != null && dni != null) {
			direc = val.validarDireccion();
		}
		if (direc != null && nombre != null && dni != null) {
			telef = val.validarTeledono();
		}
		if (telef != null && direc != null && nombre != null && dni != null) {
			// Se procede al alta
			fich.getBin().guardar(new ModProfesor(dni, nombre, direc, telef, new HashMap<String, ModCurso>()));
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

	public void baja() {
		System.out.println("MENU DE BAJA:");
		System.out.println("EL SIGUIENTE DNI VALDRA PARA IDENTIFICAR AL PROFESOR QUE DESEA BORRAR");
		String dni = val.validarDNI(2);
		if (dni != null) {
			HashMap<String, ModProfesor> profesores = fich.getBin().obtenerHashDeProfes();
			System.out.println(profesores);
			if (profesores.get(dni) != null) {
				if (siNo("borrar", "", profesores.get(dni).getNombre(), "")) {
					profesores.remove(dni);
					fich.getText().borrarDeProfesoresEnCascade(profesores.get(dni));
					fich.getBin().reEscribirFichero(profesores);
					System.out.println("BAJA REALIZADA.");
				}
			} else {
				System.out.println("LA BAJA NO SE PUDO REALIZAR YA QUE EL DNI INTRODUCIDO NO PERTENECE A NINGUN PROFESOR");
			}
		} else {
			if (dni == null) {
				System.out.println(
						"LA BAJA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DNI");
			}
		}
	}

	public void modificar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DE MODIFICAR:");
		System.out.println("EL SIGUIENTE DNI VALDRA PARA IDENTIFICAR PROFESOR QUE DESEA MODIFICAR");
		String dni = val.validarDNI(2);
		if (dni != null) {
			HashMap<String, ModProfesor> profesores = fich.getBin().obtenerHashDeProfes();
			if (profesores.get(dni) != null) {
				String elec = "";
				do {
					System.out.println(
							"INTRODUZCA 1 PARA MODIFICAR DNI\n2 PARA MODIFICAR NOMBRE\n3 PARA MODIFICAR DIRECCION\n4 PARA MODIFICAR TELEFONO\n0 PARA SALIR");
					elec = sc.nextLine();
					switch (elec) {
					case "1":
						String dniAux = val.validarDNI(1);
						if (dniAux != null) {
							if (profesores.get(dniAux) == null) {
								if (siNo("Modificar", "DNI", profesores.get(dni).getDni(), dniAux)) {
									profesores.get(dni).setDni(dniAux);
									profesores.put(dniAux, profesores.get(dni));
									profesores.remove(dni);
									dni = dniAux;
									System.out.println("MODIFICACION REALIZADA");
								} else {
									System.out.println("MODIFICACION CANCELADA");
								}
							} else {
								System.out.println("DNI INVALIDO YA QUE PERTENECE A OTRO PROFESOR YA EXITENTE");
							}
						} else {
							System.out.println(
									"DNI INVALIDO POR DEMASIADOS VECES CONSECUTIVAS, VOLVIENDO A MENU DE MODIFICACION");
						}
						break;
					case "2":
						String nomb = val.validarNombre();
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
						String direc = val.validarDireccion();
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
						String telef = val.validarTeledono();
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
					fich.getBin().reEscribirFichero(profesores);
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

	public void buscar() {
		System.out.println("MENU DE BUSQUEDA:");
		System.out.println("EL SIGUIENTE DNI VALDRA PARA IDENTIFICAR AL PROFESOR QUE DESEA BUSCAR");
		String dni = val.validarDNI(2);
		if (dni != null) {
			fich.getBin().mostrarUnProfesor(dni);
		} else {
			if (dni == null) {
				System.out.println(
						"LA BUSQUEDA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DNI");
			}
		}
	}

	public void mostrarTodosLosProfesores() {
		fich.getBin().mostrarContenidoDelFichero();
	}

	public void relacionarProfesoresConCursos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DE RELACIONAR:");
		System.out.println("EL SIGUIENTE DNI VALDRA PARA IDENTIFICAR PROFESOR QUE DESEA RELACIONAR");
		String dni = val.validarDNI(2);
		if (dni != null) {
			HashMap<String, ModProfesor> profesores = fich.getBin().obtenerHashDeProfes();
			HashMap<String, ModCurso> cursos = fich.getText().obtenerTodosLosCursos();
			if (profesores.get(dni) != null) {
				String elec = "";
				do {
					System.out.println(
							"INTRODUZCA 1 PARA AGREGAR UNA DOCENCIA\n2 PARA ELIMINAR UNA DOCENCIA \n0 PARA SALIR");
					elec = sc.nextLine();
					switch (elec) {
					case "1":
						String cod = val.validarCodigo();
						if (cod != null) {
							if (profesores.get(dni).getCursos().get(cod) == null) {
								if (relacionar(profesores.get(dni).getNombre(), cursos.get(cod).getNombre())) {
									profesores.get(dni).getCursos().put(cursos.get(cod).getCodigo(), cursos.get(cod));
									cursos.get(cod).setProfesor(profesores.get(dni));
									System.out.println(profesores.get(dni).getNombre() + " AHORA ES EL PROFESOR DE "
											+ profesores.get(dni).getCursos().get(cod).getNombre());

									fich.getBin().reEscribirFichero(profesores);
									fich.getText().reEscribirTrasBajaOMod(cursos);
									profesores = fich.getBin().obtenerHashDeProfes();
									cursos = fich.getText().obtenerTodosLosCursos();
								} else {
									System.out.println(profesores.get(dni).getNombre() + " YA NO SERA EL PROFESOR DE "
											+ profesores.get(dni).getCursos().get(cod).getNombre());
								}
							} else {
								System.out.println(
										"LA RELACION NO SE PUDO REALIZAR DEBIDO A QUE EL CODIGO INTRODUCIDO YA EXITENTE DENTRO DE LAS DOCENCIAS DEL PROFESOR");
							}
						} else {
							System.out.println(
									"LA RELACION NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: CODIGO");
						}
						break;
					case "2":
						if (!profesores.get(dni).getCursos().isEmpty()) {
							cod = val.validarCodigo();
							if (cod != null) {
								if (profesores.get(dni).getCursos().get(cod) != null) {
									if (desRelacionar(profesores.get(dni).getNombre(),
											profesores.get(dni).getCursos().get(cod).getNombre())) {
										profesores.get(dni).getCursos().remove(cod);
										cursos.get(cod).setProfesor(null);
										System.out.println(profesores.get(dni).getNombre() + " YA NO ES EL PROFESOR DE "
												+ cursos.get(cod).getNombre());

										fich.getBin().reEscribirFichero(profesores);
										fich.getText().reEscribirTrasBajaOMod(cursos);
										profesores = fich.getBin().obtenerHashDeProfes();
										cursos = fich.getText().obtenerTodosLosCursos();
									} else {
										System.out.println(
												profesores.get(dni).getNombre() + " SEGUIRA SIENDO ES EL PROFESOR DE "
														+ profesores.get(dni).getCursos().get(cod).getNombre());
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

	private boolean siNo(String modalidad, String campo, String antiguo, String nuevo) {
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
		} else if (modalidad.equalsIgnoreCase("borrar")) {
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

	private boolean relacionar(String prof, String curs) {
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

	private boolean desRelacionar(String persona, String curs) {
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
}
