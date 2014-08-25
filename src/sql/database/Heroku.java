package sql.database;

/**
 * Created by Selvin
 * on 25.08.2014.
 */
public class Heroku implements SqlSettings {
    private final static String url = "jdbc:postgresql://ec2-54-228-227-236.eu-west-1.compute.amazonaws.com:5432/dc364m3j0q3tpb?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    private final static String user = "sapqdrkeawbsrq";
    private final static String password = "uvSmRm47cHgEDEcnx21V9Q9uSn";

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
