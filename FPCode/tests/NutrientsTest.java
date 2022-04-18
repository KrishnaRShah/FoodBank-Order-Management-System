/**
 * @author Krishna Shah 30114067<a & German Fonseca 30061209
 * href="mailto:krishna.shah@ucalgary.ca">krishna.shah@ucalgary.ca</a>
 * @version 1.8 
 * @since 0.0
 */
package FPCode.tests;
import FPCode.*;

import org.junit.Test;
import static org.junit.Assert.*;

 public class NutrientsTest {

    /**
    * These are global variables that will be used in all tests
    */
    public double grainsPercent=35;
    public double fruitsPercent=30;
    public double meatsPercent=20;
    public double otherPercent=15;
    public double totalCalories=1500;

    //example value for grains which goes over percentage limit to test whether an exception will be thrown
    public double badGrainsPercent=40;

    public NutrientsTest(){}
   
    /**
    * Constructor test with good data
    */
    @Test
    public void testNutrientsConstructorGoodData() {
        Nutrients first = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        assertNotNull("Nutrients constructor did not create an object when given valid inputs", first);
    }

    /**
    * Constructor test with bad data, that should throw IllegalArgumentExeption
    */
    @Test
    public void testNutrientsConstructorInvalidData() {
        
        boolean correctException = false;
        
        try{
            @SuppressWarnings("unused")
            Nutrients second=new Nutrients(badGrainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }

        assertEquals("Nutrients constructor did not throw an IllegalArgumentException when given an invalid input", true, correctException);        
    }

    /**
    * Tests getter functions for Nutrient data
    */
    @Test 
    public void testNormalGetsNSets(){
        // return percentages
        Nutrients zeroth = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double fgrain = zeroth.getGrains();
        double ffruit = zeroth.getFruits();
        double fprot = zeroth.getProtein();
        double foth = zeroth.getOther();
        assertEquals("getGrains did not return the correct value.", fgrain, grainsPercent,0);
        assertEquals("getFruits did not return the correct value.", ffruit, fruitsPercent,0);
        assertEquals("getProtein did not return the correct value.", fprot, meatsPercent,0);
        assertEquals("getOther did not return the correct value.", foth, otherPercent,0);
    }

    /**
    * Tests getter for grainsCals
    */
    @Test
    public void testGetGrainCals() {
        Nutrients third = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundGRAINS=third.getGrainCals();
        double expectedGRAINS=grainsPercent*totalCalories/100.0;
        assertEquals("Method getGrainCals did not return the expected result: ", expectedGRAINS, foundGRAINS,0);
    }  
    /**
    * Tests getter for fruitsCals
    */
    @Test
    public void testGetFruitCals() {
        Nutrients fourth = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundFRUITS=fourth.getFruitCals();
        double expectedFRUITS=fruitsPercent*totalCalories/100.0;
        assertEquals("Method getFruitCals did not return the expected result: ", expectedFRUITS, foundFRUITS,0);
    }  
    /**
    * Tests getter for proteinCals
    */
    @Test
    public void testGetProteinCals() {
        Nutrients fifth = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundMEATS=fifth.getProteinCals();
        double expectedMEATS=meatsPercent*totalCalories/100.0;
        assertEquals("Method getProteinCals did not return the expected result: ", expectedMEATS, foundMEATS,0);
    }
    /**
    * Tests getter for otherCals
    */
    @Test
    public void testGetOtherCals() {
        Nutrients sixth = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundOTHER=sixth.getOtherCals();
        double expectedOTHER=otherPercent*totalCalories/100.0;
        assertEquals("Method getOtherCals did not return the expected result: ", expectedOTHER, foundOTHER,0);
    }    
    @Test
    /**
    * Tests getter for total cals
    */
    public void testGetTotalCals() {
        Nutrients seventh = new Nutrients(grainsPercent,fruitsPercent,meatsPercent,otherPercent,totalCalories);
        double foundCALS=seventh.getTotalCalories();
        double expectedCALS=totalCalories;
        assertEquals("Method getTotalCalories did not return the expected result: ", expectedCALS, foundCALS,0);
    }  

}
