/**
 * Clase que inicia al Builder es un Singleton
 * @author Andr&eacute Freyr&iacutea Cedeño
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figuero Ram&iacuterez
 * @author Jos&eacute Roberto Ram&iacuterez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package gui;

import struct.StructV;

public class ActivityDirector {
	
	
	private static ActivityDirector instance;
	
	private ActivityDirector(){}
	
	public static ActivityDirector getInstance(){
		if(instance == null){
			instance = new ActivityDirector();
		}
		return instance;
	}
	
	public void construct(ActivityBuilder activityBuilder, String activity, StructV stv){		
		activityBuilder.buildActivity(activity, stv);		
	}

}
