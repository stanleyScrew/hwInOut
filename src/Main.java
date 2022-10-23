import jdk.jshell.SourceCodeAnalysis;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("basket.bin");
        Basket basket = null;
        try {
            basket = Basket.loadFromBinFile(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Планировщик");
        System.out.println("Список");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < basket.getProducts().length; i++) {
            System.out.println((i + 1) + ". " + basket.getProducts()[i] + " " + basket.getPrices()[i] + " руб/шт");
        }

//        String[] products = {"Хлеб", "Яблоки", "Молоко"};
//        int[] prices = {100, 200, 300};
//        System.out.println("Список возможных товаров для покупки");

//        for (int i = 0; i < products.length; i++)
//            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");

//        Scanner scanner = new Scanner(System.in);

//        int[] quantity = new int[4];
//        int[] commPrice = new int[4];
//        int volume = 0;

        while (true) {
            System.out.println("Выберте товар и количество или введите 'end'");
            String input = scanner.nextLine();
            int productNumber = 0;
            int productCount = 0;
            if ("end".equals(input)) {
                break;
            }
            String[] purchase = input.split(" ");
            productNumber = Integer.parseInt(purchase[0]) - 1;
            productCount = Integer.parseInt(purchase[1]);

            basket.addToCart(productNumber, productCount);

//            quantity[productNumber] += volume;
//            commPrice[productNumber] += volume * prices[productNumber];
        }
        basket.saveBin(file);
        basket.printCart();
//        System.out.println("Ваша корзина");

//        for (int i = 0; i < products.length; i++)
//            System.out.println(products[i] + " " + quantity[i] + " штук " + prices[i] + " руб/шт " + (quantity[i] * prices[i]) + " руб в сумме");
//        System.out.println("Итого " + Arrays.stream(commPrice).sum() + " рублей");
    }
}

