package centroFormacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ContGestionCursos {
	
	private ContFicherializador fich = new ContFicherializador();
	private ModValidador val = new ModValidador();
	
	public void alta() {
		String nomb = null, descrip = null;
		nomb = val.validarNombre();
		if (nomb != null) {
			descrip = val.validarDescripcion();
		}
		if (nomb != null && descrip != null) {
			fich.getText().escribirCurso(new ModCurso(nomb, descrip));
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

	public void baja() {
		System.out.println("MENU DE BAJA:");
		System.out.println("EL SIGUIENTE CODIGO VALDRA PARA IDENTIFICAR CURSO QUE DESEA BORRAR");
		String cod = val.validarCodigo();
		if (cod != null) {
			HashMap<String, ModCurso> cursos = fich.getText().obtenerTodosLosCursos();
			if (cursos.get(cod) != null) {
				if (siNo("borrar", "", cursos.get(cod).getNombre() + " CON CODIGO: " + cursos.get(cod).getCodigo(),
						"")) {
					fich.getBin().borradoDeCursoEnCascada(cod);
					fich.getSer().borrarDeCursoEnCascada(cod);
					cursos.remove(cod);
					System.out.println("CURSO BORRADO");
					fich.getText().reEscribirTrasBajaOMod(cursos);
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

	public void modificar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DE MODIFICAR:");
		System.out.println("EL SIGUIENTE CODIGO VALDRA PARA IDENTIFICAR CURSO QUE DESEA RMODIFICARRAR");
		String cod = val.validarCodigo();
		if (cod != null) {
			HashMap<String, ModCurso> cursos = fich.getText().obtenerTodosLosCursos();
			System.out.println(cursos.size());
			if (cursos.get(cod) != null) {
				String elec = "";
				do {
					System.out.println("INTODUZCA 1 PARA MODIFICAR NOMBRE\n2 PARA MODIFICAR DESCRIPCION\n0 PARA SALIR");
					elec = sc.nextLine();
					switch (elec) {
					case "1":
						String nomb = val.validarNombre();
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
						String desc = val.validarDescripcion();
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
				fich.getText().reEscribirTrasBajaOMod(cursos);
			} else {
				System.out.println(
						"LA MODIFICACION NO SE PUDO REALIZAR DEBIDO A QUE NO EXISTE NINGUN CURSO CON EL CODIGO QUE SE PROPORCIONO");
			}
		} else {
			System.out.println(
					"LA MODIFICACION NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: CODIGO");
		}
	}

	public void buscar() {
		System.out.println("MENU DE BUSQUEDA:");
		System.out.println("EL SIGUIENTE CODIGO VALDRA PARA IDENTIFICAR CURSO QUE DESEA BUSCAR");
		String cod = val.validarCodigo();
		if (cod != null) {
			fich.getText().buscarPorCodigo(cod);
		} else {
			System.out.println(
					"LA BUSQUEDA NO SE PUDO REALIZAR DEBIDO A QUE SE FALLARON 5 VECES CONSECUTIVAS EN EL CAMPO: CODIGO");
		}
	}

	public void mostrarTodosLosProfesores() {
		fich.getText().mostrarTodos();
	}

	public void relacionarCursos() {
		System.out.println("MENU DE RELACION DE CURSOS");
		System.out.println("EL SIGUIENTE CODIGO VALDRA PARA IDENTIFICAR CURSO QUE DESEA RELACUIONAR");
		String cod = val.validarCodigo();
		if (cod != null) {
			HashMap<String, ModCurso> cursos = fich.getText().obtenerTodosLosCursos();
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

	private void relacionarCursosYProfesores(String cod) {
		HashMap<String, ModCurso> cursos = fich.getText().obtenerTodosLosCursos();
		HashMap<String, ModProfesor> profesores = fich.getBin().obtenerHashDeProfes();
		Scanner sc = new Scanner(System.in);
		String selec = "", dni = "";
		if (!profesores.isEmpty()) {
			do {
				System.out.println(
						"PULSE 1 PARA ASIGNAR UN DOCENTE AL CURSO\n PULSE 2 RETIRAR EL DOCENTE DE ESTE CURSO\n PULSE 0 PARA SALIR");
				selec = sc.nextLine();
				switch (selec) {
				case "1":
					System.out.println(
							"INTRODUZCA EL DNI DEL PROFESOR QUE DESEA ASGINAR A " + cursos.get(cod).getNombre());
					dni = val.validarDNI(2);
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
					fich.getBin().reEscribirFichero(profesores); // Reset de listas.
					fich.getText().reEscribirTrasBajaOMod(cursos);
					cursos = fich.getText().obtenerTodosLosCursos();
					profesores = fich.getBin().obtenerHashDeProfes();
					break;
				case "2":
					if (cursos.get(cod).getProfesor() != null) {
						if (desRelacionar(cursos.get(cod).getProfesor().getNombre(), cursos.get(cod).getNombre())) {
							profesores.get(cursos.get(cod).getProfesor().getDni()).getCursos().remove(cod); // Quitar el curso de la lista del profesor.
							cursos.get(cod).setProfesor(null); // quitar el profesor del curso.
							System.out.println("DESASIGNACION REALIZADA CON EXITO " + cursos.get(cod).getNombre()
									+ ", YA NO TIENE PROFEOSR");

							fich.getBin().reEscribirFichero(profesores); // Reset de listas.
							fich.getText().reEscribirTrasBajaOMod(cursos);
							cursos = fich.getText().obtenerTodosLosCursos();
							profesores = fich.getBin().obtenerHashDeProfes();
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

	private void relacionarCursosYAlumno(String cod) {
		Scanner sc = new Scanner(System.in);
		HashMap<String, ModCurso> cursos = fich.getText().obtenerTodosLosCursos();
		HashMap<String, ModAlumno> alumnos = fich.getSer().cargarEnHash();
		String selec = "", nom = "", ape = "";
		do {
			System.out.println(
					"PULSE 1 PARA MATRICULAR UN ALUMNO AL CURSO\n PULSE 2 DESMATRIUCLAR UN ALUMNO DE ESTE CURSO\n PULSE 0 PARA SALIR");
			selec = sc.nextLine();
			switch (selec) {
			case "1":
				System.out.println("EL SIGUIENTE NOMBRE VALDRA PARA IDENTIFICAR EL ALUMNO AL QUE DESEA RELACIONAR CON EL CURSO");		
				nom = val.validarNombre();
				if (nom != null) {
				System.out.println("EL SIGUIENTE APELLIDO VALDRA PARA IDENTIFICAR EL ALUMNO AL QUE DESEA RELACIONAR CON EL CURSO");
				ape = val.validarApellido();				
				}
				if (nom != null && ape != null) {
					String key = nom+"_"+ape;
					if (alumnos.get(key) != null) {
						if (alumnos.get(key).getCursos().get(cod) == null) {
							if (relacionar2(alumnos.get(key).getNombre(), cursos.get(cod).getNombre())) {
								System.out.println("AHORA "+alumnos.get(key).getNombre()+" ES ALUMNO DE "+cursos.get(cod).getNombre());
								cursos.get(cod).getAlumnos().put(key, alumnos.get(key));
								alumnos.get(key).getCursos().put(cod, cursos.get(cod));
								
								fich.getSer().guardar(alumnos); // Reset de listas.
								fich.getText().reEscribirTrasBajaOMod(cursos);
								cursos = fich.getText().obtenerTodosLosCursos();
								alumnos = fich.getSer().cargarEnHash();
							}
						} else {
							System.out.println("EL ALUMNO YA PERTENECE AL CURSO "+cursos.get(cod).getNombre());
						}
					} else {
						System.out.println("NO EXISTE UN ALUMNO CON ESOS NOMBRES Y APELLIDOS");
					}
				} else {
					if (nom == null) {
						System.out.println("RELACION SUSPENDIDA DEBIDO A QUE SE FALLO EN EL CAMPO NOMBRE 5 VECES");
					} else if (ape == null) {
						System.out.println("RELACION SUSPENDIDA DEBIDO A QUE SE FALLO EN EL CAMPO APELLIDO 5 VECES");
					}
				}
				break;
			case "2":
				if (!cursos.get(cod).getAlumnos().isEmpty()) {
					System.out.println("EL SIGUIENTE NOMBRE VALDRA PARA IDENTIFICAR EL ALUMNO AL QUE DESEA DESMATRICULAR DEL CURSO");		
					nom = val.validarNombre();
					if (nom != null) {
						System.out.println("EL SIGUIENTE APELLIDO VALDRA PARA IDENTIFICAR EL ALUMNO AL QUE DESEA DESMATRICULAR DEL CURSO");
						ape = val.validarApellido();
					}
					if (nom != null && ape != null) {
						String key = nom+"_"+ape;
						if (alumnos.get(key) != null) {
							if (cursos.get(cod).getAlumnos().get(key) != null) {
								if (desRelacionar2(alumnos.get(key).getNombre(), cursos.get(cod).getNombre())) {
									System.out.println(alumnos.get(key).getNombre()+" YA NO ES ALUMNO DE "+cursos.get(cod).getNombre());
									cursos.get(cod).getAlumnos().remove(key);
									alumnos.get(key).getCursos().remove(cod);
									
									fich.getSer().guardar(alumnos); // Reset de listas.
									fich.getText().reEscribirTrasBajaOMod(cursos);
									cursos = fich.getText().obtenerTodosLosCursos();
									alumnos = fich.getSer().cargarEnHash();
								}
							} else {
								System.out.println("NO SE PUEDE DESMATRICULAR UN ALUMNO DE UN CURSO AL QUE NO PERTENECE");
							}
						} else {
							System.out.println("NO EXISTE UN ALUMNO CON ESOS NOMBRES Y APELLIDOS");
						}
					} else {
						if (nom == null) {
							System.out.println("RELACION SUSPENDIDA DEBIDO A QUE SE FALLO EN EL CAMPO NOMBRE 5 VECES");
						} else if (ape == null) {
							System.out.println("RELACION SUSPENDIDA DEBIDO A QUE SE FALLO EN EL CAMPO APELLIDO 5 VECES");
						}
					}
				} else {
					System.out.println("NO PUEDES DESMATRICULAR ALUMNOS DE UN CURSO QUE NO TIENE ALUMNOS MATRICULADOS");
				}
				break;
			case "0":

				break;
			default:
				System.out.println("OPCION NO VALIDA");
				break;
			}
		} while (!selec.equals("0"));
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

	private boolean relacionar2(String persona, String curs) {
		Scanner sc = new Scanner(System.in);
		String elec = null;
		System.out.println("SEGURO QUE QUIERE QUE " + persona + " SEA MATRICULADO EN " + curs);
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

	private boolean desRelacionar2(String persona, String curs) {
		Scanner sc = new Scanner(System.in);
		String elec = null;
		System.out.println("SEGURO QUE QUIERE QUE " + persona + " DEJE DE ESTAR MATRICULADO EN " + curs);
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
