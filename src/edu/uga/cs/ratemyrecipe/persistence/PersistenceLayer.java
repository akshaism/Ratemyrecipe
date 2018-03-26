package edu.uga.cs.ratemyrecipe.persistence;

public interface PersistenceLayer
{
	public void upload(String recipeName,String recipeDescription,String category,String step1,String step2,String step3,String step4,String step5);
   
}
