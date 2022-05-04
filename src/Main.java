import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Utils utils = new Utils();
        List<Food> items = new ArrayList<>();
        try {
            items = utils.readCsv();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your dislikes: (use space to separate each input)");
        String dislike_ = scanner.nextLine();
        System.out.print("Input your type: (1 for Normal, 2 for Fitness)");
        int type = scanner.nextInt();
        String[] dislike = dislike_.split(" ");
        Integer[] test = new Integer[dislike.length];
        for (int i = 0; i < dislike.length; i++) {
            test[i] = Integer.parseInt(dislike[i]);
        }
//        Integer[] test = {1,2};
        List<Integer> dislikes = Arrays.asList(test);
        Algorithm algorithm;
        if (type == 1) {
            algorithm = new Algorithm(items, dislikes, Algorithm.Normal);
        } else {
            algorithm = new Algorithm(items, dislikes, Algorithm.Fitness);
        }
        algorithm.setTimeForRandom(100);
        algorithm.setResultCount(5);
        List<FoodPlan> plans = algorithm.getOverallPlan();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("plans.json"), plans);
    }
}
