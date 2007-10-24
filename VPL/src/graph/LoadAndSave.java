package graph;
/**
 * Clase encarga de los procesos de guardar y cargar archivos de trabajo.
 * 
 * @author Andr&eacute Freyr&iacutea Cedeño
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figuero Ram&iacuterez
 * @author Jos&eacute Roberto Ram&iacuterez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */
import dataio.*;
import gui.*;
import struct.*;

public class LoadAndSave<O>{
		
	private Load<O> in;
	private Save<O> out;
	
	/**
	 * M&eacutetodo para guardar al grafo.
	 *
	 * @return			<code>boolean</code>
	 */
	public boolean save(O obj, String path, String filename) {
		
		out = new Save(obj, path, filename);
		return out.saveObj();
	}
	
	/**
	 * M&eacutetodo para cargar al grafo.
	 *
	 * @return			<code>boolean</code>
	 */
	public O load(String path, String filename) {
		
		O obj;
		
		in = new Load(path, filename);
		obj = in.loadObj();
		
		return obj;
	}
	
	/**
	 * M&eacutetodo para llenar las estructuras de <code>Graph</code> y 
	 * <code>Line</code> de <code>GUI</code>.
	 */
/*	
	public void fillGUI() {
		
		gui.setGraph(g);
		
		for(int i = 0; i < g.getNumVertices(); i++) {
				
			StructV stv = (StructV) g.getVertexAt(i).getValue();
			gui.addSprite(stv.getSprite());
		}
		
		for(int i = 0; i < g.getNumEdges(); i++) {
				
			StructE ste = (StructE) g.getEdgeAt(i).getWeight();
			gui.addLine(ste.getLine());
		}		
	}
*/	
}