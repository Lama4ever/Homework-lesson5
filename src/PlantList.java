import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantList {

    List<Plant> plantList;

    public PlantList() {
        this.plantList = new ArrayList<>();
    }

    public void addPlant(Plant plant){
        plantList.add(plant);
    }
    public void removePlant(int index){
        plantList.remove(index);
    }

    public Plant getPlant(int index){
        return plantList.get(index);
    }

    public void loadPlantList(String path)
    {
        try (Scanner s = new Scanner(new BufferedReader(new FileReader(path)));) {
            while (s.hasNextLine()) {
                plantList.add(Plant.parsePlant(s.nextLine()));
            }
        } catch (NumberFormatException | IOException | PlantException ex ) {
            System.err.println("Chyba při čtení souboru " + path + ": "
                    + ex.getLocalizedMessage());
        }
    }

    public void savePlantList(String path)
    {
        try (PrintWriter outputWriter =
                     new PrintWriter(new BufferedWriter(new FileWriter(path)))) {
            for (Plant plant : plantList) {
                outputWriter.println(plant.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder reply = new StringBuilder();
        for(Plant plant: plantList)
        {
            reply.append(plant.toString()).append("\n");
        }
        return reply.toString();
    }
}
