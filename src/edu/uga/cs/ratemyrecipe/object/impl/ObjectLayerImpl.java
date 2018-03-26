package edu.uga.cs.ratemyrecipe.object.impl;

import edu.uga.cs.ratemyrecipe.object.ObjectLayer;
import edu.uga.cs.ratemyrecipe.persistence.PersistenceLayer;
import edu.uga.cs.ratemyrecipe.persistence.impl.Persistent;

public class ObjectLayerImpl implements ObjectLayer {
	
	PersistenceLayer persistence = null;
	
	public void upload(String recipeName,String recipeDescription,String category,String step1,String step2,String step3,String step4,String step5)
	{
		persistence.upload(recipeName, recipeDescription, category, step1, step2,step3,step4,step5);
	}
	 
	 public void setPersistence(PersistenceLayer persistence)
	    {
	        this.persistence = persistence;        
	    }
}
