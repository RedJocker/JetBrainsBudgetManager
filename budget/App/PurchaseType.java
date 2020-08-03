package budget.App;

import java.util.EnumSet;
import java.util.stream.Collectors;

public enum PurchaseType {
    FOOD(1,"Food"), CLOTHES(2,"Clothes"),
    ENTERTAINMENT(3,"Entertainment"), OTHER(4,"Other");

    private String type;
    private int menuOrder;
    static private EnumSet<PurchaseType> enumSet = EnumSet.allOf(PurchaseType.class);
    static private String menuString;

    PurchaseType(int menuOrder, String type){
        this.menuOrder = menuOrder;
        this.type = type;
    }

    public static String getMenuString(){
        if (menuString == null) {
            menuString = enumSet.stream()
                                .sorted((a, b) -> a.getOrder() - b.getOrder())
                                .map(en -> String.format("%d) %s", en.getOrder(), en.getType()))
                                .collect(Collectors.joining("\n"));
        }
        return menuString;
    }

    private int getOrder(){
        return this.menuOrder;
    }

    public String getType(){
        return this.type;
    }

    public static int size(){
        return enumSet.size();
    }

    public static PurchaseType getTypeByNumber(int i){
        PurchaseType type;
        switch(i){
            case 1:
                type = PurchaseType.FOOD;
                break;
            case 2:
                type = PurchaseType.CLOTHES;
                break;
            case 3:
                type = PurchaseType.ENTERTAINMENT;
                break;
            default:
                type = PurchaseType.OTHER;
                break;
        }
        return type;
    }

}
