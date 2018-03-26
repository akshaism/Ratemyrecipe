package edu.uga.cs.ratemyrecipe.persistence.impl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.ratemyrecipe.object.ObjectLayer;

class UploadManager
{
    private ObjectLayer objectLayer = null;
    private Connection  conn = null;
    
    public UploadManager( Connection conn, ObjectLayer objectLayer )
    {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void upload(String recipeName,String recipeDescription,String category,String step1,String step2,String step3,String step4,String step5)
    {
    		String               insertRecipeSql = "insert into Recipe (recipe_name, recipe_description, vote_sum, num_votes, user_id, category_id) values (?, ?, ?, ?, ?, ?)"; 
    		String               insertStepsSql1 = "insert into Steps (recipe_id, step_num, step_instruction) values (?, ?, ?)"; 
    		String               insertStepsSql2 = "insert into Steps (recipe_id, step_num, step_instruction) values (?, ?, ?)"; 
    		String               insertStepsSql3 = "insert into Steps (recipe_id, step_num, step_instruction) values (?, ?, ?)"; 
    		String               insertStepsSql4 = "insert into Steps (recipe_id, step_num, step_instruction) values (?, ?, ?)"; 
    		String               insertStepsSql5 = "insert into Steps (recipe_id, step_num, step_instruction) values (?, ?, ?)"; 
        PreparedStatement    stmt;
        int                  inscnt;
        long                 vehicleId;
        
        try {
            	stmt = (PreparedStatement) conn.prepareStatement( insertRecipeSql );
            stmt.setString( 1, recipeName);
            stmt.setString( 2, recipeDescription);
            stmt.setInt( 3, 1);
            stmt.setInt( 4, 1);
            stmt.setInt( 5, 1);
            stmt.setInt( 6, Integer.parseInt(category));
            inscnt = stmt.executeUpdate();
        }
        catch( SQLException e ) {
            e.printStackTrace();
        }
        try {
        	stmt = (PreparedStatement) conn.prepareStatement( insertStepsSql1 );
        stmt.setInt( 1, 12);
        stmt.setInt( 2, 1);
        stmt.setString( 3, step1);
        inscnt = stmt.executeUpdate();
        }
        catch( SQLException e ) {
        		e.printStackTrace();
        }
        try {
        	stmt = (PreparedStatement) conn.prepareStatement( insertStepsSql1 );
        stmt.setInt( 1, 12);
        stmt.setInt( 2, 2);
        stmt.setString( 3, step2);
        inscnt = stmt.executeUpdate();
        }
        catch( SQLException e ) {
        		e.printStackTrace();
        }
        try {
        	stmt = (PreparedStatement) conn.prepareStatement( insertStepsSql1 );
        stmt.setInt( 1, 12);
        stmt.setInt( 2, 3);
        stmt.setString( 3, step3);
        inscnt = stmt.executeUpdate();
        }
        catch( SQLException e ) {
        		e.printStackTrace();
        }
        try {
        	stmt = (PreparedStatement) conn.prepareStatement( insertStepsSql1 );
        stmt.setInt( 1, 12);
        stmt.setInt( 2, 4);
        stmt.setString( 3, step4);
        inscnt = stmt.executeUpdate();
        }
        catch( SQLException e ) {
        		e.printStackTrace();
        }
        try {
        	stmt = (PreparedStatement) conn.prepareStatement( insertStepsSql1 );
        stmt.setInt( 1, 12);
        stmt.setInt( 2, 5);
        stmt.setString( 3, step5);
        inscnt = stmt.executeUpdate();
        }
        catch( SQLException e ) {
        		e.printStackTrace();
        }
    }
}
