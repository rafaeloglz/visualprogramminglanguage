package graph;
 
import dataio.CodeWriter;
import sprite.Sprite;
import sprite.SpriteEnd;
import sprite.SpriteFor;
import sprite.SpriteGlobalVar;
import sprite.SpriteIf;
import sprite.SpriteUnion;
import sprite.SpriteVar;
import sprite.SpriteWhile;
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
	private ArrayList<Sprite> unionList;
	private ArrayList<String> nombresMetodos;
	private Graph g;

	/**
	 * Constructor por omisi&oacuten.
	 * 
	 * @param g			<code>Graph</code>
	 */
 	public CodeMaker(ArrayList<Graph> graphs, ArrayList<String> nombresMetodos) { 		
 		this.graphs = graphs;
 		this.nombresMetodos = nombresMetodos;
 		
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
 		
 		whileList = new ArrayList<Sprite>();
 		forList = new ArrayList<Sprite>();
 		ifList = new ArrayList<Vertex<StructV>>();
 		unionList = new ArrayList<Sprite>(); 		
 	}
 	
 	/**
 	 * M&eacutetodo para hacer el c&oacutedigo a partir del contenido de
 	 * los v&eacutertices en el grafo.
 	 */
 	public boolean make() {

 		boolean codeGenerated = false;
 		
 		for(int i=0;i<graphs.size();i++){
 			g = graphs.get(i);
 			
 			if (i == 0){
 				addGlobalVariables(g);
 			}
 			
	 		vertices = g.getVertices();
	 		edges = g.getEdges();
	 		head = g.getHead();
	
	 		if (head == null){
	 			continue;
	 		}

	 		if(g.getNumVertices() > 0) {	
	 			recurse(head);
	 			codeGenerated = true;
	 		}	 		
	 		else{
	 			codeGenerated = false;
	 		}
 		}
 		
 		return codeGenerated;
 	}
 	
 	/**
 	 * M&eacutetodo recursivo para escribir el c&oacutedigo en un archivo.
 	 * 
 	 * param path			<code>String</code>
 	 * param filename		<code>String</code>
 	 */
 	public boolean writeToFile(String path, String filename) {

 		code = "class "+filename.replace(".java", "")+"{\n" + code;
 		
 		for(int i=0;i<nombresMetodos.size();i++){	 		
 			code = code.replaceFirst("genericMethod", nombresMetodos.get(i));
 		}

 		code = code + "\n}";
 		
 		cw = new CodeWriter(code, path, filename);
 		return cw.write();

 	} 	
	
	/**
	 * M&eacutetodo recursivo para hacer un recorrido del grafo para obtener el
	 * valor de cada v&eacutertice.
	 * 
	 * param index			<code>int</code>
	 *
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
	 * @param v
	 * @return
	 */
	public boolean recurse(Vertex<StructV> v){

		if(!(v.getValue().getSprite() instanceof SpriteUnion))
			precondition(v);		

		ArrayList<Vertex<StructV>> neighbors = v.getNeighbors();
		
		for(int i=0;i<neighbors.size();i++){		
			Vertex<StructV> tmp = neighbors.get(i);

			if(tmp == null)
				break;
			
			if(whileCase(tmp)) continue;
			if(forCase(tmp)) continue;
			if(ifCase(tmp)) continue;
			if(unionCase(tmp)) continue;
			
			//System.out.println(tmp.getValue().getSprite().getClass());
			recurse(tmp);
		}

		if(!(v.getValue().getSprite() instanceof SpriteIf))
			postcondition(v);

		return false;
	}

	private boolean whileCase(Vertex<StructV> tmp){

		if(tmp.getValue().getSprite() instanceof SpriteWhile){
			if(whileList.size()!= 0){
				Sprite currentWhile = whileList.get(whileList.size()-1);
				
				if(!currentWhile.equals(tmp.getValue().getSprite())){
					whileList.add(tmp.getValue().getSprite());
				}
				else{
					whileList.remove(whileList.size()-1);
					return true;
				}
			}					
			else{
				whileList.add(tmp.getValue().getSprite());
			}
		}
		
		return false;
	}

	private boolean forCase(Vertex<StructV> tmp){

		if(tmp.getValue().getSprite() instanceof SpriteFor){
			if(forList.size()!= 0){
				Sprite currentFor = forList.get(forList.size()-1);
				
				if(!currentFor.equals(tmp.getValue().getSprite())){
					forList.add(tmp.getValue().getSprite());
				}
				else{
					forList.remove(forList.size()-1);
					return true;
				}
			}					
			else{
				forList.add(tmp.getValue().getSprite());
			}
		}

		return false;
	}	
	
	private boolean ifCase(Vertex<StructV> tmp){

		if(tmp.getValue().getSprite() instanceof SpriteIf){
			if(ifList.size()!= 0){
				Sprite currentIf = ifList.get(ifList.size()-1).getValue().getSprite();
				
				if(!currentIf.equals(tmp.getValue().getSprite())){
					ifList.add(tmp);
				}				
			}					
			else{
				ifList.add(tmp);
			}
		}

		return false;
	}
	
	private boolean unionCase(Vertex<StructV> tmp){
		
		if(tmp.getValue().getSprite() instanceof SpriteUnion){
			if(unionList.size()!= 0){
				unionList.remove(unionList.size()-1);
				precondition(tmp);
				return false;
			}					
			else{
				if(ifList.size()>0){
					Vertex<StructV> ifTemp = ifList.get(ifList.size()-1);
					postcondition(ifTemp);
					ifList.remove(ifList.size()-1);
				}

				unionList.add(tmp.getValue().getSprite());
				return true;
			}
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
		
		if(name.equals("begin"))
			addLocalVariables(g);		
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
	
	private ArrayList<Hashtable> getGlobalVariables(Graph g){	
		
		ArrayList<Hashtable> globalVariables = new ArrayList<Hashtable>();
		
		for(int i = 0; i < g.getNumVertices(); i++){			
			Sprite  tempSprite = ((StructV)g.getVertexAt(i).getValue()).getSprite();
			Hashtable tempTable = (Hashtable)((StructV)g.getVertexAt(i).getValue()).getValue();
			if (tempSprite instanceof SpriteGlobalVar){
				globalVariables.add(tempTable);				
			}
		}
		
		return globalVariables;
	}
	
	private ArrayList<Hashtable> getLocalVariables(Graph g){

		ArrayList<Hashtable> localVariables = new ArrayList<Hashtable>();

		for(int i = 0; i < g.getNumVertices(); i++){			
			Sprite  tempSprite = ((StructV)g.getVertexAt(i).getValue()).getSprite();
			Hashtable tempTable = (Hashtable)((StructV)g.getVertexAt(i).getValue()).getValue();
			if (tempSprite instanceof SpriteVar)
				localVariables.add(tempTable);
		}		
		return localVariables;		
	}
	
	private void addGlobalVariables(Graph g){
		ArrayList<Hashtable> globalVariables = getGlobalVariables(g);
		for(int j = 0; j < globalVariables.size(); j++){
			Hashtable tempTable = globalVariables.get(j);
			for(int k = 0; k < tempTable.size(); k++){ 						
				String var = (String)tempTable.get("var"+k);
				if (var != null){
					if(!var.contains("null")) {
						code += var + ";" + "\r";
					}							
				}
			}
		}
	}
	
	private void addLocalVariables(Graph g){
		ArrayList<Hashtable> localVariables = getLocalVariables(g);
		for(int j = 0; j < localVariables.size(); j++){
			Hashtable tempTable = localVariables.get(j);
			for(int k = 0; k < tempTable.size(); k++){ 						
				String var = (String)tempTable.get("var"+k);
				if (var != null){
					if(!var.contains("null")) {
						code += var + ";" + "\r";
					}							
				}
			}
		}
	}
 }