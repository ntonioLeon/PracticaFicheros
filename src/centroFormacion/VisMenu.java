package centroFormacion;

import java.util.Scanner;

public class VisMenu {

	public static void main(String[] args) {
		System.out.println("Inicio de PracticaADFIcheros".toUpperCase());
		Scanner sc = new Scanner(System.in);
		String eleccion = null, elecAlumnos = null, elecProfesores = null, elecCursos = null;
		ContGestionAlumnos gesAlumnos = new ContGestionAlumnos();
		ContGestionProfesores gesProfesores = new ContGestionProfesores();
		ContGestionCursos gesCursos = new ContGestionCursos();
		
		do {
			System.out.println(
					"SELECCIONE 1 PARA GESTIONAR ALUMNOS, 2 PARA GESTIONAR PROFESORES, 3 PARA GESTIONAR CURSOS, 0 PARA SALIR DE LA APLICACION.");
			eleccion = sc.nextLine();
			switch (eleccion) {
			case "1":
				do {
					System.out.println("GESTION ALUMNOS");
					System.out.println("SELECCIONE 1 PARA DAR DE ALTA UN ALUMNO\n" + "2 PARA DAR DE BAJA UN ALUMNO\n"
							+ "3 PARA MODIFICAR UN ALUMNO\n" + "4 PARA BUSCAR UN ALUMNO\n"
							+ "5 PARA MOSTRAR TODOS LOS ALUMNOS\n"
							+ "6 PARA ADMINISTRAR LA RELACION ENTRE LOS ALUMNOS Y LOS CURSOS\n"
							+ "0 VOLVER AL MENU PRINCIPAL.");
					elecAlumnos = sc.nextLine();
					switch (elecAlumnos) {
					case "1":
						gesAlumnos.alta();
						break;
					case "2":
						gesAlumnos.baja();
						break;
					case "3":
						gesAlumnos.modificar();
						break;
					case "4":
						gesAlumnos.buscar();
						break;
					case "5":
						gesAlumnos.mostrarTodosLosAlumnos();
						break;
					case "6":
						gesAlumnos.relacionarAlumnosConCursos();
						break;
					case "0":
						//Evitamos el default
						break;
					default:
						System.out.println("ELECCION NO VALIDA.");
						break;
					}
				} while (!elecAlumnos.equals("0"));
				break;
			case "2":
				do {
					System.out.println("GESTION PROFESORES");
					System.out.println("SELECCIONE 1 PARA DAR DE ALTA UN PROFESOR\n"
							+ "2 PARA DAR DE BAJA UN PROFESOR\n" + "3 PARA MODIFICAR UN PROFESOR\n"
							+ "4 PARA BUSCAR UN PROFESOR\n" + "5 PARA MOSTRAR TODOS LOS PROFESORES\n"
							+ "6 PARA ADMINISTRAR LA RELACION ENTRE PROFESORES Y LOS CURSOS\n"
							+ "0 VOLVER AL MENU PRINCIPAL.");
					elecProfesores = sc.nextLine();
					switch (elecProfesores) {
					case "1":
						gesProfesores.alta();
						break;
					case "2":
						gesProfesores.baja();
						break;
					case "3":
						gesProfesores.modificar();
						break;
					case "4":
						gesProfesores.buscar();
						break;
					case "5":
						gesProfesores.mostrarTodosLosProfesores();
						break;
					case "6":
						gesProfesores.relacionarProfesoresConCursos();
						break;
					case "0":
						//Evitamos el default
						break;
					default:
						System.out.println("ELECCION NO VALIDA.");
						break;
					}
				} while (!elecProfesores.equals("0"));
				break;
			case "3":
				do {
					System.out.println("GESTION CURSOS");
					System.out.println("SELECCIONE 1 PARA DAR DE ALTA UN CURSO\n" + "2 PARA DAR DE BAJA UN CURSO\n"
							+ "3 PARA MODIFICAR UN CURSO\n" + "4 PARA BUSCAR UN CURSO\n"
							+ "5 PARA MOSTRAR TODOS LOS CURSOS\n"
							+ "6 PARA ADMINISTRAR LA RELACION ENTRE LOS CURSOS Y LOS ALUMNOS Y LOS PROFESORES\n"
							+ "0 VOLVER AL MENU PRINCIPAL.");
					elecCursos = sc.nextLine();
					switch (elecCursos) {
					case "1":
						gesCursos.alta();
						break;
					case "2":
						gesCursos.baja();
						break;
					case "3":
						gesCursos.modificar();
						break;
					case "4":
						gesCursos.buscar();
						break;
					case "5":
						gesCursos.mostrarTodosLosProfesores();
						break;
					case "6":
						gesCursos.relacionarCursos();
						break;
					case "0":
						//Evitamos el default
						break;
					default:
						System.out.println("ELECCION NO VALIDA.");
						break;
					}
				} while (!elecCursos.equals("0"));
				break;
			case "0":
				// Para que no entre el dafault
				break;
			default:
				System.out.println("ELECCION NO VALIDA.");
				break;
			}
		} while (!eleccion.equals("0"));
		sc.close();
		System.out.println("Fin de PracticaADFIcheros".toUpperCase());
	}
}
