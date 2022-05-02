import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

public class Algorithm {
    public List<Food> menu;
    public List<Food> options;
    public int limitCalories;
    public final static String Protein = "protein";
    public final static String Calcium = "calcium";
    public final static String Iron = "Iron";
    public final static String VitaminA = "vitaminA";
    public final static String VitaminC = "vitaminC";
    // public final static String Sodium = "sodium";
    public final static int Normal = 2300;
    public final static int Fitness = 2700;

    public Algorithm(List<Food> all, int[] input, int limitCalories) {
        this.menu = all;
        this.options = getOptions(input);
        this.limitCalories = limitCalories;
    }

    public List<Food> getOptions(int[] input) {
        List<Food> options = new ArrayList<>();
        for (int index : input) {
            options.add(menu.get(index));
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
        List<Food> calcium_list = getNurtritionMaxPlan(Calcium);
        List<Food> vitaminA_list = getNurtritionMaxPlan(VitaminA);
        List<Food> vitaminC_list = getNurtritionMaxPlan(VitaminC);
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
        //         .sorted(Map.Entry.comparingByValue());
        // sorted.forEach(System.out::println);
        Set<Integer> set = new HashSet<>();
        getRandomFood(set, proten_list);
        getRandomFood(set, calcium_list);
        getRandomFood(set, vitaminA_list);
        getRandomFood(set, vitaminC_list);
        getRandomFood(set, iron_list);

        int b = 3;
    }
    public void evaluatePlan() {
        
    }
    public void getRandomFood(Set<Integer> set, List<Food> list) {
        Random random = new Random();
        while (true) {
            if(list.size() == 0) {
                break;
            }
            int num = random.nextInt(list.size());
            Food randomFood = list.get(num);
            if (set.contains(randomFood.getIndex())) {
                list.remove(num);
                continue;
            }else{
                set.add(randomFood.getIndex());
                break;
            }
        }
    }
}
