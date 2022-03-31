package FPCode;

import java.util.Vector;

import javax.xml.namespace.QName;

// not Hamper -- only to test the calc method stuff


class calcHamper{
    // client number [AM, AF, CO8, CU8]
    static int[] clients = new int[4];
    static Vector<Items> itemlist;
    static Vector<Client> clientlist;
    static Nutrients hamperNutrients;

    public static void main(String[] args) {
        itemlist = new Vector<Items>();
        clientlist = new Vector<Client>();
        double[] nuts = new double[5];

        clients[0] = 1; clients[1] = 1; clients[2] = 1; clients[3] = 2;
        // if i did my math right, the clients[] pattern 1112 should give
        // a hamper with 3 granola, 

        nuts[0] = 0; nuts[1] = 80; nuts[2] = 10; nuts[3] = 10; nuts[4] = 120;
        itemlist.add(new Items(0023, "Tomato Sauce, jar", nuts));
        nuts[0] = 0; nuts[1] = 100; nuts[2] = 0; nuts[3] = 0; nuts[4] = 52;
        itemlist.add(new Items(1417, "Apple, medium", nuts));
        nuts[0] = 71; nuts[1] = 0; nuts[2] = 6; nuts[3] = 23; nuts[4] = 936;
        itemlist.add(new Items(2349, "Granola Bar, box", nuts));

        calculate();

        System.out.println(hamperNutrients.getGrainCals());
        System.out.println(hamperNutrients.getFruitCals());
        System.out.println(hamperNutrients.getProteinCals());
        System.out.println(hamperNutrients.getOtherCals());
        System.out.println(hamperNutrients.getTotalCalories());
    }

    // will set hamperNutrients to be the required nutrient calories for the hamper
    public static void calculate(){
        double grains = 0;
        double fruits = 0;
        double protein = 0;
        double other = 0;
        double totalCals = 0;

        for(int i = 0; i < 4; i++){
            String type="";
            switch (i) {
                case 0:
                    type = "AM";
                case 1:
                    type = "AF";
                case 2:
                    type = "CO8";
                case 3:
                    type = "CU8";
            }
            for(int j = 0; j < clients[i]; j++){
                clientlist.add(new Client(i+1, type));
            }
        }
        
        for(int x = 0; x < clientlist.size(); x++){
            grains += clientlist.get(x).getNutrientData().getGrainCals();
            fruits += clientlist.get(x).getNutrientData().getFruitCals();
            protein += clientlist.get(x).getNutrientData().getProteinCals();
            other += clientlist.get(x).getNutrientData().getOtherCals();
            totalCals += clientlist.get(x).getNutrientData().getTotalCalories();
        }

        grains = grains * 100 / totalCals;
        fruits = fruits * 100 / totalCals;
        protein = protein * 100 / totalCals;
        other = other * 100 / totalCals;

        hamperNutrients = new Nutrients(grains, fruits, protein, other, totalCals);
    }

}