package centroFormacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class ModFicherosDeTexto {
	private static File cursos = new File("Cursos.txt");

	public static void escribirCurso(ModCurso curso) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(cursos, true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			pw.write(curso.toString()+"\n");

		} catch (Exception ex) {

		} finally {
			try {
				pw.close();
				bw.close();
				fw.close();
			} catch (Exception ex) {

			}
		}
	}

	public static void reEscribirTrasBajaOMod(HashMap<String, ModCurso> listaCursos) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(cursos);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			System.out.println(listaCursos.size());
			for (String a : listaCursos.keySet()) {						
				System.out.println(listaCursos.get(a).toString());
				pw.write(listaCursos.get(a).toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				pw.close();
				bw.close();
				fw.close();
			} catch (Exception ex) {

			}
		}
	}

	public static void buscarPorCodigo(String codigo) {
		FileReader fr = null;
		BufferedReader br = null;
		boolean esta = false;
		try {
			fr = new FileReader(cursos);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				String cod = line.split(",")[0];
				if (codigo.equals(cod)) {
					System.out.println(line);
					esta = true;
				}
			}
		} catch (Exception ex) {

		} finally {
			if (!esta) {
				System.out.println("NO SE ENCONTRO NINGUN CURSO CON EL CODIGO PROPORCIONADO");
			}
			try {
				br.close();
				fr.close();
			} catch (Exception ex) {

			}
		}
	}

	public static void mostrarTodos() {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(cursos);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception ex) {

		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception ex) {

			}
		}
	}

	public static HashMap<String, ModCurso> obtenerTodosLosCursos() {
		HashMap<String, ModCurso> listaCursos = new HashMap<String, ModCurso>();
		FileReader fr = null;
		BufferedReader br = null;
		String cod = "", nomb = "", descripcion = "";
		try {
			fr = new FileReader(cursos);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				int cantidad = line.split(",").length; 
 				cod = line.split(",")[0];
				nomb = line.split(",")[1];
				descripcion = line.split(",")[2];				
				ModProfesor prof = null;
				HashMap<String, ModAlumno> alumns = new HashMap<String, ModAlumno>();
				if (cantidad > 3) {
					prof = ModBinario.ObtenerUnProfesor(line.split(",")[3].split(";")[1]);
				} 
				if (cantidad > 4) {
					alumns = ModSerializado.obtenerAlumnosPorNomYApe(line.split(",")[4]);
				}
				ModCurso curs = new ModCurso(cod, nomb, descripcion);
				curs.setProfesor(prof);
				curs.setAlumnos(alumns);
				listaCursos.put(curs.getCodigo(), curs);
				System.out.println(curs.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception ex) {

			}
		}
		return listaCursos;
	}

	public static HashMap<String, ModCurso> obtenerCursosDeCodigos(String codigos) {
		HashMap<String, ModCurso> listaCursos = new HashMap<String, ModCurso>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(cursos);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				String cod = line.split(",")[0];
				String nomb = line.split(",")[1];
				String descripcion = line.split(",")[2];
				String[] listaCodigos = codigos.split(";");
				for (int i = 0; i < listaCodigos.length; i++) {
					if (listaCodigos[i].equals(cod)) {
						ModCurso curs = new ModCurso(cod, nomb, descripcion);
						listaCursos.put(curs.getCodigo(), curs);
					}
				}
			}
		} catch (Exception ex) {

		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception ex) {

			}
		}
		return listaCursos;
	}
	
	public static void borrarDeProfesoresEnCascade(ModProfesor profesor) {
		HashMap<String, ModCurso> listaCursos = new HashMap<String, ModCurso>();
		if (!listaCursos.isEmpty()) {
			for (String a : listaCursos.keySet()) {
				if (listaCursos.get(a).getProfesor().getDni().equals(profesor.getDni())) {
					listaCursos.get(a).setProfesor(null);
				}
			}
		}
	}
}