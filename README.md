# McDonaldMenuSuggester

***INFO 6205 Final Project Idea***

**Kun Li, Zongyao Li, Dongqiao Yue**


## What are you doing? What is the question?
Design an algorithm to help customers to build a healthy and customized McDonald's diet.
## Data?  Where is the data?
[data reference](https://www.kaggle.com/mcdonalds/nutrition-facts)
## Algorithms?  What approaches?
### Dynamic programming
There are 2 groups of customers, each of them have different preferences. (eg: normal customers and fitness customers). We should recommend different diets based on their preferences. 
The approach would be like, Calorie as the limitation, so the whole diet’s total calorie should not be more than this limitation. And each type of diet (normal and fitness) has different weights of nutrition (eg: vitamin A, C, fiber). We should recommend a diet that aligns with the nutrition and calorie requirements mentioned. 
## Evaluation? How do you know it worked?
The data sets of results will show the recommended diet we calculate, the data sets’ attributions include the total calorie, the total fat, total daily value of the nutritions and the diet’s name….
We will also sort it by calorie, nutrition, fat…
