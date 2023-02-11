import java.util.Scanner;

public class personalExpense {
    static double budget;
    static int[] expenseFood;
    static int[] expenseTransport;
    static int[] expenseShopping;
    static int[] expenseOthers;
    static int numberOfDays;
    static double expenseSum;
    static double foodTotal;
    static double transportTotal;
    static double shoppingTotal;
    static double othersTotal;
    static double highestExpense;
    static int dailyExpense;
    static double[] allCategoryExpense;

    public static void main(String[] args) {
        boolean again;
        String Username;
        int highestExpenseDay = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nHello...");
        System.out.println("what is your name?");
        Username = keyboard.nextLine();
        System.out.println("Welcome," + " " + Username + "!");
        System.out.println("what would you like to manage" + " " + Username + "," + " " +
                "'Cash expense' or 'bank accounts' expenses?");
        String choice = keyboard.next();
        System.out.println(choice);
        System.out.println("for how many days expense  do you want to manage?" + " " + Username);
        numberOfDays = keyboard.nextInt();
        System.out.println("thank you," + " " + Username + "!");
        System.out.println("so"+ " " + Username + " " + "what is your budget for the" + " " + numberOfDays + " " + "days?");
        budget = keyboard.nextInt();
        System.out.println("thank you," + " " + Username + "!" + " " + budget + " " + "Euro for" + " " + numberOfDays + " " + "days");
        System.out.println(Username + "," + " " + "i will  manage your" + " " + choice + " " + "expenses for the " + " " + numberOfDays + " " + "days based on this 4 categories, Food," +
                "Transportation,Shopping and Others.\n");
        System.out.println("Lets get started " + " " + Username);

        getUserExpenses();
        System.out.println();
        System.out.println("please select the option you would like to get information on");
        do {
            System.out.println("""
                    [1]'Displays the sum of all expenses across the four categories per day'
                     [2] 'Display relative and absolute remaining budget'
                     [3] 'Display expenses per category'
                     [4] 'Display relative expenses per category'
                     [5] 'Display the day with the highest expense'
                     [6] 'Display the day with the lowest expenses'
                    , [7] 'Exit'""");

            int choose = keyboard.nextInt();

            while (choose == 7)
                System.exit(0);
            if (choose == 1) {
                getSumExpense();
            } else if (choose == 2) {
                getAbsoluteAndRelativeRemainderOfBudget();
            } else if (choose == 3) {
                getTotalExpenseRespectively();
            } else if (choose == 4) {
                getRelativeExpensePerCategory();
            } else if (choose == 5) {
                getHighestSpent(highestExpenseDay, highestExpense);
            } else if (choose == 6) {
                getLeastSpentCategory();
            } else
                System.out.println("Error, select options '1-7' only");
            System.out.println("do you want something else? ");
            System.out.println("select 'y' for Yes or 'n' for No");
            char select = keyboard.next().charAt(0);
            if (select == 'y' || select == 'Y')
                again = true;
            else
                again = false;
        }
        while (again);
        System.out.println("Goodbye... see you later");
    }
    // method to get input of the expense of each day from user and also get the sum of the expenses
    public static void getUserExpenses() {

        Scanner keyboard = new Scanner(System.in);
        expenseFood = new int[numberOfDays];
        for (int i = 0; i < expenseFood.length; i++) {
            System.out.println("how much did you spend on Food for  day" + (i + 1));
            expenseFood[i] = keyboard.nextInt();
            foodTotal += expenseFood[i];
        }
        expenseTransport = new int[numberOfDays];
        for (int i = 0; i < expenseTransport.length; i++) {
            System.out.println("how much did you spend on transport for day" + (i + 1));
            expenseTransport[i] = keyboard.nextInt();
            transportTotal += expenseTransport[i];
        }
        expenseShopping = new int[numberOfDays];
        for (int i = 0; i < expenseShopping.length; i++) {
            System.out.println("how much did you spend on Shopping for day" + (i + 1));
            expenseShopping[i] = keyboard.nextInt();
            shoppingTotal += expenseShopping[i];
        }
        expenseOthers = new int[numberOfDays];
        for (int i = 0; i < expenseOthers.length; i++) {
            System.out.println("how much did you spend on Others for day" + (i + 1));
            expenseOthers[i] = keyboard.nextInt();
            othersTotal += expenseOthers[i];
        }
        dailyExpense = expenseFood.length + expenseTransport.length + expenseShopping.length + expenseOthers.length;

    }
    //      GET TOTAL SUM OF ALL CATEGORY
    static void getSumExpense() {
        expenseSum = foodTotal + transportTotal + shoppingTotal + othersTotal;
        System.out.println("your total expense on the 4 categories is:" + " " + expenseSum + "Euro");
    }

