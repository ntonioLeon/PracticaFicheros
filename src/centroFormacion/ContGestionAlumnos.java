package centroFormacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ContGestionAlumnos {

	public static ArrayList<ModAlumno> listaAlumnos = new ArrayList<>();

	public static void alta() {

		String nombre = null, apellido = null, direc = null, telef = null;
		LocalDate naz = null;

		nombre = ModValidador.validarNombre();
		if (nombre != null) {
			apellido = ModValidador.validarApellido();
		}
		if (apellido != null && nombre != null) {
			direc = ModValidador.validarDireccion();
		}
		if (direc != null && apellido != null && nombre != null) {
			telef = ModValidador.validarTeledono();
		}
		if (telef != null && direc != null && apellido != null && nombre != null) {
			naz = ModValidador.validarFechaNacimiento();
			if (naz != null && telef != null && direc != null && apellido != null && nombre != null) {
				
				if(!repetido(nombre,apellido)) {
					listaAlumnos.add(new ModAlumno(nombre, apellido, direc, telef, naz, new HashMap<String, ModCurso>()));
					ModSerializado.guardar(listaAlumnos);
				}else {
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

	public static void baja() {
		boolean borrado = false; 
		
		System.out.println("MENU DE BAJA:");
		System.out.println("EL SIGUIENTE NOMBRE VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA BORRAR");
		String nom = ModValidador.validarNombre();
		System.out.println("EL SIGUIENTE APELLIDO VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA BORRAR");
		String ape = ModValidador.validarApellido();
		if (nom != null || ape != null) {
			ArrayList<ModAlumno> listaAlumnos = ModSerializado.cargar();
			for (int i = 0; i < listaAlumnos.size();i++) {
				if (listaAlumnos.get(i).getNombre().equals(nom) && listaAlumnos.get(i).getApellido().equals(ape)) {
					if (siNo("borrar", "", listaAlumnos.get(i).getNombre(), "")) {
						listaAlumnos.remove(listaAlumnos.get(i));
						borrado = true;
						System.out.println("BAJA REALIZADA.");
						ModSerializado.guardar(listaAlumnos);
					}
				}
				
			}
			if (borrado) {
				ModSerializado.guardar(listaAlumnos);
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

	public static void buscar() {
		System.out.println("MENU DE BUSQUEDA:");
		System.out.println("EL SIGUIENTE NOMBRE VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA BUSCAR");
		String nom = ModValidador.validarNombre();
		System.out.println("EL SIGUIENTE APELLIDO VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA BUSCAR");
		String ape = ModValidador.validarApellido();
		if (nom != null || ape != null) {
			ModSerializado.cargarUnAlumno(nom);
		} else {
			if (nom == null) {
				System.out.println(
						"LA BUSQUEDA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: DNI");
			}
		}
	}

	public static void modificar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DE BUSQUEDA:");
		System.out.println("EL SIGUIENTE NOMBRE VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA MODIFICAR");
		String nom = ModValidador.validarNombre();
		System.out.println("EL SIGUIENTE APELLIDO VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA MODIFICAR");
		String ape = ModValidador.validarApellido();
		if (nom != null || ape != null) {
			ArrayList<ModAlumno> listaAlumnos = ModSerializado.cargar();
			for (int i = 0; i < listaAlumnos.size();i++) {
				if (listaAlumnos.get(i).getNombre().equals(nom) && listaAlumnos.get(i).getApellido().equals(ape)) {
					String elec = "";
					while (!elec.equals("0")) {
						System.out.println(
								"INTRODUZCA 1 PARA MODIFICAR NOMBRE\n2 PARA MODIFICAR APELLIDO\n3 PARA MODIFICAR DIRECCION\n4 PARA MODIFICAR TELEFONO\n0 PARA SALIR");
						elec = sc.nextLine();
						switch (elec) {
						case "1":
							String NombreAux = ModValidador.validarNombre();
							if (NombreAux != null) {
								if (!repetido(NombreAux,listaAlumnos.get(i).getApellido())) {
									if (siNo("Modificar", "NOMBRE", listaAlumnos.get(i).getNombre(), NombreAux)) {
										listaAlumnos.get(i).setNombre(NombreAux);
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
							String ApellidoAux = ModValidador.validarApellido();
							if (ApellidoAux != null) {
								if (!repetido(listaAlumnos.get(i).getNombre(),ApellidoAux)) {
									if (siNo("Modificar", "APELLIDO", listaAlumnos.get(i).getApellido(), ApellidoAux)) {
										listaAlumnos.get(i).setApellido(ApellidoAux);
										System.out.println("MODIFICACION REALIZADA");
									} else {
										System.out.println("MODIFICACION CANCELADA");
									}
								}
								else {
									System.out.println(
											"NOMBRE Y APELLIDO INVALIDO YA QUE PERTENECE A OTRO ALUMNO YA EXITENTE");
								}
								
							} else {
								System.out.println(
										"NOMBRE INVALIDO POR DEMASIADOS VECES CONSECUTIVAS, VOLVIENDO A MENU DE MODIFICACION");
							}
							break;
						case "3":
							String direc = ModValidador.validarDireccion();
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
							String telef = ModValidador.validarTeledono();
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
						ModSerializado.guardar(listaAlumnos);
					}
						
				}
			}
		}
	}
	
	public static void mostrarTodosLosAlumnos() {
		ArrayList<ModAlumno> listaAlumnos = ModSerializado.cargar();
		for (ModAlumno modAlumno : listaAlumnos) {
			System.out.println(modAlumno.toString());
		}
	}
	
	public static void relacionarProfesoresConCursos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DE RELACIONAR:");
		System.out.println("EL SIGUIENTE NOMBRE VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA MODIFICAR");
		String nom = ModValidador.validarNombre();
		System.out.println("EL SIGUIENTE APELLIDO VALDRA PARA IDENTIFICAR AL ALUMNO QUE DESEA MODIFICAR");
		String ape = ModValidador.validarApellido();
		if (nom != null || ape != null) {
			ArrayList<ModAlumno> listaAlumnos = ModSerializado.cargar();
			HashMap<String, ModCurso> cursos = ModFicherosDeTexto.obtenerTodosLosCursos();
			ModAlumno alumno = ModSerializado.obtenerUnAlumno(nom,ape);
			if (alumno != null) {
				String elec = "";
				do {
					System.out.println(
							"INTRODUZCA 1 PARA AGREGAR UN ALUMNADO \n2 PARA ELIMINAR UN ALUMNADO \n0 PARA SALIR");
					elec = sc.nextLine();
					switch (elec) {
					case "1":						
							String cod = ModValidador.validarCodigo();
							if (cod != null) {
								if (alumno.getCursos().get(cod) == null) {									
									if (relacionar(alumno.getNombre(), cursos.get(cod).getNombre())) {																				
										alumno.getCursos().put(cursos.get(cod).getCodigo(), cursos.get(cod));
										cursos.get(cod).setAlumnos(alumno);
										System.out.println(profesores.get(dni).getNombre()+ " AHORA ES EL PROFESOR DE "+profesores.get(dni).getCursos().get(cod).getNombre());

										ModBinario.reEscribirFichero(profesores);
										ModFicherosDeTexto.reEscribirTrasBajaOMod(cursos);
										profesores = ModBinario.obtenerHashDeProfes();
										cursos = ModFicherosDeTexto.obtenerTodosLosCursos();
									} else {										
										System.out.println(profesores.get(dni).getNombre()+ " YA NO SERA EL PROFESOR DE "+profesores.get(dni).getCursos().get(cod).getNombre());
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
							cod = ModValidador.validarCodigo();
							if (cod != null) {
								if (profesores.get(dni).getCursos().get(cod) != null) {
									if (desRelacionar(profesores.get(dni).getNombre(), profesores.get(dni).getCursos().get(cod).getNombre())) {
										profesores.get(dni).getCursos().remove(cod);										
										cursos.get(cod).setProfesor(null);
										System.out.println(profesores.get(dni).getNombre()+ " YA NO ES EL PROFESOR DE "+cursos.get(cod).getNombre());

										ModBinario.reEscribirFichero(profesores);
										ModFicherosDeTexto.reEscribirTrasBajaOMod(cursos);
										profesores = ModBinario.obtenerHashDeProfes();
										cursos = ModFicherosDeTexto.obtenerTodosLosCursos();
									} else {										
										System.out.println(profesores.get(dni).getNombre()+ " SEGUIRA SIENDO ES EL PROFESOR DE "+profesores.get(dni).getCursos().get(cod).getNombre());
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
	
	private static boolean repetido(String Nombre, String Apellido) {
		listaAlumnos = ModSerializado.cargar();
		for (ModAlumno modAlumno : listaAlumnos) {
			if (modAlumno.getNombre().equals(Nombre) &&modAlumno.getApellido().equals(Apellido)) {
				return true;
			}
			
		}
		return false;
	}

	private static boolean relacionar(String alum, String curs) {
		Scanner sc = new Scanner(System.in);
		String elec = null;
		System.out.println("SEGURO QUE QUIERE QUE "+alum +" ASISTIR "+curs);
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
		System.out.println("SEGURO QUE QUIERE QUE "+persona+" DEJE DE ASISTIR "+curs);
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
