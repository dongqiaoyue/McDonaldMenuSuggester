import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Food> items = new ArrayList<>();
        BufferedReader in = null;
        String csvLine = null;
        try {
            System.out.println("\t\tReading from file \"./src/menu.csv\":");
            in = new BufferedReader(new FileReader("./src/menu.csv"));
            int index = 0;
            csvLine = in.readLine();
            while((csvLine = in.readLine()) != null) {
//                System.out.println(csvLine);
                items.add(new Food(index++, csvLine));
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.err.println("\t\tNo such file exists!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("\t\tI/O stream err.");
            e.printStackTrace();
        }
//        System.out.println(items);
    }
}
