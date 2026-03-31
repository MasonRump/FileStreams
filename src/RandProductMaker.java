public class RandProductMaker {
    RandomAccessFile file = new RandomAccessFile("products.dat", "rw");

    file.seek(file.length());

    Product p = new Product(name, desc, id, cost);
    p.writeToFile(file);

    recordCount++;
    countField.setText(String.valueOf(recordCount));

}
