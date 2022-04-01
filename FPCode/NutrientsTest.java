package FPCode;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;
public class NutrientsTest {
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

        assertEquals("Nutrients constructor did not throw an IllegalArgumentException when given an invalid input", true, correctException);        
    }

    @Test 
    public void testNormalGetsNSets(){
        Nutrients zeroth = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double fgrain = zeroth.getGrains();
        double ffruit = zeroth.getFruits();
        double fprot = zeroth.getProtein();
        double foth = zeroth.getOther();
        assertEquals("getGrains did not return the correct value.", fgrain, grainsPercent);
        assertEquals("getFruits did not return the correct value.", ffruit, fruitsPercent);
        assertEquals("getProtein did not return the correct value.", fprot, meatsPercent);
        assertEquals("getOther did not return the correct value.", foth, otherPercent);
    }

    //test getter for grains
    @Test
    public void testGetGrainCals() {
        Nutrients third = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundGRAINS=third.getGrainCals();
        double expectedGRAINS=grainsPercent*totalCalories/100.0;
        assertEquals("Method getGrainCals did not return the expected result: ", expectedGRAINS, foundGRAINS);
    }  
    //test getter for fruits
    @Test
    public void testGetFruitCals() {
        Nutrients fourth = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundFRUITS=fourth.getFruitCals();
        double expectedFRUITS=fruitsPercent*totalCalories/100.0;
        assertEquals("Method getFruitCals did not return the expected result: ", expectedFRUITS, foundFRUITS);
    }  
    //test getter for meats
    @Test
    public void testGetProteinCals() {
        Nutrients fifth = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundMEATS=fifth.getProtein();
        double expectedMEATS=meatsPercent*totalCalories/100.0;
        assertEquals("Method getProteinCals did not return the expected result: ", expectedMEATS, foundMEATS);
    }
    //test getter for other
    @Test
    public void testGetOtherCals() {
        Nutrients sixth = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundOTHER=sixth.getOtherCals();
        double expectedOTHER=otherPercent*totalCalories/100.0;
        assertEquals("Method getOtherCals did not return the expected result: ", expectedOTHER, foundOTHER);
    }    
    @Test
    //test getter for total calories
    public void testGetTotalCals() {
        Nutrients seventh = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundCALS=seventh.getTotalCalories();
        double expectedCALS=totalCalories;
        assertEquals("Method getTotalCalories did not return the expected result: ", expectedCALS, foundCALS);
    }  

}
