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
        int[] selected = new int[260];
        for(int i=0;i<=259;i++) {
            selected[i] = i;
        }
        Algorithm algorithm = new Algorithm(items,selected,Algorithm.Normal);
        algorithm.setTimeForRandom(100);
        algorithm.getOverallPlan();
    }
}
