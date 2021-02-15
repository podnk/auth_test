package conf;

import java.io.*;
import java.util.Properties;

public class Conf
{
    private static Properties PROPERTIES;

    static
    {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/conf.properties"))
        {
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key)
    {
        return PROPERTIES.getProperty(key);
    }
}
