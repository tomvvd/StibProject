package mentoring;

import config.ConfigManager;

import java.io.File;
import java.io.IOException;

public class Mentoring {
    public Mentoring(){}

    public static void main(String[] args) {
        Mentoring mentoring = new Mentoring();
        mentoring.checkPath();

        try{
            ConfigManager.getInstance().load();
        }catch (IOException e){
            System.out.println("Chargement de configuration impossible"+ e.getMessage());
        }
        String author = ConfigManager.getInstance().getProperties("app.author");
        System.out.println("Auteur : "+author);
    }

    private void checkPath() {
        System.out.println("Chemin courant \t"+ new File(".").getAbsolutePath());
        System.out.println("Chemin classe \t"+ this.getClass().getResource(".").getPath());
        System.out.println("Chemin jar \t"+ new File(getClass().getClassLoader().getResource(".").getFile()));
    }
}
