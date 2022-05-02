public class Food implements Comparable<Food>{
    private int index;
    private String category;
    private String name;
    private String servingSize;
    /**
     * The attributes of nutrition
     */
    
    private int calories,
            caloriesFromFat,
            cholesterol,
            sodium,
            carbohydrates,
            dietaryFiber,
            sugars,
            protein,
            vaDailyValue,
            vcDailyValue,
            calciumDailyValue,
            ironDailyValue;
    private double transFat, saturatedFat, totalFat;

    public Food(int index, String csvLine){
        this.index = index;
        String[] tokens = csvLine.split(",");
        this.category = tokens[0];
        this.name = tokens[1];
        this.servingSize = tokens[2];
//        System.out.println(tokens[2]+" "+ tokens[3]);
        this.calories = Integer.parseInt(tokens[3]);
        this.caloriesFromFat = Integer.parseInt(tokens[4]);
        this.totalFat = Double.parseDouble(tokens[5]);
        this.saturatedFat = Double.parseDouble(tokens[7]);
        this.transFat = Double.parseDouble(tokens[9]);
        this.cholesterol = Integer.parseInt(tokens[10]);
        this.sodium = Integer.parseInt(tokens[12]);
        this.carbohydrates = Integer.parseInt(tokens[14]);
        this.dietaryFiber = Integer.parseInt(tokens[16]);
        this.sugars = Integer.parseInt(tokens[18]);
        this.protein = Integer.parseInt(tokens[19]);
        this.vaDailyValue = Integer.parseInt(tokens[20]);
        this.vcDailyValue = Integer.parseInt(tokens[21]);
        this.calciumDailyValue = Integer.parseInt(tokens[22]);
        this.ironDailyValue = Integer.parseInt(tokens[23]);

    }

    public Food(int index, String category, String name, String servingSize, int calories, int caloriesFromFat, double totalFat, double saturatedFat, double transFat, int cholesterol, int sodium, int carbohydrates, int dietaryFiber, int sugars, int protein, int vaDailyValue, int vcDailyValue, int calciumDailyValue, int ironDailyValue) {
        this.index = index;
        this.category = category;
        this.name = name;
        this.servingSize = servingSize;
        this.calories = calories;
        this.caloriesFromFat = caloriesFromFat;
        this.totalFat = totalFat;
        this.saturatedFat = saturatedFat;
        this.transFat = transFat;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
        this.carbohydrates = carbohydrates;
        this.dietaryFiber = dietaryFiber;
        this.sugars = sugars;
        this.protein = protein;
        this.vaDailyValue = vaDailyValue;
        this.vcDailyValue = vcDailyValue;
        this.calciumDailyValue = calciumDailyValue;
        this.ironDailyValue = ironDailyValue;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public void setCaloriesFromFat(int caloriesFromFat) {
        this.caloriesFromFat = caloriesFromFat;
    }
    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }
    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
    public void setDietaryFiber(int dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }
    public void setSugars(int sugars) {
        this.sugars = sugars;
    }
    public void setProtein(int protein) {
        this.protein = protein;
    }
    public void setVaDailyValue(int vaDailyValue) {
        this.vaDailyValue = vaDailyValue;
    }
    public void setVcDailyValue(int vcDailyValue) {
        this.vcDailyValue = vcDailyValue;
    }
    public void setCalciumDailyValue(int calciumDailyValue) {
        this.calciumDailyValue = calciumDailyValue;
    }
    public void setIronDailyValue(int ironDailyValue) {
        this.ironDailyValue = ironDailyValue;
    }
    public void setTransFat(double transFat) {
        this.transFat = transFat;
    }
    public void setSaturatedFat(double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }
    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public String getName() {
        return name;
    }

    public String getServingSize() {
        return servingSize;
    }

    public int getCalories() {
        return calories;
    }

    public int getCaloriesFromFat() {
        return caloriesFromFat;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public double getSaturatedFat() {
        return saturatedFat;
    }

    public double getTransFat() {
        return transFat;
    }

    public int getCholesterol() {
        return cholesterol;
    }

    public int getSodium() {
        return sodium;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public int getDietaryFiber() {
        return dietaryFiber;
    }

    public int getSugars() {
        return sugars;
    }

    public int getProtein() {
        return protein;
    }

    public int getVaDailyValue() {
        return vaDailyValue;
    }

    public int getVcDailyValue() {
        return vcDailyValue;
    }

    public int getCalciumDailyValue() {
        return calciumDailyValue;
    }

    public int getIronDailyValue() {
        return ironDailyValue;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public int compareTo(Food o) {
        return getCalories() - o.getCalories();
    }
    
}
