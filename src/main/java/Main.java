import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));

        ReadBlock load = new ReadBlock(doc.getElementsByTagName("load").item(0));
        ReadBlock save = new ReadBlock(doc.getElementsByTagName("save").item(0));
        ReadBlock log = new ReadBlock(doc.getElementsByTagName("log").item(0));

        Basket basket = null;
        File textFile = new File("basket.txt");
        File jsonFile = new File("basket.json");

        try {
            basket = Basket.loadFromTxtFile(textFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            basket = Basket.loadFromJson(jsonFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (basket == null) {
            String[] products = {"Хлеб", "Яблоки", "Молоко", "Рыба"};  //товар
            int[] prices = {60, 120, 50, 250};  //цена
            basket = new Basket(products, prices);  //в корзину продукты и цену
        }
        ClientLog clientLog = new ClientLog();

        if (load.enabled) {
            if (load.format.equals("txt")) { //условие загрузки формата txt
                try {
                    basket = Basket.loadFromTxtFile(new File(load.fileName));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (load.format.equals("json")) { //условие загрузки формата json
                try {
                    basket = Basket.loadFromJson(new File(load.fileName));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("Программа планировщик продуктовой корзины");
        System.out.println("Список возможных товаров для покупки");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < basket.getProducts().length; i++) {
            System.out.println((i + 1) + " " + basket.getProducts()[i] + " " + basket.getPrices()[i] + " руб/шт"); // вывод списка товаров и их цен
        }
        while (true) {                             //цикл while
            System.out.println("Выберите товар и количество или введите 'end' ");
            String inputString = scanner.nextLine(); // Ввод данных
            int productNumber = 0;
            int productCount = 0;
            if ("end".equals(inputString)) {       // выход из программы
                System.out.println("Ваша корзина:");
                break;
            }
            String[] myPrice = inputString.split(" "); // разделитель строки по пробелу
            productNumber = Integer.parseInt(myPrice[0]) - 1; // приведение ввода пользователя к значению массива
            productCount = Integer.parseInt(myPrice[1]); // ввод кол-ва товара пользователем

            clientLog.log(productNumber + 1, productCount);
            basket.addToCart(productNumber, productCount);

        }
        basket.saveTxt(textFile);
        basket.saveJson(jsonFile);
        clientLog.exportAsCSV(new File("log.csv")); //сохраняем журнал действий

        if (save.enabled) {
            if (save.format.equals("txt")) {
                try {
                    basket.saveTxt(new File(save.fileName));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else if (save.format.equals("json")) {
                try {
                    basket.saveJson(new File(save.fileName));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        if (log.enabled) {//сохраняем журнал действий
            try {
                clientLog.exportAsCSV(new File(log.fileName));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        basket.printCart();

    }
}

class ReadBlock {
    boolean enabled;
    String fileName;
    String format;

    public ReadBlock(Node node) {
        NodeList listNode = node.getChildNodes();

        for (int i = 0; i < listNode.getLength(); i++) {
            Node currentNode = listNode.item(i);
            if (Node.ELEMENT_NODE == currentNode.getNodeType()) {
                if (currentNode.getNodeName().equals("enabled")) { //условие поска файла по имени "enabled"
                    enabled = Boolean.parseBoolean(currentNode.getTextContent());
                }
                if (currentNode.getNodeName().equals("fileName")) {//условие поска файла по имени "fileName"
                    fileName = currentNode.getTextContent();
                } else if (currentNode.getNodeName().equals("format")) {//условие поска файла по имени "format"
                    format = currentNode.getTextContent();
                }
            }
        }
    }
}