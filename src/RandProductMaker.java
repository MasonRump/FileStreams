import java.io.RandomAccessFile;

public class RandProductMaker {
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("products.dat", "rw");

            file.seek(file.length()); // go to end

            Product p = new Product("123456", "TestProduct", "TestDesc", 9.99);
            p.writeToFile(file);

            file.close();

            System.out.println("Product written!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}