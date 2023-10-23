package centroFormacion;
import java.util.Scanner;
public class VisMenu {
	public static void main(String[] args) {
		System.out.println("Inicio de PracticaADFIcheros");
		Scanner sc = new Scanner(System.in);
		String eleccion = null, elecAlumnos = null, elecProfesores = null, elecCursos = null;
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
						ContGestionAlumnos.alta();
						break;
					case "2":
						ContGestionAlumnos.baja();
						break;
					case "3":
						ContGestionAlumnos.modificar();
						break;
					case "4":
						ContGestionAlumnos.buscar();
						break;
					case "5":
						ContGestionAlumnos.mostrarTodosLosAlumnos();
						break;
					case "6":
						ContGestionAlumnos.relacionarCursosYAlumno();
						break;
					case "0":
						break;
					default:
						System.out.println("ELECCION NO VALIDA.");
						break;
						}} while (!elecAlumnos.equals("0"));
						break;
					case "2":
					elecProfesores = sc.nextLine();
					switch (elecProfesores) {
					case "1":
						ContGestionProfesores.alta();
						break;
					case "2":
						ContGestionProfesores.baja();
						break;
					case "3":
						ContGestionProfesores.modificar();
						break;
					case "4":
						ContGestionProfesores.buscar();
						break;
					case "5":
						ContGestionProfesores.mostrarTodosLosProfesores();
						break;
					case "6":
						ContGestionProfesores.relacionarProfesoresConCursos();
						break;
					case "0":
						break;
					default:
						System.out.println("ELECCION NO VALIDA.");
						break;
					}
					}
					} while (!elecProfesores.equals("0") || !elecAlumnos.equals("0"));
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
						ContGestionCursos.alta();
						break;
					case "2":
						ContGestionCursos.baja();
						break;
					case "3":
						ContGestionCursos.modificar();
						break;
					case "4":
						ContGestionCursos.buscar();
						break;
					case "5":
						ContGestionCursos.mostrarTodosLosProfesores();
						break;
					case "6":
						ContGestionCursos.mostrarTodosLosAlumnos();
						break;
					case "0":
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
		System.out.println("Fin de PracticaADFIcheros");
	}
}
