package graph;
 
import dataio.CodeWriter;
import sprite.Sprite;
import sprite.SpriteEnd;
import sprite.SpriteIf;
import sprite.SpriteUnion;
import struct.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JTextField;
 
 public class CodeMaker {
 	
 	private String code;
 	private Graph g;
 	private CodeWriter cw;
 	private String method;
 	private ArrayList<Vertex<StructV>> vertices;
	private ArrayList<Edge<StructV, StructE>> edges;
	private Vertex<StructV> head;
	private Vertex<StructV> tail;
	private ArrayList<String> template;
	private boolean isThenPath;
 		
	/**
	 * Constructor por omisi&oacuten.
	 * 
	 * @param g			<code>Graph</code>
	 */
 	public CodeMaker(Graph g) { 		
 		this.g = g;
 		//this.mergeEndVertex();
 		code = "";
 		String configFile="code.txt";
 		template = new ArrayList<String>();
 		
 		try {
			BufferedReader temp = new BufferedReader(new FileReader(configFile));
						
			while (temp.ready()){
				template.add(temp.readLine().replaceFirst("^#.*", ""));
			}
			
			temp.close();
			
 		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 		isThenPath = false;
 	}
 	
 	/**
 	 * M&eacutetodo para hacer el c&oacutedigo a partir del contenido de
 	 * los v&eacutertices en el grafo.
 	 */
 	public boolean make() {

 		vertices = g.getVertices();
 		edges = g.getEdges();
 		head = g.getHead();

 		if (head == null) return false;
 		
 		if(g.getNumVertices() > 0) {

 			recurse(head);

 			return true;
 		}
 		
 		else return false;
 	}
 	
 	/**
 	 * M&eacutetodo recursivo para escribir el c&oacutedigo en un archivo.
 	 * 
 	 * param path			<code>String</code>
 	 * param filename		<code>String</code>
 	 */
 	public boolean writeToFile(String path, String filename) {
 		
 		code = code.replace("genericMethod",filename.replace(".java", ""));
 		cw = new CodeWriter(code, path, filename);
 		return cw.write();
 		
 	}

	/**
 	 * M&eacutetodo que hace que todos los vertices fin del grafo sean uno solo
 	 * 
 	 */
	private void mergeEndVertex(){
		Vertex end = null;

		for(int i=0;i<g.getNumEdges();i++){
			StructV tempStruct = (StructV) g.getEdgeAt(i).getDest().getValue();
			if(tempStruct.getSprite().getClass() == SpriteEnd.class){				
				if(end == null){ 
					end = g.getEdgeAt(i).getDest();
				}
				
				g.getEdgeAt(i).getDest().setValue(end);
			}
		}
		tail = end;
	} 	
	
	/*
	public void searchGraph() {
		
		StructV stv;
		int num;
		
		stv = (StructV)g.getVertexAt(index).getValue();
		code += (String)stv.toString() + "\n";
		num = g.getVertexAt(index).getNumNeighbors();	
		
		for(int i = 0; i < g.getNumVertices(); i++)
			for(int j = 0; j < num; j++)
				if(g.getVertexAt(i).equals(g.getVertexAt(index).getNeighborAt(j)))
					searchGraph(i);
	}*/	
	
	/**
	 * M&eacutetodo recursivo para hacer un recorrido del grafo para obtener el
	 * valor de cada v&eacutertice.
	 * 
	 * param index			<code>int</code>
	 *
	*/

	public boolean recurse(Vertex<StructV> v){

		precondition(v);
		postcondition(v);

		if(isThenPath && v.getValue().getValue() instanceof SpriteUnion)
			return true;
		
		ArrayList<Vertex<StructV>> neighbors = v.getNeighbors();
		Iterator<Vertex<StructV>> iterator = neighbors.iterator();
		
		int i = 0;
		
		while (iterator.hasNext()){

			if(i==0 && v.getValue().getValue() instanceof SpriteIf)
				isThenPath = true;			

			Vertex<StructV> tmp = iterator.next();

			recurse(tmp);
			i++;
		}

		return false;
	}

	public void precondition(Vertex<StructV> v){	 		
		replaceVars((Hashtable)v.getValue().getValue());
	}

	public void postcondition(Vertex<StructV> v){

		String name = (String) ((Hashtable)v.getValue().getValue()).get("name");

		int line = search(name);

		if (line == -1) {
			code+="\r";
			//System.out.println("Adding Newline");
			return;
		}

		if (!(template.get(line+2).matches("[\\s]*")))//If the line isn't empty:
			code += template.get(line+2)+"\r";
	}

	private void replaceVars(Hashtable h){
		
		String name = (String) h.get("name");
		
		int line = search(name);
		
		if (line == -1) {
			code+="\r";
			//System.out.println("Adding Newline");
			return;
		}
		
		//System.out.println("Line found: "+template[line]);
		
		String regex = "\\$\\w*";
		String vars[] = template.get(line+1).split(" ");
		String result = template.get(line+1);
		
		int i = 0;
		while (i < vars.length){
			if (vars[i].matches(regex))//If the String equals $.*
				result = replaceVar(result,vars[i],h);
			i++;
		}		
		code+=result+"\r";
	}
	
	private String replaceVar(String result, String var, Hashtable h) {

		String data = (String) h.get(var.substring(1));
		
		//System.out.println("Inside ReplaceVar :\n"+
				//"Line: "+result+"\nVar: "+var);
		
		if (data != null)
			result = result.replace(var,data);
		else {
			//System.out.println("Couldn't find Var in Hashtable.");
			int line = search(var);
			//System.out.println(template[line]);
			if (line == -1 || !(template.get(line).startsWith(var))){
				//System.out.println("Couldn't find Var in code.txt, adding space.");
				result = result.replace(var, " ");
				return result;
			} else {
			//System.out.println("Replacing from code.txt");
			//System.out.println("Value: "+template[line].replace(var+" = ", ""));
			result = result.replace(var, template.get(line).replace(var+" = ", ""));
			//System.out.println("Result: "+result);
			}
		}
		return result.trim();
	}

	private int search(String word){
					
		for(int i = 0; i < template.size(); i++){
			if(template.get(i).contains(word))
				return i;
		}
		
		return -1;
	}	
 }