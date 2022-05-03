import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();
        List<Food> items = new ArrayList<>();
        try {
            items = utils.readCsv();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer[] test = {1,2};
        List<Integer> dislikes = Arrays.asList(test);
        Algorithm algorithm = new Algorithm(items,dislikes,Algorithm.Normal);
        algorithm.setTimeForRandom(100);
        algorithm.setResultCount(5);
        List<FoodPlan> plans = algorithm.getOverallPlan();
        System.out.println("Algorithm has executed");
    }
}
