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

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
            if(watering.isBefore(planted)) throw new PlantException("Zalevani musi byt az po zasazeni");
            this.watering=watering;

            if (frequencyOfWatering < 1) throw new PlantException("Frequence zalevani musi byt kladne cele cislo.");
            this.frequencyOfWatering = frequencyOfWatering;
    }
    public Plant(String name, LocalDate planted, int frequencyOfWatering) throws PlantException {
        this(name, "", planted, LocalDate.now(), frequencyOfWatering);
    }

    public Plant(String name) throws PlantException {
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

    public void setWatering(LocalDate watering) throws PlantException {
            if(watering.isBefore(planted)) throw new PlantException("Zalevani musi byt az po zasazeni");
            this.watering=watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
            if (frequencyOfWatering < 1) throw new PlantException("Frequence zalevani musi byt kladne cele cislo.");
            this.frequencyOfWatering = frequencyOfWatering;
    }

    public String getWatertingInfo()
    {
        String info = name + " " + watering.toString() + " "
                + getWatering().plusDays(frequencyOfWatering).toString();
        return info;
    }

    public static Plant parsePlant(String data, int lineNumber) throws PlantException {
        String[] parsedData = data.split("\t");
        if (parsedData.length < 5) throw new PlantException("Spatny pocet polozek na radku c:" + lineNumber + " " + data);
        if (parsedData[0].isEmpty()) throw new PlantException("Prazdne jmeno kvetiny na radku c:" + lineNumber + " " + data);
        LocalDate plantedData;
        try {
        plantedData = LocalDate.parse(parsedData[4]);
        } catch (Exception e) {
            throw new PlantException("Spatny format data zasazeni na radku c:" + lineNumber + " " + parsedData[4]);
        }
        LocalDate wateredData;
        try {
            wateredData = LocalDate.parse(parsedData[3]);
        } catch (Exception e) {
            throw new PlantException("Spatny format data posledniho zalevani na radku c:" + lineNumber + " " + parsedData[3]);
        }
        int frequencyOfWateringData;
        try {
            frequencyOfWateringData=Integer.parseInt(parsedData[2]);
        } catch (Exception e)
        {
            throw new PlantException("Spatny format frekvence zalevani na radku c:" + lineNumber + " " + parsedData[2]);
        }
        return new Plant(parsedData[0], parsedData[1], plantedData, wateredData, frequencyOfWateringData);
    }

    @Override
    public String toString() {
        return name + "\t" + notes + "\t" + frequencyOfWatering + "\t" + watering + "\t" + planted;
    }
}
