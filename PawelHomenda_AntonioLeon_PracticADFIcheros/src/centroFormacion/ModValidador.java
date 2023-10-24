package centroFormacion;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Clase basada en la validacion de campos. Los contadores que enumeran el
 * numero de fallos se encuentran aqui para mayor limpieza en las clases
 * controladoras.
 */
public class ModValidador {
	private Scanner sc = new Scanner(System.in); // Para no declararlo en cada metodo.
	private ContFicherializador fich = new ContFicherializador(); // Para validar el DNI

	/**
	 * Metodo que pide un nombre y valida que este sea correcto.
	 * 
	 * @return el nombre si todo va bien o null si se fallan cinco veces.
	 */
	public String validarNombre() { // Usado por Alumnos, profesores y cursos
		int cont = 0;
		do {
			System.out.println("INTRODUZCA NOMBRE");
			String nombre = sc.nextLine();
			if (!nombre.trim().isEmpty()) { // Si no es un espacio vacio ni una cadena de espacios
				if (nombre.trim().length() < 26) { // si no tiene mas de 25 caracteres
					if (!nombre.trim().contains(",") && !nombre.trim().contains(";") && !nombre.trim().contains("_")) {
						// no contiene caracteres separadores
						System.out.println("NOMBRE VALIDO");
						return nombre.trim(); // Si pasa las pruebas devuelve una String
					} else { // Cada fallo marca el error y suma uno al contador de fallos
						System.out.println("EL CAMPO NOMBRE NO PUEDE CONTENER , NI ; NI _");
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

	public String validarApellido() { // Usado por Alumnos
		int cont = 0;
		do {
			System.out.println("INTRODUZCA APELLIDO");
			String apellido = sc.nextLine();
			if (!apellido.trim().isEmpty()) { // No es una cadena vacia ni una cadena de espacios vacios
				if (apellido.trim().length() < 26) { // no es mayor de 25 caracteres
					if (!apellido.trim().contains(",") && !apellido.trim().contains(";")
							&& !apellido.trim().contains("_")) {
						// no contiene caracteres separadores
						System.out.println("APELLIDO VALIDO");
						return apellido.trim(); // Si pasa las pruebas devuelve una String
					} else { // Cada fallo marca el error y suma uno al contador de fallos
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

	public String validarTeledono() { // Usado por Alumnos y profesores
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

	public String validarDireccion() { // Usado por Alumnos y Profesores
		int cont = 0;
		do {
			System.out.println("INTRODUZCA DIRECCION");
			String direc = sc.nextLine();
			if (!direc.trim().isEmpty()) {
				if (direc.trim().length() < 26) {
					if (!direc.trim().contains(",") && !direc.trim().contains(";") && !direc.trim().contains("_")) {
						// Son campos separadores por splits
						System.out.println("DIRECCION VALIDA");
						return direc.trim(); // Si pasa las pruebas devuelve una String
					} else { // Cada fallo marca el error y suma uno al contador de fallos
						System.out.println("EL CAMPO APELLIDO NO PUEDE CONTENER , NI ; NI _");
						cont++;
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

	public LocalDate validarFechaNacimiento() { // Usado por Alumnos
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
					} else { // Cada fallo marca el error y suma uno al contador de fallos ademas lo printea
								// ya que son tres comprobaciones paralelas
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
						} else { // Cada fallo marca el error y suma uno al contador de fallos ademas lo printea
									// ya que son tres comprobaciones paralelas
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
						} else { // Cada fallo marca el error y suma uno al contador de fallos ademas lo printea
									// ya que son tres comprobaciones paralelas
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

	/**
	 * Metodo que comprueba que la descripcion del curso sea una descripcion valida.
	 * 
	 * @return la descriocion si se realiza correctamente, null si no.
	 */
	public String validarDescripcion() { // Usado por Cursos
		int cont = 0;
		do {
			System.out.println("INTRODUZCA DESCRIPCION");
			String descipcion = sc.nextLine();
			if (!descipcion.trim().isEmpty()) { // Comprobamos que no este vacia ni ea una cadena de solo espacios
				if (descipcion.trim().length() < 51) { // No queremos una biblia, con 50 caracteres sobra.
					if (!descipcion.trim().contains(",") && !descipcion.trim().contains(";")
							&& !descipcion.trim().contains("_")) {
						// CAMPOS SEPARADORES
						System.out.println("DESCRIPCION VALIDA");
						return descipcion.trim(); // Si pasa las pruebas devuelve una String
					} else { // Cada fallo marca el error y suma uno al contador de fallos
						System.out.println("EL CAMPO APELLIDO NO PUEDE CONTENER , NI ; NI _");
						cont++;
					}
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

	/**
	 * Metodo que comprueba si el dni es valido. si se introduce un 1 se comprueba
	 * ademas que el dni no sea repetido el 1 se usa para nuevos dni el 2 para
	 * cuando buscas una coincidencia en baja, modif etc...
	 * 
	 * @param el int que marca que funcionalidad realizar
	 * @return El dni si se introduce un dni valido null si se fallan 5 veces
	 */
	public String validarDNI(int i) { // Usado por Profesores
		int cont = 0;
		switch (i) { // Switch que marca que hacer en base al parametro
		case 1: // Si es un dni nuevo (Que no debe repetirse)
			do {
				System.out.println("INTRODUZCA DNI, ESTE CAMPO SE COMPONE DE 8 DIGITOS Y 1 LETRA AL FINAL");
				String dni = sc.nextLine();
				dni = dni.trim(); // Se pide el campo y se comprueba que no esta vacio ni es una cadena solo de
									// espacios
				if (!dni.isEmpty()) {
					if (dni.length() == 9) { // Se comprueba que sean 9 caracteres
						if (isNumeric(dni.substring(0, 7))) { // Se comprueba que los 8 primeros caracteres sean
																// numericos
							if (isAlfabetic(dni.substring(8))) { // Se comprueba que el ultimo sea alfbetico
								if (!fich.getBin().comprobarDNI(dni)) { // Al ser este case para altas o para el nuevo
																		// dni de una modificacion queremos que no este
																		// entre los dni existentes
									System.out.println("DNI VALIDO");
									return dni;
								} else { // Cada fallo marca el error y suma uno al contador de fallos
									System.out.println("YA EXISTE UN PROFESOR CON EL DNI ");
									cont++;
								}
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
			break;
		case 2: // Si no es un dni nuevo es decir, buscamos a alguien ya existente
			do {
				System.out.println("INTRODUZCA DNI, ESTE CAMPO SE COMPONE DE 8 DIGITOS Y 1 LETRA AL FINAL");
				String dni = sc.nextLine();
				dni = dni.trim();
				if (!dni.isEmpty()) { // Comprobamos que el dni no esta vacio ni es una cadena solo de espacios
					if (dni.length() == 9) { // length 9
						if (isNumeric(dni.substring(0, 7))) { // los 8 primeros numericos
							if (isAlfabetic(dni.substring(8))) { // ultimo alfabetico
								System.out.println("DNI VALIDO"); // Aqui, queremos que coicida, por lo que dejamos
																	// pasar un posibl dni repetido pues sera necesario
																	// para encontrar profespres
								return dni;
							} else { // Cada fallo marca el error y suma uno al contador de fallos
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
			break;
		default:
			break;
		}
		return null; // Si falla cinco veces devuelve null
	}

	/**
	 * Metodo que valida si el codigo de curso es numerico, este metodo es usado
	 * para la busqueda modificacion baja y relacion
	 * 
	 * @return el codigo si es un codigo valido null si se fallan 5 veces
	 */
	public String validarCodigo() { // Usado por Cursos
		int cont = 0;
		do {
			System.out
					.println("INTRODUZCA EL CODIGO DEL CURSO, ESTE DEBERA EMPEZAR POR 'AE' E IR SEGUIDO DE UN NUMERO");
			String cod = sc.nextLine(); // Pide el codigo
			cod = cod.trim(); // Comprueba que no sea una cadena vacia o llena de espacios
			if (!cod.isEmpty()) {
				if (cod.substring(0, 2).equals("AE")) { // Comprueba que las posiciones 0 y 1 sean AE (Recordemos que
														// subString es cerrado por la izquierda abierto por la derecha)
					if (isNumeric(cod.substring(3))) { // Comprueba que la cadena desde la posicion 2 en adelante es
														// numerica
						System.out.println("CODIGO VALIDO");
						return cod;
					} else { // Cada fallo marca el error y suma uno al contador de fallos
						System.out.println("EL CODIGO DEBE SER NUMERICO A PARTIR DE SU TERCER DIGITO");
						cont++;
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

	/**
	 * comprueba que la cadena sea numerica
	 * 
	 * @param cadena
	 * @return true si es numerico, false si no
	 */
	private boolean isNumeric(String cadena) {
		for (int i = 0; i < cadena.length(); i++) { // recorre la cadena
			if (!Character.isDigit(cadena.charAt(i))) { // Si algun caracter no es numerico retorna false
				return false;
			}
		}
		return true; // Si todos los caracteres son numericos retorna true
	}

	/**
	 * comprueba que la cadena es alfabetico
	 * 
	 * @param cadena
	 * @return true si es algabetico false si no
	 */
	private boolean isAlfabetic(String cadena) {
		for (int i = 0; i < cadena.length(); i++) { // recorre la cadena
			if (!Character.isAlphabetic(cadena.charAt(i))) { // Si algun caracter no es alfabetico retorna false
				return false;
			}
		}
		return true;
	}
}
