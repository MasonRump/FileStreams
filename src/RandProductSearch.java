public class RandProductSearch {
    RandomAccessFile file = new RandomAccessFile("products.dat", "r");

    for (long i = 0; i < file.length(); i += Product.RECORD_SIZE) {
        file.seek(i);
        Product p = Product.readFromFile(file);

        if (p.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
            // display result
        }
    }
}
