package budget.App;

public class Income implements BudgetItem {
    private double value;
    private String description;

    Income(double value){
        this.value = value;
        this.description = "";
    }

    Income(double value, String description) {
        this.value = value;
        this.description = description;
    }
    Income(String description, String value) {
        this.description = description;
        this.value = Double.parseDouble(value);
    }

    public double getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }
}
