import jdk.jshell.SourceCodeAnalysis;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};
        System.out.println("Список возможных товаров для покупки");

        for (int i = 0; i < products.length; i++)
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");

        Scanner scanner = new Scanner(System.in);

        int[] quantity = new int[4];
        int[] commPrice = new int[4];
        int volume = 0;

        while (true) {
            System.out.println("Выберте товар и количество или введите 'end'");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] purchase = input.split(" ");
            int productNumber = Integer.parseInt(purchase[0]) - 1;
            volume = Integer.parseInt(purchase[1]);
            quantity[productNumber] += volume;
            commPrice[productNumber] += volume * prices[productNumber];
        }
        System.out.println("Ваша корзина");

        for (int i = 0; i < products.length; i++)
            System.out.println(products[i] + " " + quantity[i] + " штук " + prices[i] + " руб/шт " + (quantity[i] * prices[i]) + " руб в сумме");
        System.out.println("Итого " + Arrays.stream(commPrice).sum() + " рублей");
    }
}

