import java.util.List;

public class FoodPlan {
    List<Food> diet;
    double original_score;
    double extra_score;
    double total_score;
    double normalized_score;
    double calories;
    List<String> desc;

    public double getNormalized_score() {
        return this.normalized_score;
    }

    public void setNormalized_score(double normalized_score) {
        this.normalized_score = normalized_score;
    }

    public List<String> getDesc() {
        return this.desc;
    }

    public void setDesc(List<String> desc) {
        this.desc = desc;
    }


    public double getCalories() {
        return this.calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public FoodPlan(List<Food> plan) {
        this.diet = plan;
    }
    public List<Food> getDiet() {
        return this.diet;
    }

    public void setDiet(List<Food> diet) {
        this.diet = diet;
    }

    public double getOriginal_score() {
        return this.original_score;
    }

    public void setOriginal_score(double original_score) {
        this.original_score = original_score;
    }

    public double getExtra_score() {
        return this.extra_score;
    }

    public void setExtra_score(double extra_score) {
        this.extra_score = extra_score;
    }

    public double getTotal_score() {
        return this.total_score;
    }

    public void setTotal_score(double total_score) {
        this.total_score = total_score;
    }
    public int compare(FoodPlan o2) {
        // TODO Auto-generated method stub
        return 0;
    }
    

}
