package centroFormacion;

public class ContFicherializador {
	private ModBinario bin;
	private ModFicherosDeTexto text;
	private ModSerializado ser;
	
	/**
	 * Objeto que permitira el uso de ficheros en las clases controladoras por medio de los getters.
	 */
	ContFicherializador() {
		this.bin = new ModBinario();
		this.ser = new ModSerializado();
		this.text = new ModFicherosDeTexto();
	}

	public ModBinario getBin() {
		return bin;
	}

	public ModFicherosDeTexto getText() {
		return text;
	}
	
	public ModSerializado getSer() {
		return ser;
	}	
}
