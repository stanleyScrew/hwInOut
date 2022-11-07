import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {
    private List<String[]> productsClientLog = new ArrayList<>(); //Список обьектов для записи

    public ClientLog() {
        productsClientLog.add(new String[]{"productNum", "amount"});//Добавляем обьектов для записи
    }

    public void log(int productNum, int amount) {
        //метод для сохранения всех операций, которые ввёл пользователь.
        // Т.е. покупатель добавил покупку, то это действие должно быть там сохранено.
        productsClientLog.add(new String[]{String.valueOf(productNum), String.valueOf(amount)});
//        productsClientLog.add(new String[]{Integer.toString(productNum), Integer.toString(amount)});
    }

    public void exportAsCSV(File txtFile) throws IOException {
        //метод для сохранения всего журнала действия в файл в формате csv
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(txtFile, true))) {
            csvWriter.writeAll(productsClientLog);
        }
    }

}