package edu.uga.cs.ratemyrecipe.logic;

import edu.uga.cs.ratemyrecipe.session.Session;

public interface LogicLayer
{
    public void upload(String recipeName,String recipeDescription,String category,String step1,String step2,String step3,String step4,String step5);

}
