import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

public class Algorithm {
    public List<Food> menu;
    public List<Food> options;
    public List<Food> mainFood;
    public int limitCalories;
    public int timeForRandom = 50;
    public int resultCount;
    public final static String Protein = "protein";
    public final static String Calcium = "calcium";
    public final static String Iron = "Iron";
    public final static String VitaminA = "vitaminA";
    public final static String VitaminC = "vitaminC";
    public final static int Normal = 2300;
    public final static int Fitness = 2700;

    public Algorithm(List<Food> all, List<Integer> dislikes, int limitCalories) {
        this.menu = all;
        this.options = getOptions(dislikes);
        this.mainFood = getMainFood();
        this.limitCalories = limitCalories;
    }

    public void setTimeForRandom(int timeForRandom) {
        this.timeForRandom = timeForRandom;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<Food> getMainFood() {
        List<Food> mainFood = new ArrayList<>();
        for (Food food : this.options) {
            if (food.getCategory().equals("Beef & Pork") ||
                    food.getCategory().equals("Chicken & Fish") ||
                    food.getCategory().equals("Breakfast")) {
                mainFood.add(food);
            }
        }
        return mainFood;
    }

    public List<Food> getOptions(List<Integer> dislikes) {
        List<Food> options = new ArrayList<>();
        for (Food food : menu) {
            if (!dislikes.contains(food.getIndex())) {
                options.add(food);
            }
        }
        return options;
    }

    public int getSpecificNutrition(Food food, String index) {
        int result = 0;
        switch (index) {
            case Protein:
                result = food.getProtein();
                break;
            case Calcium:
                result = food.getCalciumDailyValue();
                break;
            case Iron:
                result = food.getIronDailyValue();
                break;
            case VitaminA:
                result = food.getVaDailyValue();
                break;
            case VitaminC:
                result = food.getVcDailyValue();
                break;
        }
        return result;
    }

    public List<Food> getNurtritionMaxPlan(String index) {
        int number = options.size();
        int[] dp = new int[limitCalories + 1];
        List<List<Integer>> list = new ArrayList<>();
        for (int k = 0; k <= limitCalories; k++) {
            list.add(new ArrayList<>());
        }
        for (int i = 1; i <= number; i++) {
            Food option = options.get(i - 1);
            for (int j = limitCalories; j >= option.getCalories(); j--) {
                int val1 = dp[j];
                int val2 = dp[j - option.getCalories()] + getSpecificNutrition(option, index);
                if (val1 < val2) {
                    dp[j] = val2;
                    List<Integer> temp = new ArrayList<>(list.get(j - option.getCalories()));
                    temp.add(option.getIndex());
                    list.set(j, temp);
                }
            }
        }
        List<Integer> temp = list.get(limitCalories);
        List<Food> res = new ArrayList<>();
        for (int select : temp) {
            res.add(menu.get(select));
        }
        return res;
    }

    public void getOverallPlan() {
        List<Food> proten_list = getNurtritionMaxPlan(Protein);
        removeRepeatedCategory(proten_list);
        List<Food> calcium_list = getNurtritionMaxPlan(Calcium);
        removeRepeatedCategory(calcium_list);
        List<Food> vitaminA_list = getNurtritionMaxPlan(VitaminA);
        removeRepeatedCategory(vitaminA_list);
        List<Food> vitaminC_list = getNurtritionMaxPlan(VitaminC);
        removeRepeatedCategory(vitaminC_list);
        List<Food> iron_list = getNurtritionMaxPlan(Iron);
        Map<Integer, Integer> freq = new HashMap<>();
        for (Food food : proten_list) {
            freq.put(food.getIndex(), freq.getOrDefault(food.getIndex(), 0) + 1);
        }
        for (Food food : calcium_list) {
            freq.put(food.getIndex(), freq.getOrDefault(food.getIndex(), 0) + 1);
        }
        for (Food food : vitaminA_list) {
            freq.put(food.getIndex(), freq.getOrDefault(food.getIndex(), 0) + 1);
        }
        for (Food food : vitaminC_list) {
            freq.put(food.getIndex(), freq.getOrDefault(food.getIndex(), 0) + 1);
        }
        for (Food food : iron_list) {
            freq.put(food.getIndex(), freq.getOrDefault(food.getIndex(), 0) + 1);
        }
        // Stream<Map.Entry<Integer, Integer>> sorted = freq.entrySet().stream()
        // .sorted(Map.Entry.comparingByValue());
        // sorted.forEach(System.out::println);
        FoodPlan test_plan = shuffleAndCombine(proten_list, calcium_list, vitaminA_list, vitaminC_list, iron_list);
        evaluatePlan(test_plan);
        List<FoodPlan> ranking = new ArrayList<>();
        for (int i = 0; i < this.timeForRandom; i++) {
            FoodPlan newPlan = shuffleAndCombine(proten_list, calcium_list, vitaminA_list, vitaminC_list, iron_list);
            ranking.add(newPlan);
        }
        Collections.sort(ranking, new Comparator<FoodPlan>() {
            @Override
            public int compare(FoodPlan o1, FoodPlan o2) {
                if (o1.getTotal_score() <= o2.getTotal_score()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        int b = 3;
    }

    public FoodPlan shuffleAndCombine(List<Food> proten_list, List<Food> calcium_list, List<Food> vitaminA_list,
            List<Food> vitaminC_list, List<Food> iron_list) {
        List<Food> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        getRandomFood(set, proten_list);
        getRandomFood(set, calcium_list);
        getRandomFood(set, vitaminA_list);
        getRandomFood(set, vitaminC_list);
        getRandomFood(set, iron_list);
        getRandomFood(set, mainFood);
        getRandomFood(set, mainFood);
        Iterator<Integer> setIterator = set.iterator();
        while (setIterator.hasNext()) {
            list.add(menu.get(setIterator.next()));
        }
        FoodPlan food_plan = new FoodPlan(list);
        evaluatePlan(food_plan);
        return food_plan;
    }

    public void removeRepeatedCategory(List<Food> list) {
        int limit_size;
        if (list.size() > 10) {
            limit_size = list.size() / 3;
        } else {
            limit_size = list.size() / 2;
        }
        Map<String, Integer> freq = new HashMap<>();
        for (Food food : list) {
            freq.put(food.getCategory(), freq.getOrDefault(food.getCategory(), 0) + 1);
        }
        List<Food> new_options = new ArrayList<>(options);
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            if (entry.getValue() >= limit_size) {
                String target_category = entry.getKey();
                for (Food removeFood : new_options) {
                    if (removeFood.getCategory().equals(target_category)) {
                        options.remove(removeFood);
                    }
                }
            }
        }
    }

    public int calculateCaloriesLevel(int total_calories,List<String> desc) {
        int gap = Math.abs(limitCalories - total_calories);
        int res;
        double deviation_val = (1.0*gap/limitCalories);
        int d = (int)(deviation_val*100);
        if (d <= 5) {
            res = 0;
        } else if (d<=10&&d>5) {
            res = -5;
        } else if (d<=15&&d>10) {
            res = -10;
        } else if (d<=20&&d>15) {
            res = -25;
        } else {
            res = -50;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Total Calories||Standard Calories For " + (this.limitCalories == 2300 ? "Normal":"Fitness")+":");
        sb.append(String.valueOf(total_calories) + "||" + String.valueOf(this.limitCalories));
        sb.append("    ");
        sb.append("deduction:" + String.valueOf(-res));
        desc.add(sb.toString());
        return res;
    }
    public int getExceedRadio(double val, double standard, int scoreForThis) {
        double gap = val - standard;
        double deviation_val = (1.0*gap/standard);
        int radio = (int)(deviation_val*100);
        return radio;
    }
    public void evaluatePlan(FoodPlan test_plan) {
        double score = 100;
        double extra_score = 0;
        List<Food> list = test_plan.getDiet();
        // key standard calories = limited_calories
        // 1.if sodium>=2300 -0.02*(total-2300) 2.total Cholesterol if<300 nothing else
        // -0.05*(total-300)
        // 3.satured fat maximun:20 (total-20)*-1 4.sugar<36 -0.5*(total-36)
        // 5.Dietary Fiber +(total)*0.75 6.category diversity c<=3 -15
        int total_sodium = 0;
        int total_cholesterol = 0;
        int total_satured_fat = 0;
        int total_sugar = 0;
        int total_dietary_fiber = 0;
        int total_calories = 0;
        Map<String, Integer> category = new HashMap<>();
        List<String> desc = new ArrayList<>();
        for (Food food : list) {
            total_sodium += food.getSodium();
            total_cholesterol += food.getCholesterol();
            total_satured_fat += food.getSaturatedFat();
            total_sugar += food.getSugars();
            total_dietary_fiber += food.getDietaryFiber();
            total_calories += food.getCalories();
            category.put(food.getCategory(), 1);
        }
        score += calculateCaloriesLevel(total_calories, desc);
        if (total_sodium >= 2300) {
            int exceed_radio = getExceedRadio(total_sodium*1.0,2300,25);
            int deduction = exceed_radio>75? 50:(20*exceed_radio/100);
            score -= deduction;
            StringBuilder sb = new StringBuilder();
            sb.append("Total Sodium||Standard sodium:");
            sb.append(String.valueOf(total_sodium) + "||" + String.valueOf(2300));
            sb.append("    ");
            sb.append("deduction:" + String.valueOf(deduction));
            desc.add(sb.toString());
        }
        if (total_cholesterol >= 300) {
            int exceed_radio = getExceedRadio(total_cholesterol*1.0,300,25);
            int deduction = exceed_radio>100? 50:(20*exceed_radio/100);
            score -= deduction;
            StringBuilder sb = new StringBuilder();
            sb.append("Total Cholesterol||Standard Cholesterol:");
            sb.append(String.valueOf(total_cholesterol) + "||" + String.valueOf(300));
            sb.append("    ");
            sb.append("deduction:" + String.valueOf(deduction));
            desc.add(sb.toString());
        }
        if (total_satured_fat >= 20) {
            int exceed_radio = getExceedRadio(total_satured_fat*1.0,20,25);
            int deduction = exceed_radio>100? 50:(20*exceed_radio/100);
            score -= deduction;
            StringBuilder sb = new StringBuilder();
            sb.append("Total Satured Fat||Standard Satured Fat:");
            sb.append(String.valueOf(total_satured_fat) + "||" + String.valueOf(20));
            sb.append("    ");
            sb.append("deduction:" + String.valueOf(deduction));
            desc.add(sb.toString());
        }
        if (total_sugar >= 36) {
            int exceed_radio = getExceedRadio(total_sugar*1.0,36,25);
            int deduction = exceed_radio>100? 50:(20*exceed_radio/100);
            score -= deduction;
            StringBuilder sb = new StringBuilder();
            sb.append("Total Sugar||Standard Sugar:");
            sb.append(String.valueOf(total_sugar) + "||" + String.valueOf(36));
            sb.append("    ");
            sb.append("deduction:" + String.valueOf(deduction));
            desc.add(sb.toString());
        }
        if (category.size() <= 4) {
            score -= 15;
            StringBuilder sb = new StringBuilder();
            sb.append("Total Category:");
            sb.append(String.valueOf(category.size()));
            sb.append("    ");
            sb.append("diversity deduction:" + "15");
            desc.add(sb.toString());
        }
        if (total_dietary_fiber >= 0) {
            extra_score += total_dietary_fiber * 0.4;
            StringBuilder sb = new StringBuilder();
            sb.append("Total Dietary Fiber:");
            sb.append(String.valueOf(total_dietary_fiber));
            sb.append("    ");
            sb.append("Extra Points:" + String.valueOf((double)total_dietary_fiber * 0.4));
            desc.add(sb.toString());
        }
        test_plan.setOriginal_score(score);
        test_plan.setExtra_score(extra_score);
        test_plan.setTotal_score(score + extra_score);
        test_plan.setCalories(total_calories);
        test_plan.setDesc(desc);
    }

    public void getRandomFood(Set<Integer> set, List<Food> list) {
        Random random = new Random();
        while (true) {
            if (list.size() == 0) {
                break;
            }
            int num = random.nextInt(list.size());
            Food randomFood = list.get(num);
            if (set.contains(randomFood.getIndex())) {
                list.remove(num);
                continue;
            } else {
                set.add(randomFood.getIndex());
                break;
            }
        }
    }
}
