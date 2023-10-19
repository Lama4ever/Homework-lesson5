import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantList {

    List<Plant> plantList;

    public PlantList() {
        this.plantList = new ArrayList<>();
    }

    public void addPlant(Plant plant) {
        plantList.add(plant);
    }

    public void removePlant(int index) throws PlantException {
        if (index > plantList.size() - 1) throw new PlantException("Index k odstraneni neexistuje");
        plantList.remove(index);
    }

    public Plant getPlant(int index) {
        return plantList.get(index);
    }

    public void loadPlantList(String path) throws PlantException {

        try (Scanner s = new Scanner(new BufferedReader(new FileReader(path)))) {
            int lineNumber = 0;
            while (s.hasNextLine()) {
                plantList.add(Plant.parsePlant(s.nextLine(), lineNumber++));
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("Nepodarilo se najit soubor: " + path);
        } catch (PlantException e) {
            throw new PlantException(e.getLocalizedMessage());
        }

    }

    public void savePlantList(String path) throws IOException {
        try (PrintWriter outputWriter =
                     new PrintWriter(new BufferedWriter(new FileWriter(path)));) {
            for (Plant plant : plantList) {
                outputWriter.println(plant);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder reply = new StringBuilder();
        for (Plant plant : plantList) {
            reply.append(plant.toString()).append("\n");
        }
        return reply.toString();
    }
}
