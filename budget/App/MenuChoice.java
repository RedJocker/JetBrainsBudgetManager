package budget.App;

import java.util.EnumSet;
import java.util.stream.Collectors;

public enum MenuChoice {

    ADD_INCOME(1, "Add Income"){
        @Override
        void executeChoise(UI ui) {
            ui.addIncome();
        }
    },
    ADD_PURCHASE(2, "Add Purchase"){
        @Override
        void executeChoise(UI ui) {
            ui.purchaseMenu();
        }

    },
    SHOW_PURCHASES_LIST(3, "Show list of purchases"){
        @Override
        void executeChoise(UI ui) {
            ui.showPurchasesListMenu();
        }
    },
    BALANCE(4, "Balance"){
        @Override
        void executeChoise(UI ui) {
            ui.showBalance();
        }
    },
    SAVE(5, "Save"){
        @Override
        void executeChoise(UI ui) {
            ui.save();
        }
    },
    LOAD(6, "Load"){
        @Override
        void executeChoise(UI ui) {
            ui.load();
        }
    },
    ANALYZE(7, "Analyze (Sort)"){
        @Override
        void executeChoise(UI ui) {
            ui.analyzeMenu();
        }
    }, EXIT(0, "Exit"){
        @Override
        void executeChoise(UI ui) {
            System.out.println("Bye!");
            System.exit(0);
        }
    };

    private String choice;
    private int menuOrder;
    static private EnumSet<MenuChoice> enumSet = EnumSet.allOf(MenuChoice.class);
    static private String menuString = getMenuString();

    MenuChoice(int menuOrder, String choice){
        this.menuOrder = menuOrder;
        this.choice = choice;

    }

    public static String getMenuString(){
        if (menuString == null) {
            menuString = enumSet.stream()
                    .sorted((a, b) -> a.getOrdertoSort() - b.getOrdertoSort())
                    .map(en -> String.format("%d) %s", en.getOrder(), en.getChoice()))
                    .collect(Collectors.joining("\n"));
        }
        return menuString;
    }

    private int getOrdertoSort(){
        return this.menuOrder == 0 ? Integer.MAX_VALUE : this.menuOrder;
    }

    private int getOrder(){
        return this.menuOrder;
    }

    public String getChoice(){
        return this.choice;
    }

    public static int size(){
        return enumSet.size();
    }

    public static MenuChoice getChoiceByNumber(int i) {
        MenuChoice choice;
        switch (i) {
            case 1:
                choice = MenuChoice.ADD_INCOME;
                break;
            case 2:
                choice = MenuChoice.ADD_PURCHASE;
                break;
            case 3:
                choice = MenuChoice.SHOW_PURCHASES_LIST;
                break;
            case 4:
                choice = MenuChoice.BALANCE;
                break;
            case 5:
                choice = MenuChoice.SAVE;
                break;
            case 6:
                choice = MenuChoice.LOAD;
                break;
            case 7:
                choice = MenuChoice.ANALYZE;
                break;
            case 0:
                choice = MenuChoice.EXIT;
                break;
            default:
                choice = null;
        }
        return choice;
    }

    void executeChoise(UI ui){

    }

}
