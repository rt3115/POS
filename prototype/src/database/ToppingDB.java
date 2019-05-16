package database;

public class ToppingDB {
    private static ToppingDB ourInstance = new ToppingDB();

    public static ToppingDB getInstance() {
        return ourInstance;
    }

    private ToppingDB() {
    }
}
