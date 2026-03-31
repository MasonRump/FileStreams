import java.io.RandomAccessFile;

public class RandProductSearch {
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("products.dat", "r");

            String searchTerm = "Test";

            for (long i = 0; i < file.length(); i += Product.RECORD_SIZE) {
                file.seek(i);

                Product p = Product.readFromFile(file);

                if (p.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                    System.out.println(p);
                }
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}