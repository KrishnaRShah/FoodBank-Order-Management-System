package FPCode;

public class Nutrients {
    private final double GRAINS;
    private final double FRUITS;
    private final double PROTEIN;
    private final double OTHER;
    private final double TOTAL_CALORIES;

    // Nutrients(){
    //     this.GRAINS = 0;
    //     this.FRUITS = 0;
    //     this.protein = 0;
    //     this.OTHER = 0;
    //     this.TOTAL_CALORIES = 0;
    // }

    Nutrients(double grains, double fruits, double protein, double other, double totalCals){
        if(grains+fruits+protein+other != 100){
            throw new IllegalArgumentException("Total nutrients does not equal 100");
        }
        this.GRAINS = grains;
        this.FRUITS = fruits;
        this.PROTEIN = protein;
        this.OTHER = other;
        this.TOTAL_CALORIES = totalCals;
    }

    public double getGrains() {
        return this.GRAINS;
    }

    public double getFruits() {
        return this.FRUITS;
    }

    public double getProtein() {
        return this.PROTEIN;
    }

    public double getOther() {
        return this.OTHER;
    }

    public double getGrainCals() {
        return this.TOTAL_CALORIES*0.01*this.GRAINS;
    }

    public double getFruitCals() {
        return this.TOTAL_CALORIES*0.01*this.FRUITS;
    }

    public double getProteinCals() {
        return this.TOTAL_CALORIES*0.01*this.PROTEIN;
    }

    public double getOtherCals() {
        return this.TOTAL_CALORIES*0.01*this.OTHER;
    }

    public double getTotalCalories() {
        return this.TOTAL_CALORIES;
    }

}
