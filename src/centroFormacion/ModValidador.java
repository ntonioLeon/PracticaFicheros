package centroFormacion;

import java.time.LocalDate;
import java.util.Scanner;

public class ModValidador {
	private static Scanner sc = new Scanner(System.in);

	public static String validarNombre() { // Alumnos, profesores y cursos
		int cont = 0;
		do {
			System.out.println("INTRODUZCA NOMBRE");
			String nombre = sc.nextLine();
			if (!nombre.trim().isEmpty()) {
				if (nombre.trim().length() < 26) {
					if (!nombre.trim().contains(",") && !nombre.trim().contains(";")) {
						System.out.println("NOMBRE VALIDO");
						return nombre.trim(); // Si pasa las pruebas devuelve una String
					} else {
						System.out.println("EL CAMPO NOMBRE NO PUEDE CONTENER , NI ;");
					}
				} else {
					System.out.println("EL CAMPO NOMBRE NO PUEDE SUPERAR LOS 25 CARACTERES");
					cont++;
				}
			} else {
				System.out.println("NO INTRODUZCA NI CADENAS VACIAS NI UNICAMENTE ESPACIOS");
				cont++;
			}
			System.out.println("FALLOS = " + cont);
		} while (cont < 5);
		return null; // Si falla cinco veces devuelve null
	}

	public static String validarApellido() { // Alumnos
		int cont = 0;
		do {
			System.out.println("INTRODUZCA APELLIDO");
			String apellido = sc.nextLine();
			if (!apellido.trim().isEmpty()) {
				if (apellido.trim().length() < 26) {
					if (!apellido.trim().contains(",") && !apellido.trim().contains(";")) {
						System.out.println("APELLIDO VALIDO");
						return apellido.trim(); // Si pasa las pruebas devuelve una String
					} else {
						System.out.println("EL CAMPO APELLIDO NO PUEDE CONTENER , NI ;");
					}
				} else {
					System.out.println("EL CAMPO APELLIDO NO PUEDE SUPERAR LOS 25 CARACTERES");
					cont++;
				}
			} else {
				System.out.println("NO INTRODUZCA NI CADENAS VACIAS NI UNICAMENTE ESPACIOS");
				cont++;
			}
			System.out.println("FALLOS = " + cont);
		} while (cont < 5);
		return null; // Si falla cinco veces devuelve null
	}

	public static String validarTeledono() { // Alumnos y profesores
		int cont = 0;
		do {
			System.out.println("INTRODUZCA TELEFONO");
			String aux = sc.nextLine();
			String telefono = aux.trim();
			if (!telefono.isEmpty()) {
				if (telefono.length() == 9) {
					if (isNumeric(telefono)) {
						System.out.println("TELEFONO VALIDO");
						return telefono;
					} else {
						System.out.println("EL CAMPO TELEFONO SOLO PUEDE CONTENER NUMEROS");
						cont++;
					}
					return telefono; // Si pasa las pruebas devuelve una String
				} else {
					System.out.println("EL CAMPO TELEFONO DEBE TENER 9 CARACTERES");
					cont++;
				}
			} else {
				System.out.println("NO INTRODUZCA NI CADENAS VACIAS NI UNICAMENTE ESPACIOS");
				cont++;
			}
			System.out.println("FALLOS = " + cont);
		} while (cont < 5);
		return null;
	}

	public static String validarDireccion() { // Alumnos y Profesores
		int cont = 0;
		do {
			System.out.println("INTRODUZCA DIRECCION");
			String direc = sc.nextLine();
			if (!direc.trim().isEmpty()) {
				if (direc.trim().length() < 26) {
					if (!direc.trim().contains(",") && !direc.trim().contains(";")) {
						System.out.println("DIRECCION VALIDA");
						return direc.trim(); // Si pasa las pruebas devuelve una String
					} else {
						System.out.println("EL CAMPO APELLIDO NO PUEDE CONTENER , NI ;");
					}
				} else {
					System.out.println("EL CAMPO DIRECCION NO PUEDE SUPERAR LOS 25 CARACTERES");
					cont++;
				}
			} else {
				System.out.println("NO INTRODUZCA NI CADENAS VACIAS NI UNICAMENTE ESPACIOS");
				cont++;
			}
			System.out.println("FALLOS = " + cont);
		} while (cont < 5);
		return null; // Si falla cinco veces devuelve null
	}

