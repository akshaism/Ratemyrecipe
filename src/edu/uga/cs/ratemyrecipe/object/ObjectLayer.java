package edu.uga.cs.ratemyrecipe.object;

import edu.uga.cs.ratemyrecipe.persistence.PersistenceLayer;

public interface ObjectLayer
{
	public void upload(String recipeName,String recipeDescription,String category,String step1,String step2,String step3,String step4,String step5);
    
	public void setPersistence( PersistenceLayer persistence );

}
