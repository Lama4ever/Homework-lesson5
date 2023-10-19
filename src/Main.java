import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        PlantList list = new PlantList();
        list.loadPlantList(Settings.getPATH()+Settings.getFilenameCorrect());
        list.addPlant(new Plant("Bazalka v kuchyni","", LocalDate.parse("2021-09-04"),
                LocalDate.parse("2021-09-04"), 3 ));
        list.removePlant(2);
        list.savePlantList(Settings.getPATH()+Settings.getFilenameOut());

        list.loadPlantList(Settings.getPATH()+Settings.getFilenameWrongFrequency());

        list.loadPlantList(Settings.getPATH()+Settings.getFilenameWrongDate());

    }
}