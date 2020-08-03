package budget.App;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BudgetRepository {

    ArrayList<BudgetItem> purchaseList;
    ArrayList<BudgetItem> incomeList;


    BudgetRepository(){

        purchaseList = new ArrayList<>();
        incomeList = new ArrayList<>();
    }

    BudgetRepository(List<BudgetItem> purchaseList) {

        this.purchaseList = new ArrayList<>(purchaseList);
        this.incomeList = new ArrayList<>();
    }

    BudgetRepository(List<BudgetItem> purchaseList, List<BudgetItem> incomeList){

        this.purchaseList = new ArrayList(purchaseList);
        this.incomeList = new ArrayList(incomeList);
    }
    void clear(){
        purchaseList.clear();
        incomeList.clear();
    }

    void addPurchase(BudgetItem product){
        purchaseList.add(product);
    }

    void addPurchaseList(List<BudgetItem> productsList){
        purchaseList.addAll(productsList);
    }

    ArrayList<BudgetItem> getPurchasesAsList(){
        return new ArrayList<>(this.purchaseList);
    }

    void addIncome(BudgetItem income) {
        incomeList.add(income);
    }

    void addIncomeList(List<BudgetItem> income) {
        incomeList.addAll(income);
    }

    ArrayList<BudgetItem> getIncomeAsList() {
        return new ArrayList<>(this.incomeList);
    }

    double getTotalPurchaseValue(){
        double total = purchaseList.stream()
                .mapToDouble( product -> product.getValue())
                .sum();
        return total;
    }

    double getListPurchaseValue(List<Purchase> list){
        double total = list.stream()
                .mapToDouble( product -> product.getValue())
                .sum();
        return total;
    }

    public String getTotalPurchasesAsString(){
        if(purchaseList.isEmpty()) {return "Purchase list is empty";}

        StringBuffer buffer = new StringBuffer();

        purchaseList.forEach(product -> {buffer.append(product.getDescription());
            buffer.append(String.format(" $%.2f%n", product.getValue()));
        });
        buffer.append(String.format("Total sum: $%.2f", this.getTotalPurchaseValue()));
        return buffer.toString();
    }

    public String getSortedTotalPurchasesAsString(){
        if(purchaseList.isEmpty()) {return "Purchase list is empty";}

        StringBuffer buffer = new StringBuffer();

        String sorted = purchaseList.stream()
                                    .sorted((a, b) -> (int) (100 * b.getValue()) - (int) (100 * a.getValue()))
                                    .map(item -> String.format("%s $%.2f%n", item.getDescription(), item.getValue()))
                                    .collect(Collectors.joining(""));
        buffer.append(sorted);
        buffer.append(String.format("Total sum: $%.2f", this.getTotalPurchaseValue()));
        return buffer.toString();
    }


    public String getPurchasesListByTypeAsString(PurchaseType type) {
        ArrayList<Purchase> filtered = new ArrayList(purchaseList);
        filtered.removeIf(purchase -> purchase.getPurchaseType() != type);
        if (filtered.isEmpty()) {return "Purchase list is empty";}

        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("%s\n", type.getType()));
        filtered.forEach(product -> {buffer.append(product.getDescription());
            buffer.append(String.format(" $%.2f%n", product.getValue()));
        });
        buffer.append(String.format("Total sum: $%.2f", this.getListPurchaseValue(filtered)));

        return buffer.toString();
    }

    public String getSortedPurchasesListByString (PurchaseType type) {
        ArrayList<Purchase> filtered = new ArrayList(purchaseList);
        filtered.removeIf(purchase -> purchase.getPurchaseType() != type);
        if (filtered.isEmpty()) {return "Purchase list is empty";}

        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("%s\n", type.getType()));

        String sorted = filtered.stream()
                                .sorted((a, b) -> (int) (100 * b.getValue()) - (int) (100 * a.getValue()))
                                .map(item -> String.format("%s $%.2f%n", item.getDescription(), item.getValue()))
                                .collect(Collectors.joining(""));
        buffer.append(sorted);

        buffer.append(String.format("Total sum: $%.2f", this.getListPurchaseValue(filtered)));

        return buffer.toString();
    }

    public String getSaveString(){
        StringBuffer buffer = new StringBuffer();
        String purchaseStr = purchaseList.stream()
                                         .map(bdgetItem -> (Purchase) bdgetItem)
                                         .map(purchase -> String.format("purchase;%s;%.2f;%s",
                                                                    purchase.getDescription(),
                                                                    purchase.getValue(),
                                                                    purchase.getPurchaseTypeAsString()))
                                         .collect(Collectors.joining("\n"));
        buffer.append(purchaseStr);
        buffer.append("\n");
        String incomeStr = incomeList.stream()
                                     .map(bdgetItem -> (Income) bdgetItem)
                                     .map(income -> String.format("income;%s;%.2f",
                                                                   income.getDescription(),
                                                                   income.getValue()))
                                     .collect(Collectors.joining("\n"));
        buffer.append(incomeStr);



        return buffer.toString();

    }

    public String getSortedPurchasesAggregatedByTypeAsString(){

        return IntStream.range(1, PurchaseType.size() + 1)
                        .mapToObj(i -> PurchaseType.getTypeByNumber(i))
                        .sorted((a, b) -> (int) (100 * this.getTotalByType(b)) - (int) (100 * this.getTotalByType(a)))
                        .map(type -> String.format("%s - $%.2f%n", type.getType(),this.getTotalByType(type)))
                        .collect(Collectors.joining(""));

    }

    private double getTotalByType(PurchaseType type) {
        return purchaseList.stream()
                           .map(item -> (Purchase) item)
                           .filter(purchase -> purchase.getPurchaseType() == type)
                           .mapToDouble(purchase -> purchase.getValue())
                           .sum();
    }


}
