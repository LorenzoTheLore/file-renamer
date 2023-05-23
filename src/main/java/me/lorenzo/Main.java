package me.lorenzo;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("Uso corretto: filereplacer [cartella] [nome] [estensione]");
            System.out.println("I file verranno trasformati in [nome]_n.[estensione], seguendo l'ordine dei files nella cartella");
            return;
        }

        String directoryPath = args[0].replace("'", "");
        String name = args[1];
        String extension = args[2];

        File directory = new File(directoryPath);

        if(!directory.exists() || !directory.isDirectory()) {
            System.out.println("La directory che hai specificato non esiste");
            return;
        }

        List<File> fileList = new ArrayList<>(List.of(directory.listFiles()));
        fileList.sort(Comparator.comparing(File::getName));

        int counter = 0;
        for(File original : fileList) {
            rename(original, name + "_" + counter + "." + extension);
            counter++;
        }
    }

    private static void rename(File original, String newName) {
        String newFilePath = original.getParent() + File.separator + newName;
        File newFile = new File(newFilePath);

        original.renameTo(newFile);
    }

}
