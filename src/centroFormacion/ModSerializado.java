package centroFormacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ModSerializado {
	//Escritura
	public void escribir(ArrayList<ModAlumno> alumnos) {
		File archivo = new File("Alumnos.ser");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(archivo);
			oos = new ObjectOutputStream(fos);
			for (ModAlumno a : alumnos) {
				oos.writeObject(a);
			}
		} catch (Exception ex) {
			
		} finally {
			try {
				oos.close();
				fos.close();
			} catch (Exception ex) {
				
			}
		}
	}
		
	//Deserializado
	public ArrayList<ModAlumno> deserializar() {
		File archivo = new File("Alumnos.ser");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ArrayList<ModAlumno> alumnos = new ArrayList<ModAlumno>();
		try {
			fis = new FileInputStream(archivo);
			ois = new ObjectInputStream(fis);
			
			while(true) {
				alumnos.add((ModAlumno) ois.readObject());
			}
		} catch (Exception ex) {
			
		} finally {
			try {
				ois.close();
				fis.close();
			} catch (Exception ex) {
				
			}
		}
		return alumnos;
	}
}
