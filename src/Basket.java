import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Basket {
    private int productNumber;
    private int volume;

    private String[] products;
    private int[] prices;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
    }


    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(file);) {

            for (long e : LongArrInField)
                out.print(e + " ");

        }

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
