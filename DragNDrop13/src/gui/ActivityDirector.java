package gui;

import struct.StructV;

public class ActivityDirector {
	
	public void construct(ActivityBuilder activityBuilder, String activity, StructV stv){		
		activityBuilder.buildActivity(activity, stv);		
	}

}
