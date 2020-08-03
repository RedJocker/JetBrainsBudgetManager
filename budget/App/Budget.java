package budget.App;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Budget {
    BudgetRepository repository = new BudgetRepository();
    double balance;

    Budget(){
        this.balance = 0.0;
    }

    void addIncome(BudgetItem income) {
        this.balance += income.getValue();
        repository.addIncome(income);
    }

    void addIncomeList(List<BudgetItem> incomeList) {
        this.balance += this.sumBudgetItemsListValues(incomeList);
        repository.addIncomeList(incomeList);
    }

    void addPurchase(BudgetItem purchase) {
        this.balance -= purchase.getValue();
        repository.addPurchase(purchase);
    }

    void addPurchaseList(List<BudgetItem> purchaseList) {
        this.balance -= this.sumBudgetItemsListValues(purchaseList);
        repository.addPurchaseList(purchaseList);

    }

    void showPurchasesList(){
        System.out.println(repository.getTotalPurchasesAsString());
    }

    void showPurchasesList(PurchaseType type) {
        System.out.println(repository.getPurchasesListByTypeAsString(type));
    }

    void showSortedPurchasesList() {
        System.out.println(repository.getSortedTotalPurchasesAsString());
    }
    void showSortedPurchasesList(PurchaseType type){
        System.out.println(repository.getSortedPurchasesListByString(type));
    }

    void showBalance(){
        System.out.printf("Balance: $%.2f%n", this.balance);
    }

    private double sumBudgetItemsListValues(List<BudgetItem> list) {
        double sum = list.stream()
                         .mapToDouble(item -> item.getValue())
                         .sum();
        return sum;
    }

    void showPurchasesAggregatedByType(){
        System.out.println(repository.getSortedPurchasesAggregatedByTypeAsString());
    }

    void save() throws IOException {
        String toSave = repository.getSaveString();
        System.out.println(toSave);
        new FileManager().save(toSave);
    }

    void load() throws FileNotFoundException {
        this.balance = 0;
        repository.clear();

        ArrayList<BudgetItem> loaded = new FileManager().load();
        ArrayList<BudgetItem> purchases = new ArrayList<>();
        ArrayList<BudgetItem> incomes = new ArrayList<>();

        loaded.forEach(item -> {
            if(item.getClass() == Purchase.class) {
                purchases.add(item);
            }else if (item.getClass() == Income.class) {
                incomes.add(item);
            }
        });

        this.addPurchaseList(purchases);
        this.addIncomeList(incomes);


    }
}