	public static LocalDate validarFechaNacimiento() { // Alumnos
		int cont = 0;
		boolean fin = false;
		int d = -1;
		int m = -1;
		int y = -1;
		do {
			System.out.println("INTRODUZCA NUMEROS ENTEROS.");
			System.out.println("INTRODUZCA DIA DEL MES, ESTE NUMERO SOLO PUEDE SER UN NUMERO DEL 1 AL 30");
			String aux = sc.nextLine();
			String dia = aux.trim();
			if (!dia.isEmpty()) {
				if (isNumeric(dia)) {
					if (Integer.parseInt(dia) > 0 && Integer.parseInt(dia) < 31) {
						d = Integer.parseInt(dia);
						fin = true;
					} else {
						System.out.println("EL CAMPO DIA SOLO PUEDE CONTENER NUMEROS ENTRE 1 AL 30");
						cont++;
						System.out.println("FALLOS = " + cont);
					}
				} else {
					System.out.println("EL CAMPO DIA SOLO PUEDE CONTENER NUMEROS ENTEROS");
					cont++;
					System.out.println("FALLOS = " + cont);
				}
			} else {
				System.out.println("DIA: NO INTRODUZCA NI CADENAS VACIAS NI UNICAMENTE ESPACIOS");
				cont++;
				System.out.println("FALLOS = " + cont);
			}
			if (cont == 5) {
				fin = true;
			}
		} while (!fin);
		if (cont < 5) {
			fin = false;
			do {
				System.out.println("INTRODUZCA NUMEROS ENTEROS.");
				System.out.println("INTRODUZCA EL MES, ESTE NUMERO SOLO PUEDE SER UN NUMERO DEL 1 AL 12");
				String aux = sc.nextLine();
				String mes = aux.trim();
				if (!mes.isEmpty()) {
					if (isNumeric(mes)) {
						if (Integer.parseInt(mes) > 0 && Integer.parseInt(mes) < 13) {
							m = Integer.parseInt(mes);
							fin = true;
						} else {
							System.out.println("EL CAMPO MES SOLO PUEDE CONTENER NUMEROS ENTRE 1 AL 12");
							cont++;
							System.out.println("FALLOS = " + cont);
						}
					} else {
						System.out.println("EL CAMPO MES SOLO PUEDE CONTENER NUMEROS ENTEROS");
						cont++;
						System.out.println("FALLOS = " + cont);
					}
				} else {
					System.out.println("MES: NO INTRODUZCA NI CADENAS VACIAS NI UNICAMENTE ESPACIOS");
					cont++;
					System.out.println("FALLOS = " + cont);
				}
				System.out.println("FALLOS = " + cont);
				if (cont == 5) {
					fin = true;
				}
			} while (!fin);
		}
		if (cont < 5) {
			fin = false;
			do {
				System.out.println("INTRODUZCA NUMEROS ENTEROS.");
				System.out.println("INTRODUZCA EL ANNO, ESTE NUMERO SOLO PUEDE SER UN NUMERO ENTRE 1950 AL 2010");
				String aux = sc.nextLine();
				String anno = aux.trim();
				if (!anno.isEmpty()) {
					if (isNumeric(anno)) {
						if (Integer.parseInt(anno) > 1949 && Integer.parseInt(anno) < 2010) {
							y = Integer.parseInt(anno);
							LocalDate fecha = LocalDate.of(y, m, d);
							System.out.println("FECHA DE NACIMIENTO VALIDA");
							return fecha;
						} else {
							System.out.println("EL CAMPO ANNO SOLO PUEDE CONTENER NUMEROS DEL 1950 AL 2010");
							cont++;
							System.out.println("FALLOS = " + cont);
						}
					} else {
						System.out.println("EL CAMPO ANNO SOLO PUEDE CONTENER NUMEROS ENTEROS");
						cont++;
						System.out.println("FALLOS = " + cont);
					}
				} else {
					System.out.println("ANNO: NO INTRODUZCA NI CADENAS VACIAS NI UNICAMENTE ESPACIOS");
					cont++;
					System.out.println("FALLOS = " + cont);
				}
				System.out.println("FALLOS = " + cont);
				if (cont == 5) {
					fin = true;
				}
			} while (!fin);
		}
		return null;
	}

