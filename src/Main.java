import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();
        List<Food> items = new ArrayList<>();
        try {
            items = utils.readCsv();
        } catch (Exception e) {
            // TODO: handle exception
        }
        // int[] selected = {0,1,2,6,10,56,57,58,59,70,80,86,12,13,14,15,17,21,31,41,51};
        List<Integer> dislikes = new ArrayList<>();
        dislikes.add(0);
        dislikes.add(1);
        Algorithm algorithm = new Algorithm(items,dislikes,Algorithm.Normal);
        algorithm.setTimeForRandom(100);
        algorithm.setResultCount(5);
        List<FoodPlan> plans = algorithm.getOverallPlan();
        System.out.println("Algorithm has executed");
    }
}