    // method to get absolut and relative value of remaining budget
    public static void getAbsoluteAndRelativeRemainderOfBudget() {
        expenseSum = foodTotal + transportTotal + shoppingTotal + othersTotal;
        double remainder = budget - expenseSum;
        System.out.println("you have only" + " " + remainder + "Euro remaining");
        remainder = 100 - (expenseSum * 100 / budget);
        System.out.println("you have " + remainder + "% of your budget remaining");
    }

    // Displays the total expenses among each category
    public static void getTotalExpenseRespectively() {
        System.out.println("Total sum for Food expenses=" + " " + foodTotal + "Euro");
        System.out.println("Total sum for Transportation expenses=" + " " + transportTotal + "Euro");
        System.out.println("Total sum for Shopping expenses=" + " " + shoppingTotal + "Euro");
        System.out.println("Total sum for Others expenses=" + " " + othersTotal + "Euro");

    }
    // Displays the relative expenses among each category
    public static void getRelativeExpensePerCategory() {
        double relativeFood = expenseSum - (expenseSum - foodTotal);
        double relativeTransport = expenseSum - (expenseSum - transportTotal);
        double relativeShopping = expenseSum - (expenseSum - shoppingTotal);
        double relativeOthers = expenseSum - (expenseSum - othersTotal);
        System.out.println("Total spent on food is" + " " + relativeFood + "%");
        System.out.println("Total spent on transportation is" + " " + relativeTransport + "%");
        System.out.println("Total spent on shopping is" + " " + relativeShopping + "%");
        System.out.println("Total spent on others is" + " " + relativeOthers + "%");
    }
    // day with the highest expense
    public static void getHighestSpent(int highestExpenseDay, double highestExpense) {
        // I added all my category arrays into one array "dailyExpense"
        allCategoryExpense = new double[dailyExpense];
        int k = 0;
        while (true) {
            for (int element : expenseFood) {
                allCategoryExpense[k] = element;
                k++;
            }
            for (int element : expenseTransport) {
                allCategoryExpense[k] = element;
                k++;
            }
            for (int element : expenseShopping) {
                allCategoryExpense[k] = element;
                k++;
            }
            for (int element : expenseOthers) {
                allCategoryExpense[k] = element;
                k++;
            }
            break;
        }
        System.out.println();
        for (int j = 0; j < dailyExpense; j++) {
            if (j <= allCategoryExpense.length) {
                if (highestExpense < allCategoryExpense[j]) {
                    highestExpense = allCategoryExpense[j];
                    highestExpenseDay = j;
                }
            }
        }
        System.out.println("the day with the highest expenses was day " + (highestExpenseDay + 1));

    }
    // category with the least expenses
    public static void getLeastSpentCategory () {
        if (foodTotal < transportTotal && foodTotal < shoppingTotal && foodTotal < othersTotal) {
            System.out.println("the least spent category is food" + " spent only, " + foodTotal + " Euros");

        } else if (transportTotal < foodTotal && transportTotal < shoppingTotal && transportTotal < othersTotal) {
            System.out.println("the least spent category is transport" + " spent only, " + transportTotal + " Euros");

        } else if (shoppingTotal < foodTotal && shoppingTotal < transportTotal && shoppingTotal < othersTotal) {
            System.out.println("the least spent category is shopping" + " " + "spent only, " + shoppingTotal + " Euros");

        } else
            System.out.println("the least spent category is others" + " spent only, " + othersTotal + " Euros");
    }

}