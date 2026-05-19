import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Plant> plants = new ArrayList<Plant>();
        FileInputStream fileByteStream = null;
        Scanner fileScanner = null;

        try {
            fileByteStream = new FileInputStream("Forage.csv");
            fileScanner = new Scanner(fileByteStream);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                if (line.length() > 0) {
                    Plant plant = new Plant(line);
                    plants.add(plant);
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Could not find Forage.csv");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Bad plant data found in file.");
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < plants.size(); i++) {
            System.out.println("Plant #" + (i + 1));
            System.out.println(plants.get(i));
            System.out.println();
        }
    }
}