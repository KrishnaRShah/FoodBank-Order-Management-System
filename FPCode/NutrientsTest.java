package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;
public interface NutrientsTest {
    //example values for testing purposes
    public double grainsPercent=35;
    public double fruitsPercent=30;
    public double meatsPercent=20;
    public double otherPercent=15;
    public double totalCalories=1500;
    //example value for grains to test whether an exception will be thrown
    public double badGrainsPercent=40;

    public NutrientsTest(){}
   
    //test constructor of Nutrients
    @Test
    public void testNutrientsConstructorGoodData() {
        Nutrients first = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        assertNotNull("Nutrients constructor did not create an object when given valid inputs", first);
    }

    //test constructor when invalid afguments are provided
    @Test
    public void testNutrientsConstructorInvalidData() {
        
        boolean correctException = false;
        
        try{
            Nutrients second=new Nutrients(badGrainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }

        assertEquals("Nutrients constructor did not throw an IllegalArgumentException when given an invalid log string: ", true, correctException);        
    }

    //test getter for grains
    @Test
    public void testGetGRAINS() {
        Nutrients third = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundGRAINS=third.getGRAINS();
        double expectedGRAINS=grainsPercent*totalCalories/100.0;
        assertEquals("Method getGRAINS did not return the expected result: ", expectedGRAINS, foundGRAINS);
    }  
    //test getter for fruits
    @Test
    public void testGetFRUITS() {
        Nutrients fourth = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundFRUITS=third.getFRUITS();
        double expectedFRUITS=fruitsPercent*totalCalories/100.0;
        assertEquals("Method getFRUITS did not return the expected result: ", expectedFRUITS, foundFRUITS);
    }  
    //test getter for meats
    @Test
    public void testGetMEATS() {
        Nutrients fifth = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundMEATS=third.getMEATS();
        double expectedMEATS=meatsPercent*totalCalories/100.0;
        assertEquals("Method getMEATS did not return the expected result: ", expectedMEATS, foundMEATS);
    }
    //test getter for other
    @Test
    public void testGetOTHER() {
        Nutrients sixth = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundOTHER=third.getOTHER();
        double expectedOTHER=otherPercent*totalCalories/100.0;
        assertEquals("Method getFRUITS did not return the expected result: ", expectedOTHER, foundOTHER);
    }    
    @Test
    //test getter for total calories
    public void testGetTOTAL_CALORIES() {
        Nutrients seventh = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundCALS=third.getTOTAL_CALORIES();
        double expectedCALS=totalCalories;
        assertEquals("Method getTOTAL_CALORIES did not return the expected result: ", expectedCALS, foundCALS);
    }  
}
