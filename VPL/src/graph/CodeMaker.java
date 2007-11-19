/**
 * Clase que recorre el grafo para generar el c&oacute;digo del programa.
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package graph;

import dataio.CodeWriter;
import sprite.Sprite;
import sprite.SpriteFor;
import sprite.SpriteGlobalVar;
import sprite.SpriteIf;
import sprite.SpriteParallel;
import sprite.SpriteSync;
import sprite.SpriteUnion;
import sprite.SpriteVar;
import sprite.SpriteWhile;
import struct.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class CodeMaker {

	private String code;
	private String parallelCode;
	private ArrayList<Graph> graphs;
	private CodeWriter cw;
	private String method;
	private ArrayList<Vertex<StructV>> vertices;
	private ArrayList<Edge<StructV, StructE>> edges;
	private Vertex<StructV> head;
	private Vertex<StructV> tail;
	private ArrayList<String> template;
	private ArrayList<Sprite> whileList;
	private ArrayList<Sprite> forList;
	private ArrayList<Vertex<StructV>> ifList;
	private ArrayList<Vertex<StructV>> parallelList;
	private ArrayList<Sprite> unionList;
	private ArrayList<String> nombresMetodos;
	private ArrayList threadCountHist;
	private Graph g;	
	private int classCount;
	private int classCount2;
	private int threadCount;
	private int parCount;
	private boolean isParallel = false;

	/**
	 * Constructor por omisi&oacute;n.
	 * 
	 * @param graphs 
	 *            <code>ArrayList<Graph></code>
	 * @param nombresMetodos 
	 *            <code>ArrayList<String></code>            
	 */
	public CodeMaker(ArrayList<Graph> graphs, ArrayList<String> nombresMetodos) {
		this.graphs = graphs;
		this.nombresMetodos = nombresMetodos;
		
		classCount2 =0;
		threadCount = 0;
		parCount = 0;
		parallelCode = "";
		code = "";
		String configFile = "code.txt";
		template = new ArrayList<String>();

		try {
			BufferedReader temp = new BufferedReader(new FileReader(configFile));

			while (temp.ready()) {
				template.add(temp.readLine().replaceFirst("^#.*", ""));
			}

			temp.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		whileList = new ArrayList<Sprite>();
		forList = new ArrayList<Sprite>();
		ifList = new ArrayList<Vertex<StructV>>();
		unionList = new ArrayList<Sprite>();
		parallelList = new ArrayList <Vertex<StructV>>();
		threadCountHist = new ArrayList();
	}

	/**
	 * M&eacute;todo para hacer el c&oacute;digo a partir del contenido de los
	 * v&eacute;rtices en el grafo.
	 */
	public boolean make() {

		boolean codeGenerated = false;

		for (int i = 0; i < graphs.size(); i++) {
			g = graphs.get(i);

			if (i == 0) {
				addGlobalVariables(g);				
			}

			vertices = g.getVertices();
			edges = g.getEdges();
			head = g.getHead();

			if (head == null) {
				continue;
			}

			if (g.getNumVertices() > 0) {
				recurse(head);
				codeGenerated = true;
			} else {
				codeGenerated = false;
			}
		}

		return codeGenerated;
	}

	/**
	 * M&eacute;todo recursivo para escribir el c&oacute;digo en un archivo.
	 * 
	 * param path <code>String</code> param filename <code>String</code>
	 */
	public boolean writeToFile(String path, String filename) {

		code = "public class " + filename.replace(".java", "") + "{\n" + code;		

		for (int i = 0; i < nombresMetodos.size(); i++) {
			code = code.replaceFirst("genericMethod", nombresMetodos.get(i));
		}

		code = code + "}" + "\r";
		code += parallelCode;

		cw = new CodeWriter(code, path, filename);
		return cw.write();

	}

	/**
	 * M&eacute;todo recursivo para hacer un recorrido del grafo para obtener el
	 * valor de cada v&eacute;rtice.
	 * 
	 * @param v
	 *            <code>Vertex<StructV></code>
	 * 
	 */
	public boolean recurse(Vertex<StructV> v) {

		if (!(v.getValue().getSprite() instanceof SpriteUnion) && !(v.getValue().getSprite() instanceof SpriteSync) && !(v.getValue().getSprite() instanceof SpriteParallel))
			precondition(v);

		ArrayList<Vertex<StructV>> neighbors = v.getNeighbors();

		for (int i = 0; i < neighbors.size(); i++) {
			Vertex<StructV> tmp = neighbors.get(i);

			if (tmp == null)
				break;
			if (whileCase(tmp))
				continue;
			if (forCase(tmp))
				continue;
			if (ifCase(tmp))
				continue;
			if (unionCase(tmp))
				continue;
			if(parallelCase(tmp))
				continue;
			if(syncCase(tmp))
				continue;

			recurse(tmp);
		}

		if (!(v.getValue().getSprite() instanceof SpriteIf) && !(v.getValue().getSprite() instanceof SpriteParallel) && !(v.getValue().getSprite() instanceof SpriteSync))
			postcondition(v);

		return false;
	}

	/**
	 * M&eacute;todo que lleva el control sobre el recorrido para el caso
	 * especial del While
	 * 
	 * @param tmp
	 *            Vertice Acual <code>Vertex<StructV></code>
	 * 
	 */

	private boolean whileCase(Vertex<StructV> tmp) {

		if (tmp.getValue().getSprite() instanceof SpriteWhile) {
			if (whileList.size() != 0) {
				Sprite currentWhile = whileList.get(whileList.size() - 1);

				if (!currentWhile.equals(tmp.getValue().getSprite())) {
					whileList.add(tmp.getValue().getSprite());
				} else {
					whileList.remove(whileList.size() - 1);
					return true;
				}
			} else {
				whileList.add(tmp.getValue().getSprite());
			}
		}

		return false;
	}

	/**
	 * M&eacute;todo que lleva el control sobre el recorrido para el caso
	 * especial del For
	 * 
	 * @param tmp
	 *            Vertice Acual <code>Vertex<StructV></code>
	 * 
	 */

	private boolean forCase(Vertex<StructV> tmp) {

		if (tmp.getValue().getSprite() instanceof SpriteFor) {
			if (forList.size() != 0) {
				Sprite currentFor = forList.get(forList.size() - 1);

				if (!currentFor.equals(tmp.getValue().getSprite())) {
					forList.add(tmp.getValue().getSprite());
				} else {
					forList.remove(forList.size() - 1);
					return true;
				}
			} else {
				forList.add(tmp.getValue().getSprite());
			}
		}

		return false;
	}

	/**
	 * M&eacute;todo que lleva el control sobre el recorrido para el caso
	 * especial del If
	 * 
	 * @param tmp
	 *            Vertice Acual <code>Vertex<StructV></code>
	 * 
	 */

	private boolean ifCase(Vertex<StructV> tmp) {

		if (tmp.getValue().getSprite() instanceof SpriteIf) {
			if (ifList.size() != 0) {
				Sprite currentIf = ifList.get(ifList.size() - 1).getValue()
				.getSprite();

				if (!currentIf.equals(tmp.getValue().getSprite())) {
					ifList.add(tmp);
				}
			} else {
				ifList.add(tmp);
			}
		}

		return false;
	}

	/**
	 * M&eacute;todo que lleva el control sobre el recorrido para el caso
	 * especial de la Union
	 * 
	 * @param tmp
	 *            Vertice Acual <code>Vertex<StructV></code>
	 * 
	 */

	private boolean unionCase(Vertex<StructV> tmp) {

		if (tmp.getValue().getSprite() instanceof SpriteUnion) {
			if (unionList.size() != 0) {
				unionList.remove(unionList.size() - 1);
				precondition(tmp);
				return false;
			} else {
				if (ifList.size() > 0) {
					Vertex<StructV> ifTemp = ifList.get(ifList.size() - 1);
					postcondition(ifTemp);
					ifList.remove(ifList.size() - 1);
				}

				unionList.add(tmp.getValue().getSprite());
				return true;
			}
		}
		return false;
	}

	private boolean parallelCase(Vertex<StructV> tmp){
		if (tmp.getValue().getSprite() instanceof SpriteParallel) {
			if(parallelList.isEmpty()){
				for(int i = 0; i < tmp.getNumNeighbors(); i++)
					parallelList.add(tmp);
				startThreads();
				isParallel = true;
				precondition(tmp);
				postcondition(tmp);
				parCount++;
			}
		}
		return false;
	}

	private boolean syncCase(Vertex<StructV> tmp){
		if (tmp.getValue().getSprite() instanceof SpriteSync) {
			if(parallelList.size() > 1){				
				Vertex<StructV> parTemp = parallelList.get(parallelList.size()-1);
				precondition(tmp);
				postcondition(tmp);
				precondition(parTemp);
				postcondition(parTemp);
				parallelList.remove(parallelList.size()-1);
				return true;
			}
			else if (parallelList.size() == 1){
				precondition(tmp);
				postcondition(tmp);
				parallelList.remove(parallelList.size()-1);
				isParallel = false;
			}
		}		
		return false;		
	}

	/**
	 * M&eacute;todo que genera las precondiciones para el vertice actual
	 * 
	 * @param v
	 *            Vertice Acual <code>Vertex<StructV></code>
	 * 
	 */

	public void precondition(Vertex<StructV> v) {
		replaceVars((Hashtable) v.getValue().getValue());
	}

	/**
	 * M&eacute;todo que genera las postcondiciones para el vertice actual
	 * 
	 * @param v
	 *            Vertice Acual <code>Vertex<StructV></code>
	 * 
	 */

	public void postcondition(Vertex<StructV> v) {

		String name = (String) ((Hashtable) v.getValue().getValue())
		.get("name");

		int line = search(name);

		if (line == -1) {
			if(isParallel)
				parallelCode += "\r";
			else
				code += "\r";
			return;
		}

		if (!(template.get(line + 2).matches("[\\s]*")))
			if(isParallel)
				parallelCode += template.get(line + 2) + "\r";
			else
				code += template.get(line + 2) + "\r";		
	}

	/**
	 * M&eacute;todo que remplaza las variables del template tomando los valores
	 * de la tabla hash
	 * 
	 * @param h
	 *            HasTable <code>Vertex<StructV></code>
	 * 
	 */

	private void replaceVars(Hashtable h) {

		String name = (String) h.get("name");
		int line = search(name);

		System.out.println(name);

		if (line == -1) {
			if(isParallel)
				parallelCode += "\r";
			else
				code += "\r";
			return;
		}

		String regex = "\\$\\w*";
		String vars[] = template.get(line + 1).split(" ");
		String result = template.get(line + 1);

		int i = 0;

		while (i < vars.length) {
			if (vars[i].matches(regex))
				result = replaceVar(result, vars[i], h);
			i++;
		}

		if(isParallel)
			parallelCode += result + "\r";
		else
			code += result + "\r";

		if (name.equals("begin")){
			addLocalVariables(g);
			buildThreads(g);			
		}	
	}

	/**
	 * M&eacute;todo que remplaza las variables del template
	 * 
	 * @param h
	 *            HasTable <code>Vertex<StructV></code>
	 * 
	 */

	private String replaceVar(String result, String var, Hashtable h) {
		String data;
		String name = (String) h.get("name");

		if(name.equals("parallel")){
			data = "Thread" + classCount;
			classCount++;
		}

		else
			data = (String) h.get(var.substring(1));


		if (data != null)
			result = result.replace(var, data);
		else {

			int line = search(var);
			if (line == -1 || !(template.get(line).startsWith(var))) {
				result = result.replace(var, " ");
				return result;
			} else {
				result = result.replace(var, template.get(line).replace(
						var + " = ", ""));
			}
		}
		return result.trim();
	}

	/**
	 * M&eacute;todo que busca una palabra dentro del template de c&oacute;;digo
	 * 
	 * @param word
	 *            Palabra a buscar <code>Vertex<StructV></code>
	 * 
	 */

	private int search(String word) {

		for (int i = 0; i < template.size(); i++) {
			if (template.get(i).contains(word))
				return i;
		}

		return -1;
	}

	/**
	 * M&eacute;todo que obtiene un arreglo de las tablas Hash de las variables
	 * Globales
	 * 
	 * @param g
	 *            Grafo <code>Vertex<StructV></code>
	 * 
	 */

	private ArrayList<Hashtable> getGlobalVariables(Graph g) {

		ArrayList<Hashtable> globalVariables = new ArrayList<Hashtable>();

		for (int i = 0; i < g.getNumVertices(); i++) {
			Sprite tempSprite = ((StructV) g.getVertexAt(i).getValue())
			.getSprite();
			Hashtable tempTable = (Hashtable) ((StructV) g.getVertexAt(i)
					.getValue()).getValue();
			if (tempSprite instanceof SpriteGlobalVar) {
				globalVariables.add(tempTable);
			}
		}

		return globalVariables;
	}

	/**
	 * M&eacute;todo que obtiene un arreglo de las tablas Hash de las variables
	 * Locales
	 * 
	 * @param g
	 *            Grafo <code>Vertex<StructV></code>
	 * 
	 */

	private ArrayList<Hashtable> getLocalVariables(Graph g) {

		ArrayList<Hashtable> localVariables = new ArrayList<Hashtable>();

		for (int i = 0; i < g.getNumVertices(); i++) {
			Sprite tempSprite = ((StructV) g.getVertexAt(i).getValue())
			.getSprite();
			Hashtable tempTable = (Hashtable) ((StructV) g.getVertexAt(i)
					.getValue()).getValue();
			if (tempSprite instanceof SpriteVar)
				localVariables.add(tempTable);
		}
		return localVariables;
	}

	/**
	 * M&eacute;todo que agrega las variables globales definidas al Grafo
	 * indicado
	 * 
	 * @param g
	 *            Grafo <code>Vertex<StructV></code>
	 * 
	 */

	private void addGlobalVariables(Graph g) {
		ArrayList<Hashtable> globalVariables = getGlobalVariables(g);
		for (int j = 0; j < globalVariables.size(); j++) {
			Hashtable tempTable = globalVariables.get(j);
			for (int k = 0; k < tempTable.size(); k++) {
				String var = (String) tempTable.get("var" + k);
				if (var != null) {
					if (!var.contains("null")) {
						code += var + ";" + "\r";
					}
				}
			}
		}
	}

	/**
	 * M&eacute;todo que agrega las variables locales definidas al Grafo indicado
	 * 
	 * @param g
	 *            Grafo <code>Vertex<StructV></code>
	 * 
	 */

	private void addLocalVariables(Graph g) {
		ArrayList<Hashtable> localVariables = getLocalVariables(g);
		for (int j = 0; j < localVariables.size(); j++) {
			Hashtable tempTable = localVariables.get(j);
			for (int k = 0; k < tempTable.size(); k++) {
				String var = (String) tempTable.get("var" + k);
				if (var != null) {
					if (!var.contains("null")) {
						code += var + ";" + "\r";
					}
				}
			}
		}
	}

	private void insertThreads(int threadNum, int threadRep, int totalThreads){

		if(threadCount == 0)
			code += "Thread[] threads = new Thread[" + totalThreads + "];" + "\r";
		code += "for(int i = 0; i < "+ threadRep +"; i++){" + "\r";
		code += "for(int j = 0; j < "+ threadNum +"; j++){" + "\r";
		code += "try{" + "\r";
		code += "threads[(j+2*i) + "+ threadCount +"] = (Thread)Class.forName(\"Thread\" + (j + "+classCount2+")).newInstance();" + "\r";
		code += "} catch (Exception e) {" + "\r";
		code += "}" + "\r";
		//code += "threads[(j+2*i) + "+threadCount+"].start();" + "\r";
		code += "}" + "\r";
		code += "}" + "\r";
		classCount2 += threadNum;
		threadCount += (threadNum * threadRep);
		threadCountHist.add(threadCount);
	}

	private int getNumThreads(Graph g){
		int coefficient;
		int totalThreads = 0;

		for(int i = 0; i < g.getNumVertices(); i++){
			Vertex<StructV> tempVertex = g.getVertexAt(i);
			Sprite tempSprite = ((StructV)g.getVertexAt(i).getValue()).getSprite();
			String stringCoefficient = (String)((Hashtable)tempVertex.getValue().getValue()).get("data");

			if(tempSprite instanceof SpriteParallel){
				if(stringCoefficient == null || stringCoefficient.isEmpty())
					coefficient = 1;
				else
					coefficient = Integer.parseInt(stringCoefficient);
				totalThreads += (coefficient * tempVertex.getNumNeighbors());				
			}
		}
		System.out.println(totalThreads);
		return totalThreads;
	}

	private void buildThreads(Graph g){
		int threadNum = 0;
		int coefficient;

		for(int i = 0; i < g.getNumVertices(); i++){
			Vertex<StructV> tempVertex = g.getVertexAt(i);
			Sprite tempSprite = ((StructV)g.getVertexAt(i).getValue()).getSprite();
			String stringCoefficient = (String)((Hashtable)tempVertex.getValue().getValue()).get("data");

			if(tempSprite instanceof SpriteParallel){
				if(stringCoefficient == null || stringCoefficient.isEmpty())
					coefficient = 1;
				else
					coefficient = Integer.parseInt(stringCoefficient);
				insertThreads(tempVertex.getNumNeighbors(), coefficient, getNumThreads(g));				
			}
		}		
	}

	private void startThreads(){
		int initIndex = 0;
		if(parCount != 0)
			initIndex = (Integer)threadCountHist.get(parCount-1);

		code += "for(int i = "+initIndex+"; i < "+threadCountHist.get(parCount)+"; i++)" + "\r";
		code += "threads[i].start();" + "\r";
		joinThreads();
	}

	private void joinThreads(){
		int initIndex = 0;
		if(parCount != 0)
			initIndex = (Integer)threadCountHist.get(parCount-1);

		code += "for(int i = "+initIndex+"; i < "+threadCountHist.get(parCount)+"; i++){" + "\r";
		code += "try{" + "\r";
		code += "threads[i].join();" + "\r";
		code += "} catch (InterruptedException e) {" + "\r";
		code += "e.printStackTrace();" + "\r";
		code += "}" + "\r";				
		code += "}" + "\r";
	}
}