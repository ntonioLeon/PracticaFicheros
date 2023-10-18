package centroFormacion;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

public class ModBinario {
	private static File archivo = new File("Profesores.dat");

	public static void guardar(ModProfesor profesor) {
		iniciar(archivo);
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		DataOutputStream dos = null;

		try {
			fos = new FileOutputStream(archivo, true);
			bos = new BufferedOutputStream(fos);
			dos = new DataOutputStream(bos);

			// Escrutura de campos dni, nombre, direc, telef.
			dos.writeUTF(profesor.getDni());
			dos.writeUTF(profesor.getNombre());
			dos.writeUTF(profesor.getDireccion());
			dos.writeUTF(profesor.getTelefono());

			// Creacion de una cadena con los codigos de los cursos del arraylist.
			String cursos = "";
			if (!profesor.getCursos().isEmpty()) {
				for (String a : profesor.getCursos().keySet()) {
					cursos = a + ";" + cursos;
				}
			}
			// Escritura de cursos
			dos.writeUTF(cursos);
		} catch (Exception ex) {

		} finally {
			try {
				dos.close();
				bos.close();
				fos.close();
			} catch (Exception ex) {

			}
		}
	}

	public static void reEscribirFichero(HashMap<String, ModProfesor> profesores) {
		iniciar(archivo);
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		DataOutputStream dos = null;

		try {
			fos = new FileOutputStream(archivo);
			bos = new BufferedOutputStream(fos);
			dos = new DataOutputStream(bos);

			for (String a : profesores.keySet()) {
				dos.writeUTF(profesores.get(a).getDni());
				dos.writeUTF(profesores.get(a).getNombre());
				dos.writeUTF(profesores.get(a).getDireccion());
				dos.writeUTF(profesores.get(a).getTelefono());
				String cursos = "";
				if (!profesores.get(a).getCursos().isEmpty()) {
					for (String b : profesores.get(a).getCursos().keySet()) {
						cursos = b + ";" + cursos;
					}
				}
				dos.writeUTF(cursos);
			}
		} catch (Exception ex) {

		} finally {
			try {
				dos.close();
				bos.close();
				fos.close();
			} catch (Exception ex) {

			}
		}
	}

	public static void mostrarContenidoDelFichero() {
		iniciar(archivo);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		int cont = 0;
		try {
			fis = new FileInputStream(archivo);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			while (true) {
				String dni = dis.readUTF();
				String nombre = dis.readUTF();
				String direc = dis.readUTF();
				String telef = dis.readUTF();
				String cursos = dis.readUTF();
				System.out.print("Profesor: Nombre= " + nombre + ", ");
				System.out.print("DNI= " + dni + ", ");
				System.out.print("Direccion= " + direc + ", ");
				System.out.print("Telefono= " + telef + ", ");
				System.out.println("Cursos= " + cursos);
				cont++;
			}

		} catch (Exception ex) {
			if (cont == 0) {
				System.out.println("El archivo no tenia contenido".toUpperCase());
			}
		} finally {
			try {
				dis.close();
				bis.close();
				fis.close();
			} catch (Exception ex) {

			}
		}
	}

	public static void mostrarUnProfesor(String dni) {
		iniciar(archivo);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		int cont = 0;
		boolean esta = false;
		try {
			fis = new FileInputStream(archivo);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			while (true) {
				String dni2 = dis.readUTF();
				String nombre = dis.readUTF();
				String direc = dis.readUTF();
				String telef = dis.readUTF();
				String cursos = dis.readUTF();
				if (dni2.equalsIgnoreCase(dni)) {
					esta = true;
					System.out.print("Profesor: Nombre= " + nombre + ", ");
					System.out.print("DNI= " + dni2 + ", ");
					System.out.print("Direccion= " + direc + ", ");
					System.out.print("Telefono= " + telef + ", ");
					System.out.println("Cursos= " + cursos);
				}
				cont++;
			}

		} catch (Exception ex) {
			if (cont == 0) {
				System.out.println("EL ARCHIVO NO TENIA CONTENIDO.");
			} else {
				if (!false) {
					System.out.println("EL DNI NO SE CORRESPONDIA CON EL DE NINGUN PROFESOR EXISTENTE.");
				}
			}
		} finally {
			try {
				dis.close();
				bis.close();
				fis.close();
			} catch (Exception ex) {

			}
		}
	}

	public static HashMap<String, ModProfesor> obtenerHashDeProfes() {
		HashMap<String, ModProfesor> listaProfesores = new HashMap<String, ModProfesor>();
		iniciar(archivo);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		int cont = 0;
		try {
			fis = new FileInputStream(archivo);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			while (true) {
				String dni = dis.readUTF();
				String nombre = dis.readUTF();
				String direc = dis.readUTF();
				String telef = dis.readUTF();
				String cursos = dis.readUTF();
				HashMap<String, ModCurso> listaCursos = ModFicherosDeTexto.obtenerCursosDeCodigos(cursos);
				ModProfesor prof = new ModProfesor(dni, nombre, direc, telef, listaCursos);
				listaProfesores.put(prof.getDni(), prof);
				cont++;
			}

		} catch (Exception ex) {
			if (cont == 0) {
				System.out.println("El archivo no tenia contenido");
			}
		} finally {
			try {
				dis.close();
				bis.close();
				fis.close();
			} catch (Exception ex) {

			}
		}
		return listaProfesores;
	}
		
	public static void borradoDeCursoEnCascada(String cod) {
		HashMap<String, ModProfesor> profesores = obtenerHashDeProfes();
		if (!profesores.isEmpty()) {			
			for (String a : profesores.keySet()) {
				if (profesores.get(a).getCursos().get(cod) != null) {
					profesores.get(a).getCursos().remove(cod);
				}
			}
			reEscribirFichero(profesores);
		}
	}
	
	public static ModProfesor ObtenerUnProfesor(String dni) {
		HashMap<String, ModProfesor> profesores = obtenerHashDeProfes();
		if (!profesores.isEmpty()) {
			if (profesores.get(dni) != null) {
				return profesores.get(dni);
			}
		}
		return null;
	}

	private static void iniciar(File archivo) {
		try {
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
		} catch (Exception ex) {

		}
	}
	
	public static boolean comprobarDNI(String dni) {
		boolean esta = false;
		HashMap<String, ModProfesor> profesores = obtenerHashDeProfes();
		if (!profesores.isEmpty()) {			
			for (String a : profesores.keySet()) {
				if (profesores.get(a).getDni().equals(dni)) {
					esta = true;
				}
			}
		}
		profesores = null;
		return esta;
	}
}