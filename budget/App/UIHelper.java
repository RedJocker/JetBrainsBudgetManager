package budget.App;


public class UIHelper {
    private String menuString;
    private String purchasesListMenuStr;
    private String purchaseMenuStr;
    private String analyzeByTypeMenuStr;

    UIHelper(){
        this.menuString = this.initMenuString();
        this.purchasesListMenuStr  = this.initShowPurchasesListMenuString();
        this.purchaseMenuStr = this.initPurchaseMenuString();
        this.analyzeByTypeMenuStr = this.initAnalyzeByTypeMenuString();
    }

    private String initMenuString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("Choose your action:\n");
        buffer.append(String.format("%s%n", MenuChoice.getMenuString()));
        return buffer.toString();
    }

    private String initShowPurchasesListMenuString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("Choose the type of purchases\n");
        buffer.append(String.format("%s%n", PurchaseType.getMenuString()));
        buffer.append(String.format("%d) All%n", PurchaseType.size() + 1 ));
        buffer.append(String.format("%d) Back%n", PurchaseType.size() + 2 ));
        return buffer.toString();
    }

    private String initPurchaseMenuString(){

        StringBuffer buffer = new StringBuffer();
        buffer.append("Choose the type of purchase\n");
        buffer.append(String.format("%s%n",PurchaseType.getMenuString()));
        buffer.append(String.format("%d) Back\n", PurchaseType.size() + 1 ));

        return buffer.toString();
    }

    private String initAnalyzeByTypeMenuString(){

        StringBuffer buffer = new StringBuffer();
        buffer.append("Choose the type of purchase\n");
        buffer.append(String.format("%s%n", PurchaseType.getMenuString()));

        return buffer.toString();
    }

    public String getShowPurchasesListMenuString(){
        return this.purchasesListMenuStr;
    }

    public String getPurchaseMenuString(){
        return this.purchaseMenuStr;
    }

    public String getMenuString(){
        return this.menuString;
    }

    public String getAnalyzeMenuString(){
        return "How do you want to sort\n1) Sort all Purchases\n2) Sort by Type\n3) Sort certain type\n4) Back\n";
    }

    public String getAnalyzeByTypeMenuString(){
        return this.analyzeByTypeMenuStr;
    }


}
