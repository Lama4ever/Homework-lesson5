import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class Plant {
    String name;
    String notes;
    LocalDate planted;
    LocalDate watering;
    int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        try{
            if(watering.isBefore(planted)) throw new PlantException("Zalevani musi byt az po zasazeni");
            this.watering=watering;
        } catch (PlantException e) {
            throw new RuntimeException(e);
        }

        try {
            if (frequencyOfWatering < 1) throw new PlantException("Frequence zalevani musi byt kladne cele cislo.");
            this.frequencyOfWatering = frequencyOfWatering;
        } catch (PlantException e) {
            throw new RuntimeException(e);
        }
    }
    public Plant(String name, LocalDate planted, int frequencyOfWatering) {
        this(name, "", planted, LocalDate.now(), frequencyOfWatering);
    }

    public Plant(String name) {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) {
        try{
            if(watering.isBefore(planted)) throw new PlantException("Zalevani musi byt az po zasazeni");
            this.watering=watering;
        } catch (PlantException e) {
            throw new RuntimeException(e);
        }
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) {
        try {
            if (frequencyOfWatering < 1) throw new PlantException("Frequence zalevani musi byt kladne cele cislo.");
            this.frequencyOfWatering = frequencyOfWatering;
        } catch (PlantException e) {
            throw new RuntimeException(e);
        }
    }

    public String getWatertingInfo()
    {
        String info = name + " " + watering.toString() + " "
                + getWatering().plusDays(frequencyOfWatering).toString();
        return info;
    }

    public static Plant parsePlant(String data) throws PlantException {
        String[] parsedData = data.split("\t");
        if (parsedData[0].isEmpty()) throw new PlantException("Prazdne jmeno kvetiny.");
        LocalDate plantedData;
        try {
        plantedData = LocalDate.parse(parsedData[4]);
        } catch (Exception e) {
            throw new PlantException("Spatny format data zasazeni.");
        }
        LocalDate wateredData;
        try {
            wateredData = LocalDate.parse(parsedData[3]);
        } catch (Exception e) {
            throw new PlantException("Spatny format data posledniho zalevani.");
        }
        int frequencyOfWateringData;
        try {
            frequencyOfWateringData=Integer.parseInt(parsedData[2]);
        } catch (Exception e)
        {
            throw new PlantException("Spatny format frekvence zalevani.");
        }
        return new Plant(parsedData[0], parsedData[1], plantedData, wateredData, frequencyOfWateringData);
    }

    @Override
    public String toString() {
        return name + "\t" + notes + "\t" + frequencyOfWatering + "\t" + watering + "\t" + planted;
    }
}
