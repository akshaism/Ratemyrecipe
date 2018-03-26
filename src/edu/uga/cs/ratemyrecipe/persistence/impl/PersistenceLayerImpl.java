package edu.uga.cs.ratemyrecipe.persistence.impl;

import java.sql.Connection;
import java.util.List;

import edu.uga.cs.ratemyrecipe.RARException;
import edu.uga.cs.ratemyrecipe.object.ObjectLayer;
import edu.uga.cs.ratemyrecipe.object.impl.ObjectLayerImpl;
import edu.uga.cs.ratemyrecipe.persistence.PersistenceLayer;

public class PersistenceLayerImpl
	implements PersistenceLayer {
	
	private UploadManager uploadManager = null;
	private ObjectLayerImpl oLayer = new ObjectLayerImpl();
	
	public PersistenceLayerImpl(Connection conn, ObjectLayer oLayer) {
		uploadManager = new UploadManager(conn, oLayer);
		System.out.println("PersistenceLayerImpl.PersistenceLayerImpl(conn,oLayer): initialized");
	}
	public void upload(String recipeName,String recipeDescription,String category,String step1,String step2,String step3,String step4,String step5)
	{
		uploadManager.upload(recipeName, recipeDescription, category, step1, step2,step3,step4,step5);
	}
}
