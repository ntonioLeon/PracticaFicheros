package centroFormacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ContGestionAlumnos {

	private ArrayList<ModAlumno> listaAlumnos = new ArrayList<>();
	private ContFicherializador fich = new ContFicherializador();
	private ModValidador val = new ModValidador();

	public void alta() {

		String nombre = null, apellido = null, direc = null, telef = null;
		LocalDate naz = null;

		nombre = val.validarNombre();
		if (nombre != null) {
			apellido = val.validarApellido();
		}
		if (apellido != null && nombre != null) {
			direc = val.validarDireccion();
		}
		if (direc != null && apellido != null && nombre != null) {
			telef = val.validarTeledono();
		}
		if (telef != null && direc != null && apellido != null && nombre != null) {
			naz = val.validarFechaNacimiento();
			if (naz != null && telef != null && direc != null && apellido != null && nombre != null) {
				if (!repetido(nombre, apellido)) {
					listaAlumnos
							.add(new ModAlumno(nombre, apellido, direc, telef, naz, new HashMap<String, ModCurso>()));
					fich.getSer().guardar(listaAlumnos);
				} else {
					System.out.println("YA HAY UN ALUMNO CON EL MISMO NOMBRE Y APELLIDO");
				}
			} else {
				if (nombre == null) {
					System.out.println(
							"EL ALTA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: NOMBRE");
				} else if (apellido == null) {
					System.out.println(
							"EL ALTA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: APELLIDO");
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

	public void baja() {
		boolean borrado = false;

		System.out.println("MENU DE BAJA:");
		System.out.println("EL SIGUIENTE NOMBRE VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA BORRAR");
		String nom = val.validarNombre();
		System.out.println("EL SIGUIENTE APELLIDO VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA BORRAR");
		String ape = val.validarApellido();
		if (nom != null || ape != null) {
			ArrayList<ModAlumno> listaAlumnos = fich.getSer().cargar();
			for (int i = 0; i < listaAlumnos.size(); i++) {
				if (listaAlumnos.get(i).getNombre().equals(nom) && listaAlumnos.get(i).getApellido().equals(ape)) {
					if (siNo("borrar", "", listaAlumnos.get(i).getNombre(), "")) {
						listaAlumnos.remove(listaAlumnos.get(i));
						borrado = true;
						fich.getSer().guardar(listaAlumnos);
					}
				}

			}
			if (borrado) {
				System.out.println("BAJA REALIZADA.");
				fich.getSer().guardar(listaAlumnos);
			}
		} else {
			if (nom == null) {
				System.out.println(
						"LA BAJA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: NOMBRE");
			}
			if (ape == null) {
				System.out.println(
						"LA BAJA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: APELLIDO");
			}
		}
	}

	public void buscar() {
		System.out.println("MENU DE BUSQUEDA:");
		System.out.println("EL SIGUIENTE NOMBRE VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA BUSCAR");
		String nom = val.validarNombre();
		System.out.println("EL SIGUIENTE APELLIDO VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA BUSCAR");
		String ape = val.validarApellido();
		if (nom != null || ape != null) {
			fich.getSer().cargarUnAlumno(nom);
		} else {
			if (nom == null) {
				System.out.println(
						"LA BUSQUEDA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DNI");
			}
		}
	}

	public void modificar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DE BUSQUEDA:");
		System.out.println("EL SIGUIENTE NOMBRE VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA MODIFICAR");
		String nom = val.validarNombre();
		System.out.println("EL SIGUIENTE APELLIDO VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA MODIFICAR");
		String ape = val.validarApellido();
		if (nom != null || ape != null) {
			ArrayList<ModAlumno> listaAlumnos = fich.getSer().cargar();
			for (int i = 0; i < listaAlumnos.size(); i++) {
				if (listaAlumnos.get(i).getNombre().equals(nom) && listaAlumnos.get(i).getApellido().equals(ape)) {
					String elec = "";
					while (!elec.equals("0")) {
						System.out.println(
								"INTRODUZCA 1 PARA MODIFICAR NOMBRE\n2 PARA MODIFICAR APELLIDO\n3 PARA MODIFICAR DIRECCION\n4 PARA MODIFICAR TELEFONO\n0 PARA SALIR");
						elec = sc.nextLine();
						switch (elec) {
						case "1":
							String nombreAux = val.validarNombre();
							if (nombreAux != null) {
								if (!repetido(nombreAux, listaAlumnos.get(i).getApellido())) {
									if (siNo("Modificar", "NOMBRE", listaAlumnos.get(i).getNombre(), nombreAux)) {
										listaAlumnos.get(i).setNombre(nombreAux);
										System.out.println("MODIFICACION REALIZADA");
									} else {
										System.out.println("MODIFICACION CANCELADA");
									}
								} else {
									System.out.println(
											"NOMBRE Y APELLIDO INVALIDO YA QUE PERTENECE A OTRO ALUMNO YA EXITENTE");
								}
							} else {
								System.out.println(
										"NOMBRE INVALIDO POR DEMASIADOS VECES CONSECUTIVAS, VOLVIENDO A MENU DE MODIFICACION");
							}
							break;
						case "2":
							String apellidoAux = val.validarApellido();
							if (apellidoAux != null) {
								if (!repetido(listaAlumnos.get(i).getNombre(), apellidoAux)) {
									if (siNo("Modificar", "APELLIDO", listaAlumnos.get(i).getApellido(), apellidoAux)) {
										listaAlumnos.get(i).setApellido(apellidoAux);
										System.out.println("MODIFICACION REALIZADA");
									} else {
										System.out.println("MODIFICACION CANCELADA");
									}
								} else {
									System.out.println(
											"NOMBRE Y APELLIDO INVALIDO YA QUE PERTENECE A OTRO ALUMNO YA EXITENTE");
								}

							} else {
								System.out.println(
										"NOMBRE INVALIDO POR DEMASIADOS VECES CONSECUTIVAS, VOLVIENDO A MENU DE MODIFICACION");
							}
							break;
						case "3":
							String direc = val.validarDireccion();
							if (direc != null) {
								if (siNo("Modificar", "DIRECCION", listaAlumnos.get(i).getDireccion(), direc)) {
									listaAlumnos.get(i).setDireccion(direc);
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
								if (siNo("Modificar", "TELEFONO", listaAlumnos.get(i).getTelefono(), telef)) {
									listaAlumnos.get(i).setTelefono(telef);
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
						fich.getSer().guardar(listaAlumnos);
					}

				}
			}
		}
	}

	public void mostrarTodosLosAlumnos() {
		ArrayList<ModAlumno> listaAlumnos = fich.getSer().cargar();
		for (ModAlumno modAlumno : listaAlumnos) {
			System.out.println(modAlumno.toString());
		}
	}

	public void relacionarAlumnosConCursos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DE RELACIONAR:");
		System.out.println("EL SIGUIENTE NOMBRE VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA MODIFICAR");
		String nom = val.validarNombre();
		System.out.println("EL SIGUIENTE APELLIDO VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA MODIFICAR");
		String ape = val.validarApellido();
		String nombreCompleto = nom + ";" + ape;
		if (nom != null || ape != null) {
			HashMap<String, ModAlumno> alumnos = fich.getSer().obtenerAlumnosPorNomYApe(nombreCompleto);
			HashMap<String, ModCurso> cursos = fich.getText().obtenerTodosLosCursos();
			if (alumnos.get(nombreCompleto)!=null) {
				String elec = "";
				do {
					System.out.println(
							"INTRODUZCA 1 PARA AGREGAR UN ALUMNADO \n2 PARA ELIMINAR UN ALUMNADO \n0 PARA SALIR");
					elec = sc.nextLine();
					switch (elec) {
					case "1":						
							String cod = val.validarCodigo();
							if (cod != null) {
								if (alumnos.get(nombreCompleto).getCursos().get(cod) == null) {									
									if (relacionar(alumnos.get(nombreCompleto).getNombre(), cursos.get(cod).getNombre())) {																				
										alumnos.get(nombreCompleto).getCursos().put(cursos.get(cod).getCodigo(), cursos.get(cod));
										cursos.get(cod).setAlumnos(alumnos);
										System.out.println(alumnos.get(nombreCompleto).getNombre()+ " AHORA ES EL PROFESOR DE "+alumnos.get(nombreCompleto).getCursos().get(cod).getNombre());

										fich.getSer().guardar(alumnos);
										fich.getText().reEscribirTrasBajaOMod(cursos);
										alumnos = fich.getSer().obtenerAlumnosPorNomYApe(nombreCompleto);
										cursos = fich.getText().obtenerTodosLosCursos();
									} else {										
										System.out.println(alumnos.get(nombreCompleto).getNombre()+ " YA NO ASISTIRÁ A CLASES DE "+alumnos.get(nombreCompleto).getCursos().get(cod).getNombre());
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
						if (!alumnos.get(nombreCompleto).getCursos().isEmpty()) {
							cod = val.validarCodigo();
							if (cod != null) {
								if (alumnos.get(nombreCompleto).getCursos().get(cod) != null) {
									if (desRelacionar(alumnos.get(nombreCompleto).getNombre(), alumnos.get(nombreCompleto).getCursos().get(cod).getNombre())) {
										alumnos.get(nombreCompleto).getCursos().remove(cod);										
										cursos.get(cod).setProfesor(null);
										System.out.println(alumnos.get(nombreCompleto).getNombre()+ " YA NO ES EL PROFESOR DE "+cursos.get(cod).getNombre());

										fich.getSer().guardar(alumnos);
										fich.getText().reEscribirTrasBajaOMod(cursos);
										alumnos = fich.getSer().obtenerAlumnosPorNomYApe(nombreCompleto);
										cursos = fich.getText().obtenerTodosLosCursos();
									} else {										
										System.out.println(alumnos.get(nombreCompleto).getNombre()+ " SEGUIRA ASISTIENDO A CLASES DE "+alumnos.get(nombreCompleto).getCursos().get(cod).getNombre());
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

	private boolean repetido(String Nombre, String Apellido) {
		listaAlumnos = fich.getSer().cargar();
		if (!listaAlumnos.isEmpty()) {
			for (ModAlumno modAlumno : listaAlumnos) {
				if (modAlumno.getNombre().equals(Nombre) && modAlumno.getApellido().equals(Apellido)) {
					return true;
				}

			}
		}
		return false;
	}

	private boolean relacionar(String alum, String curs) {
		Scanner sc = new Scanner(System.in);
		String elec = null;
		System.out.println("SEGURO QUE QUIERE QUE " + alum + " ASISTA " + curs);
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
		System.out.println("SEGURO QUE QUIERE QUE " + persona + " DEJE DE ASISTIR A " + curs);
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
