package edu.uga.cs.ratemyrecipe.logic.impl;

import java.sql.Connection;

import edu.uga.cs.ratemyrecipe.logic.LogicLayer;
import edu.uga.cs.ratemyrecipe.object.ObjectLayer;
import edu.uga.cs.ratemyrecipe.object.impl.ObjectLayerImpl;
import edu.uga.cs.ratemyrecipe.persistence.PersistenceLayer;
import edu.uga.cs.ratemyrecipe.persistence.impl.PersistenceLayerImpl;

public class LogicLayerImpl implements LogicLayer
{
    private ObjectLayer objectLayer = null;
    
    public LogicLayerImpl( Connection conn )
    {
        objectLayer = new ObjectLayerImpl();
        PersistenceLayer persistenceLayer = new PersistenceLayerImpl( conn, objectLayer );
        objectLayer.setPersistence( persistenceLayer );
        System.out.println( "LogicLayerImpl.LogicLayerImpl(conn): initialized" );
    }
    
    public LogicLayerImpl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
        System.out.println( "LogicLayerImpl.LogicLayerImpl(objectLayer): initialized" );
    }
    

    public void upload(String recipeName,String recipeDescription,String category,String step1,String step2,String step3,String step4,String step5)
    {
    		UpdateCtrl ctrlUpdate = new UpdateCtrl(objectLayer);
    		ctrlUpdate.upload(recipeName, recipeDescription, category, step1, step2,step3,step4,step5);
    }
}
