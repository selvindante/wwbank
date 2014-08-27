package bank;

import bank.storage.IStorage;
import bank.storage.SqlStorage;

/**
 * Created by Selvin
 * on 27.08.2014.
 */
public class Config {
    public static final String DIR_STORAGE = "D:\\Dropbox\\Projects\\Java\\webapp4\\file_storage";

    public static final IStorage SQL_STORAGE = new SqlStorage();

    public static IStorage getStorage() {
        return SQL_STORAGE;
    }
}
