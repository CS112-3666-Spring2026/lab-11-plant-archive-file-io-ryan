public class Plant {
    public static final String DEFAULT_NAME = "Mario Mushroom";
    public static final double DEFAULT_TEMP_FAHRENHEIT = 451.0;
    public static final String DEFAULT_USES = "Makes you Super!";

    private String name;
    private double tempFahrenheit;
    private String uses;

    public Plant() {
        this(DEFAULT_NAME, DEFAULT_TEMP_FAHRENHEIT, DEFAULT_USES);
    }

    public Plant(String name, double tempFahrenheit, String uses) throws IllegalArgumentException {
        if (!setAll(name, tempFahrenheit, uses)) {
            throw new IllegalArgumentException("Invalid plant data.");
        }
    }

    public Plant(Plant original) throws IllegalArgumentException {
        if (original == null) {
            throw new IllegalArgumentException("Invalid Plant object to copy passed (null)");
        }

        setAll(original.name, original.tempFahrenheit, original.uses);
    }

    public Plant(String csvLine) throws IllegalArgumentException {
        if (csvLine == null || csvLine.length() == 0) {
            throw new IllegalArgumentException("Invalid CSV line.");
        }

        String[] parts = csvLine.split(",");

        if (parts.length != 3) {
            throw new IllegalArgumentException("CSV line must have exactly 3 values.");
        }

        String name = parts[0].trim();
        double tempFahrenheit;
        String uses = parts[2].trim();

        try {
            tempFahrenheit = Double.parseDouble(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid temperature in CSV line.");
        }

        if (!setAll(name, tempFahrenheit, uses)) {
            throw new IllegalArgumentException("Invalid plant data in CSV line.");
        }
    }

    public boolean setName(String name) {
        if (name == null || name.length() == 0) {
            return false;
        }

        this.name = name;
        return true;
    }

    public boolean setTempFahrenheit(double tempFahrenheit) {
        if (tempFahrenheit < -459.67 || tempFahrenheit > 451.0) {
            return false;
        }

        this.tempFahrenheit = tempFahrenheit;
        return true;
    }

    public boolean setUses(String uses) {
        if (uses == null || uses.length() == 0) {
            return false;
        }

        this.uses = uses;
        return true;
    }

    public boolean setAll(String name, double tempFahrenheit, String uses) {
        String nameBackup = this.name;
        String usesBackup = this.uses;
        double tempBackup = this.tempFahrenheit;

        if (!setName(name)) {
            this.name = nameBackup;
            return false;
        }

        if (!setTempFahrenheit(tempFahrenheit)) {
            this.tempFahrenheit = tempBackup;
            return false;
        }

        if (!setUses(uses)) {
            this.uses = usesBackup;
            return false;
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public double getTempFahrenheit() {
        return tempFahrenheit;
    }

    public String getUses() {
        return uses;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }

        Plant otherPlant = (Plant) other;

        return name.equals(otherPlant.name)
                && Double.compare(tempFahrenheit, otherPlant.tempFahrenheit) == 0
                && uses.equals(otherPlant.uses);
    }

    @Override
    public String toString() {
        return "name: " + name + "\n"
                + "temp: " + tempFahrenheit + "°F\n"
                + "uses: " + uses;
    }
}