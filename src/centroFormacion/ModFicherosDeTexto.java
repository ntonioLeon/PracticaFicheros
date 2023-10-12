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

			pw.write(curso.getCodigo() + "," + curso.getNombre() + "," + curso.getDescripcion() + "\n");

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
			for (String a : listaCursos.keySet()) {
				pw.write(listaCursos.get(a).getCodigo() + "," + listaCursos.get(a).getNombre() + ","
						+ listaCursos.get(a).getDescripcion());
			}
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
		try {
			fr = new FileReader(cursos);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				String cod = line.split(",")[0];
				String nomb = line.split(",")[1];
				String descripcion = line.split(",")[2];
				listaCursos.put(cod, new ModCurso(cod, nomb, descripcion));
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
						listaCursos.put(cod, new ModCurso(cod, nomb, descripcion));
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
}
