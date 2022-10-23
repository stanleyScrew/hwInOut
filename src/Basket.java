import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {
    private int productNumber;
    private int volume;

    private String[] products;
    private int[] prices;
    private long[] productBasket;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        productBasket = new long[products.length];
    }

    public void addToCart(int productNumber, int amount) {
        productBasket[productNumber] += amount;
    }

    public void printCart() {
        int sumProduct = 0;
        int sumCount = 0;
        for (int i = 0; i < productBasket.length; i++) {
            if (productBasket[i] != 0) {
                System.out.println(products[i] + " кол-во " + productBasket[i] + " шт " + prices[i] + " руб/шт " + (productBasket[i] * prices[i]) + " руб в сумме");
                sumProduct += productBasket[i] * prices[i];
                sumCount += productBasket[i];
            }
        }
        System.out.println("Кол-во продуктов в корзине: " + sumCount + " шт");
        System.out.println("Итого: " + sumProduct + " руб");
    }

//    public void saveTxt(File textFile) throws FileNotFoundException {
//        try (PrintWriter out = new PrintWriter(textFile)) {
//            for (String s : getProducts())
//                out.print(s + " ");
//            out.print("\n");
//            for (int i : getPrices())
//                out.print(i + " ");
//            out.print("\n");
//            for (long e : getProductBasket())
//                out.print(e + " ");
//        }
//    }

    public void saveBin(File file) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        }
    }

    public static Basket loadFromBinFile(File file) throws Exception {
        if (file.exists()) {
            Basket basket = null;
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                basket = (Basket) ois.readObject();
            }
            return basket;
        } else {
            String[] products = {"Хлеб", "Печенье", "Молоко", "Мясо"};
            int[] prices = {60, 70, 80, 100};
            Basket basket = new Basket(products, prices);
            return basket;
        }
    }

//    static Basket loadFromTxtFile(File textFile) throws Exception {
//        if (textFile.exists()) {
//            try (BufferedReader in = new BufferedReader(new FileReader(textFile));) {
//
//                String[] products = in.readLine().strip().split(" ");
//
//                String[] pricesStr = in.readLine().strip().split(" ");
//                int[] prices = new int[pricesStr.length];

//                for (int i = 0; i < prices.length; i++) {
//                    prices[i] = Integer.parseInt(pricesStr[i]);
//                }

//                Basket basket = new Basket(products, prices);

//                String[] amountsStr = in.readLine().strip().split(" ");

//                for (int i = 0; i < amountsStr.length; i++) {
//                    basket.productBasket[i] = Integer.parseInt(amountsStr[i]);
//                }
//                return basket;
//            }
//        } else {
//            String[] products = {"Хлеб", "Яблоки", "Молоко", "Рыба"};
//            int[] prices = {60, 120, 50, 250};
//            Basket basket = new Basket(products, prices);
//            return basket;
//        }
//    }

    public int getProductNumber() {
        return productNumber;
    }

    public int getVolume() {
        return volume;
    }

    public String[] getProducts() {
        return products;
    }

    public int[] getPrices() {
        return prices;
    }

    public long[] getProductBasket() {
        return productBasket;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "productNumber=" + productNumber +
                ", volume=" + volume +
                ", products=" + Arrays.toString(products) +
                ", prices=" + Arrays.toString(prices) +
                '}';
    }
}
