package edu.uga.cs.ratemyrecipe.logic.impl;

import edu.uga.cs.ratemyrecipe.object.ObjectLayer;

public class UpdateCtrl {

	private ObjectLayer objectLayer = null;
	
	public UpdateCtrl(ObjectLayer objectModel)
	{
		this.objectLayer = objectModel;
	}
	public void upload(String recipeName,String recipeDescription,String category,String step1,String step2,String step3,String step4,String step5)
	{
		objectLayer.upload(recipeName, recipeDescription, category, step1, step2,step3,step4,step5);
	}
}
