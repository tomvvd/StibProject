package g58594.atlg4.stibRide.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

public class ConfigManager {
    private final Properties prop;
    private final String url;
    private static final String FILE = "g58594/atlg4/stibRide/config.properties";
    private ConfigManager(){
        prop = new Properties();
        url = getClass().getClassLoader().getResource(FILE).getFile();
    }

    public void load() throws IOException{
        try(InputStream input = new FileInputStream(url)){
            prop.load(input);
        } catch (IOException e){
            throw new IOException("Chargement configuration impossible"+e.getMessage());
        }
    }

    public String getProperties(String name){
        return prop.getProperty(name);
    }

    public static ConfigManager getInstance(){
        return ConfigManagerHolder.INSTANCE;
    }

    private static class ConfigManagerHolder{
        private static final ConfigManager INSTANCE = new ConfigManager();
    }
}

