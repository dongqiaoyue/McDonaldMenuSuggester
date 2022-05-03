import java.math.BigDecimal;
import java.util.List;

public class Normalization {
    public void normalize(List<FoodPlan> plans) {
        if (plans.size() == 0 || plans == null) {
            return;
        }
        double max_score = plans.get(0).getTotal_score();
        double min_score = plans.get(plans.size() - 1).getTotal_score();
        for (FoodPlan plan : plans) {
            double normalized_score = (plan.getTotal_score() - min_score) / (max_score - min_score);
            BigDecimal b = new BigDecimal(normalized_score*100);
            normalized_score = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            plan.setNormalized_score(normalized_score);
        }
    }
}