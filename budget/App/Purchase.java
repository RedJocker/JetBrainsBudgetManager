package budget.App;

public class Purchase implements BudgetItem {
    private double value;
    private String description;
    private PurchaseType type;

    Purchase(String description, double value){
        this.description = description;
        this.value = value;
        this.type = PurchaseType.OTHER;
    }

    Purchase(String description, String value){
        this.description = description;
        this.value = Double.parseDouble(value);
        this.type = PurchaseType.OTHER;
    }

    Purchase(String description, double value, PurchaseType type){
        this.description = description;
        this.value = value;
        this.type = type;
    }
    Purchase(String description, String value, String type){
        this.description = description;
        this.value = Double.parseDouble(value);
        this.type = PurchaseType.valueOf(type);
    }

    public double getValue(){
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public PurchaseType getPurchaseType(){
        return this.type;
    }

    public String getPurchaseTypeAsString() {
        return this.type.name();
    }
}
