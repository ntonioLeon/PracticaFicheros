package centroFormacion;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ModSerializado {
	
	public static final String FILENAME = "alumnos.ser";
	
	public static void guardar(ArrayList<ModAlumno> alumnos) {
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(FILENAME);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);
			
			for (ModAlumno modAlumno : alumnos) {
				oos.writeObject(modAlumno);
				oos.flush();
				oos.reset();
			}
			
			oos.close();
			bos.close();
			fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<ModAlumno> cargar(){
		
		ArrayList<ModAlumno> alumnos = new ArrayList<>();
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(FILENAME);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			
			while(true) {
				alumnos.add((ModAlumno)ois.readObject());
			}
			
		}
		catch (EOFException eof) {
			//Saltando
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
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

	public static void cargarUnAlumno(String nombre) {
		boolean encontrado = false;
		ArrayList<ModAlumno> listaAlumno = ModSerializado.cargar();
		for (ModAlumno modAlumno : listaAlumno) {
			if(modAlumno.getNombre().equals(nombre)) {
				System.out.println("COINCIDENCIA ENCONTRADO:");
				System.out.println(modAlumno.toString());
				encontrado = true;
			}
		}
		if(!encontrado) {
			System.out.println("NO SE HA ENCONTRADO NINGUNA COINCIDENCIA.");
		}
	}
}
