import java.io.IOException;
import java.io.RandomAccessFile;

public class Product {
    private String id;
    private String name;
    private String desc;
    private double cost;

    public static final int NAME_LENGTH = 35;
    public static final int DESC_LENGTH = 75;
    public static final int ID_LENGTH = 6;
    public static final int RECORD_SIZE = (NAME_LENGTH + DESC_LENGTH + ID_LENGTH) * 2 + 8;

    public Product(String id, String name, String desc, double cost) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.cost = cost;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDesc() { return desc; }
    public double getCost() { return cost; }

    public static String padString(String s, int length) {
        StringBuilder sb = new StringBuilder(s);
        if (sb.length() > length) return sb.substring(0, length);
        while (sb.length() < length) sb.append(" ");
        return sb.toString();
    }

    public void writeToFile(RandomAccessFile file) throws IOException {
        file.writeChars(padString(name, NAME_LENGTH));
        file.writeChars(padString(desc, DESC_LENGTH));
        file.writeChars(padString(id, ID_LENGTH));
        file.writeDouble(cost);
    }

    public static Product readFromFile(RandomAccessFile file) throws IOException {
        String name = readString(file, NAME_LENGTH);
        String desc = readString(file, DESC_LENGTH);
        String id = readString(file, ID_LENGTH);
        double cost = file.readDouble();

        return new Product(id.trim(), name.trim(), desc.trim(), cost);
    }

    private static String readString(RandomAccessFile file, int length) throws IOException {
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = file.readChar();
        }
        return new String(chars);
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + desc + " | $" + cost;
    }
}