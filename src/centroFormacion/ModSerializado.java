package centroFormacion;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ModSerializado {

	private String fileName = "alumnos.ser";
	
	public void guardar(ArrayList<ModAlumno> alumnos) {
		File arch = new File(fileName);
		iniciar(arch);
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream(arch);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			for (ModAlumno modAlumno : alumnos) {
				oos.writeObject(modAlumno);
				oos.flush();
				oos.reset();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
				bos.close();
				fos.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	
	}
	
	public void guardar(HashMap<String, ModAlumno> alumnos) {
		File arch = new File(fileName);
		iniciar(arch);
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream(arch);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			for (String a : alumnos.keySet()) {
				oos.writeObject(alumnos.get(a));
				oos.flush();
				oos.reset();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
				bos.close();
				fos.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	
	}

	public ArrayList<ModAlumno> cargar() {
		File arch = new File(fileName);
		iniciar(arch);
		ArrayList<ModAlumno> alumnos = new ArrayList<>();
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(arch);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(ois);
			while (true) {
				ois.readObject();
				ModAlumno a = (ModAlumno) ois.readObject();
				alumnos.add(a);
			}
		} catch (EOFException eof) {
			// Saltando
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
				bis.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return alumnos;
	}
	
	public HashMap<String, ModAlumno> cargarEnHash() {
		File arch = new File(fileName);
		iniciar(arch);
		HashMap<String, ModAlumno> lista = new HashMap<String, ModAlumno>();
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(arch);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(ois);
			while (true) {
				ois.readObject();
				ModAlumno a = (ModAlumno) ois.readObject();
				lista.put(a.getNombre()+"_"+a.getApellido(), a);
			}
		} catch (EOFException eof) {
			// Saltando
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
				bis.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return lista;
	}

	public void cargarUnAlumno(String nombre) {
		boolean encontrado = false;
		ArrayList<ModAlumno> listaAlumno = cargar();
		for (ModAlumno modAlumno : listaAlumno) {
			if (modAlumno.getNombre().equals(nombre)) {
				System.out.println("COINCIDENCIA ENCONTRADO:");
				System.out.println(modAlumno.toString());
				encontrado = true;
			}
		}
		if (!encontrado) {
			System.out.println("NO SE HA ENCONTRADO NINGUNA COINCIDENCIA.");
		}
	}

	public ModAlumno obtenerUnAlumno(String Nombre, String Apellido) {
		ArrayList<ModAlumno> listaAlumno = cargar();
		for (ModAlumno modAlumno : listaAlumno) {
			if (modAlumno.getNombre().equals(Nombre) && modAlumno.getApellido().equals(Apellido)) {
				return modAlumno;
			}
		}
		return null;
	}

	public void borrarDeCursoEnCascada(String cod) {
		ArrayList<ModAlumno> alumnos = cargar();
		if (!alumnos.isEmpty()) {
			for (int i = 0; i < alumnos.size(); i++) {
				if (alumnos.get(i).getCursos().get(cod) != null) {
					alumnos.get(i).getCursos().remove(cod);
				}
			}
			guardar(alumnos);
		}
	}

	public HashMap<String, ModAlumno> obtenerAlumnosPorNomYApe(String cadena) {
		HashMap<String, ModAlumno> HashAlumnos = new HashMap<String, ModAlumno>();
		ArrayList<ModAlumno> alumnos = cargar();
		String[] nombresYApellidos = cadena.split(";");
		String[] nombre = new String[nombresYApellidos.length];
		String[] apellido = new String[nombresYApellidos.length];
		if (!alumnos.isEmpty()) {
			for (int i = 0; i < nombresYApellidos.length; i++) {
				nombre[i] = nombresYApellidos[i].split("_")[0];
				apellido[i] = nombresYApellidos[i].split("_")[1];
			}
			for (int i = 0; i < alumnos.size(); i++) {
				for (int j = 0; j < nombre.length; i++) {
					if (nombre[j].equals(alumnos.get(i).getNombre())
							&& apellido[j].equals(alumnos.get(i).getApellido())) {
						HashAlumnos.put(nombre[j] + "_" + apellido[j], alumnos.get(i));
					}
				}
			}
		}
		return HashAlumnos;
	}

	private void iniciar(File archivo) {
		try {
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
