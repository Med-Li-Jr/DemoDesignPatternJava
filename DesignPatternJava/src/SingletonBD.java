import java.sql.*;
import java.util.UUID;

class SingletonBD
{

    private static final String StrConnexion = "jdbc:sqlserver://localhost:1433;"
            + "database=DemoDatabase;"
            + "encrypt=true;"
            + "integratedSecurity=true;"
            + "trustServerCertificate=true;"
            + "loginTimeout=30;";

    // Un Champ static de la meme classe
    private static SingletonBD myInstance;


    // Constructeur privée pour faire le Singleton Pattern
    // ce qui va empecher de pouvoir creer plusieurs instances de la classe
    private SingletonBD()
    {
    }

    // C'est cette fonction qui va permettre d'avoir une seule instance de la classe dans le programe
    public static SingletonBD getInstance()
    {
        //pour eviter l'acces à plusieurs threads d'utiliser à meme temps
        synchronized (SingletonBD.class)
        {
            if(myInstance == null)
                myInstance= new SingletonBD();
        }
        return myInstance;
    }

    //Pour l'insertion des actions dans la base de données
    public boolean InsertionTable(String CommandRequestSql)
    {
        try
        {
                ResultSet resultSet = null;
                try (Connection mConnection = DriverManager.getConnection(StrConnexion);
                     PreparedStatement prepsInsertProduct = mConnection.prepareStatement(CommandRequestSql, Statement.RETURN_GENERATED_KEYS);) {

                    prepsInsertProduct.execute();
                    // Retrieve the generated key from the insert.
                    resultSet = prepsInsertProduct.getGeneratedKeys();
                    return true;
                }
                // Handle any errors that may have occurred.
                catch (Exception e) {
                    e.printStackTrace();
                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;

    }

}