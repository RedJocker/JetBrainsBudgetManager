package budget.App;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileManager {

    public void save(String toSave) throws IOException {
        File file = new File("./purchases.txt");

        FileWriter fileWriter = new FileWriter(file);
        PrintWriter writer = new PrintWriter(fileWriter);
        writer.write(toSave);
        writer.close();
    }

    public ArrayList<BudgetItem> load() throws FileNotFoundException {
        File file = new File("./purchases.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<BudgetItem> loaded = scanner.useDelimiter("\n")
                                                .tokens()
                                                .map(str -> str.split(";"))
                                                .map(arr -> {
                                                   if(arr[0].equals("purchase")) {
                                                       return new Purchase(arr[1], arr[2], arr[3]);
                                                   }else if(arr[0].equals("income")) {
                                                       return new Income(arr[1], arr[2]);
                                                   }else {
                                                       return null;
                                                   }})
                                                 .collect(Collectors.toCollection(ArrayList::new));
        return loaded;


    }
}