	public static String validarDescripcion() { // Cursos
		int cont = 0;
		do {
			System.out.println("INTRODUZCA DESCRIPCION");
			String descipcion = sc.nextLine();
			if (!descipcion.trim().isEmpty()) {
				if (descipcion.trim().length() < 51) {
					System.out.println("DESCRIPCION VALIDA");
					return descipcion.trim(); // Si pasa las pruebas devuelve una String
				} else {
					System.out.println("EL CAMPO DESCRIPCION NO PUEDE SUPERAR LOS 50 CARACTERES");
					cont++;
				}
			} else {
				System.out.println("NO INTRODUZCA NI CADENAS VACIAS NI UNICAMENTE ESPACIOS");
				cont++;
			}
			System.out.println("FALLOS = " + cont);
		} while (cont < 5);
		return null; // Si falla cinco veces devuelve null
	}

	public static String validarDNI() { // Profesores
		int cont = 0;
		do {
			System.out.println("INTRODUZCA DNI, ESTE CAMPO SE COMPONE DE 8 DIGITOS Y 1 LETRA AL FINAL");
			String dni = sc.nextLine();
			dni = dni.trim();
			if (!dni.isEmpty()) {
				if (dni.length() == 9) {
					if (isNumeric(dni.substring(0, 7))) {
						if (isAlfabetic(dni.substring(8))) {
							System.out.println("DNI VALIDO");
							return dni;
						} else {
							System.out.println(
									"FALLO EN LETRA: EL CAMPO DEBE TENER LOS 8 PRIMEROS NUMERICOS Y EL ULTIMO ALFABETICO");
							cont++;
						}
					} else {
						System.out.println(
								"FALLO EN NUMERO: EL CAMPO DEBE TENER LOS 8 PRIMEROS NUMERICOS Y EL ULTIMO ALFABETICO");
						cont++;
					}
				} else {
					System.out.println(
							"EL CAMPO DNI DEBE TENER 9 CARACTERES, LOS 8 PRIMEROS NUMERICOS Y EL ULTIMO ALFABETICO");
					cont++;
				}
			} else {
				System.out.println("NO INTRODUZCA NI CADENAS VACIAS NI UNICAMENTE ESPACIOS");
				cont++;
			}
			System.out.println("FALLOS = " + cont);
		} while (cont < 5);
		return null; // Si falla cinco veces devuelve null
	}

	public static String validarCodigo() { //Cursos
		int cont = 0;
		do {
			System.out.println("INTRODUZCA EL CODIGO DEL CURSO, ESTE DEBERA EMPEZAR POR 'AE' E IR SEGUIDO DE UN NUMERO");
			String cod = sc.nextLine();
			cod = cod.trim();
			if (!cod.isEmpty()) {
				if (!cod.substring(0, 1).equalsIgnoreCase("AE")) {
					if (isNumeric(cod.substring(3))) {
						System.out.println("CODIGO VALIDO");
						return cod;
					} else {
						System.out.println("EL CODIGO DEBE SER NUMERICO A PARTIR DE SU TERCER DIGITO");
					}
				} else {
					System.out.println("EL CODIGO DEBE EMPEZAR POR 'AE'");
					cont++;
				}
			} else {
				System.out.println("NO INTRODUZCA NI CADENAS VACIAS NI UNICAMENTE ESPACIOS");
				cont++;
			}
			System.out.println("FALLOS = " + cont);
		} while (cont < 5);
		return null;
	}

	private static boolean isNumeric(String cadena) { // comprueba que la cadena sea numerica
		for (int i = 0; i < cadena.length(); i++) { // recorre la cadena
			if (!Character.isDigit(cadena.charAt(i))) { // Si algun caracter no es numerico retorna false
				return false;
			}
		}
		return true; // Si todos los caracteres son numericos retorna true
	}

	private static boolean isAlfabetic(String cadena) {
		for (int i = 0; i < cadena.length(); i++) { // recorre la cadena
			if (!Character.isAlphabetic(cadena.charAt(i))) { // Si algun caracter no es numerico retorna false
				return false;
			}
		}
		return true;
	}
}
