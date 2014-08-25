package sql.database;

/**
 * Created by Selvin
 * on 25.08.2014.
 */
public class Local implements SqlSettings {
    private final static String url = "jdbc:postgresql://localhost:5432/bankClients";
    private final static String user = "postgres";
    private final static String password = "123456";

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
