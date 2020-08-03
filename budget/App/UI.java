package budget.App;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private Budget budget = new Budget();
    private Scanner scanner = new Scanner(System.in);
    private UIHelper helper = new UIHelper();


    public void execute() {
        this.mainMenu();
    }

    private void mainMenu(){
        String menuString = helper.getMenuString();
        int menuChoiceNum;

        while(true) {

            System.out.println(menuString);
            menuChoiceNum = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            MenuChoice menuChoice = MenuChoice.getChoiceByNumber(menuChoiceNum);

            if(menuChoice == null) {
                System.out.println("Invalid choice number\n");
                continue;
            }

            menuChoice.executeChoise(this);

        }
    }

    void addIncome(){
        System.out.println("Enter income:");
        double incomeNum = scanner.nextDouble();
        scanner.nextLine();

        Income income = new Income(incomeNum);
        budget.addIncome(income);
        System.out.println("Income was added!");
        System.out.println();
    }

    void purchaseMenu(){
        ArrayList<BudgetItem> purchases = new ArrayList<>();

        while(true) {
            System.out.println(helper.getPurchaseMenuString());
            int typeNum = scanner.nextInt();
            scanner.nextLine();

            if(typeNum == PurchaseType.size() + 1) {
                this.addPurchase(purchases);
                return;
            }

            PurchaseType type = PurchaseType.getTypeByNumber(typeNum);

            System.out.println("Enter purchase name:");
            String purchaseName = scanner.nextLine();

            System.out.println("Enter its price:");
            double price = scanner.nextDouble();
            scanner.nextLine();

            Purchase purchase = new Purchase(purchaseName, price, type);
            purchases.add(purchase);
            System.out.println("Purchase was added!");

            System.out.println();
        }
    }

    private void addPurchase(List<BudgetItem> purchases) {
        if(purchases.isEmpty()) {
            return;
        }
        budget.addPurchaseList(purchases);
    }

    void showPurchasesListMenu(){
        while(true) {
            System.out.println(helper.getShowPurchasesListMenuString());

            int menuNum = scanner.nextInt();
            scanner.nextLine();

            int purchaseTypeSize = PurchaseType.size();

            if(menuNum > purchaseTypeSize + 2) {                  //invalid input
                System.out.println("invalid number, try again");
                continue;
            } else if (menuNum == purchaseTypeSize + 1) {
                System.out.println("All");                    //Show All
                budget.showPurchasesList();
            } else if (menuNum == purchaseTypeSize + 2) {       //Exit
                System.out.println();
                return;
            } else {                                           // Show by type
                budget.showPurchasesList(PurchaseType.getTypeByNumber(menuNum));
            }

            System.out.println();
        }
    }

    void showBalance(){
        budget.showBalance();
        System.out.println();
    }

    void save(){
        try {
            budget.save();
            System.out.println("Purchases were saved!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void load(){
        try {
            budget.load();
            System.out.println("Purchases were loaded!\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void analyzeMenu(){
        int menuNum;

        while(true) {
            System.out.println(helper.getAnalyzeMenuString());
            menuNum = scanner.nextInt();
            scanner.nextLine();

            switch (menuNum){
                case 1:
                    budget.showSortedPurchasesList();
                    System.out.println();
                    continue;
                case 2:
                    budget.showPurchasesAggregatedByType();
                    System.out.println();
                    continue;
                case 3:
                    this.analyzeByTypeMenu();
                    System.out.println();
                    continue;
                case 4:
                    System.out.println();
                    return;
            }
            System.out.println("Invalid number, try again");
        }//close while(true)
    }//close analyzeMenu()

    void analyzeByTypeMenu(){
        System.out.println(helper.getAnalyzeByTypeMenuString());
        int menuNum = scanner.nextInt();

        PurchaseType type = PurchaseType.getTypeByNumber(menuNum);
        budget.showSortedPurchasesList(type);
        System.out.println();
    }
}
