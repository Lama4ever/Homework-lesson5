import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        PlantList list = new PlantList();
        try {
            list.loadPlantList(Settings.getPATH() + Settings.getFilenameCorrect());
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }
        try {
            list.addPlant(new Plant("Bazalka v kuchyni", "", LocalDate.parse("2021-09-04"),
                    LocalDate.parse("2021-09-04"), 3));
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }

        try {
            list.removePlant(2);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }

        try {
            list.savePlantList(Settings.getPATH() + Settings.getFilenameOut());
        } catch (IOException e) {
            System.err.println("Chyba v souboru:" + Settings.getPATH() + Settings.getFilenameOut() + " " + e.getMessage());
        }
        try {
            list.loadPlantList(Settings.getPATH() + Settings.getFilenameWrongFrequency());
        } catch (PlantException e) {
            System.err.println("Chyba v souboru:" + Settings.getPATH() + Settings.getFilenameWrongFrequency() + " " + e.getLocalizedMessage());
        }
        try {
            list.loadPlantList(Settings.getPATH() + Settings.getFilenameWrongDate());
        } catch (PlantException e) {
            System.err.println("Chyba v souboru:" + Settings.getPATH() + Settings.getFilenameWrongDate() + " " + e.getLocalizedMessage());
        }

    }

}