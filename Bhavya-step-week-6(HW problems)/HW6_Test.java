class Weather {
    String description;

    public Weather(String description) {
        this.description = description;
        System.out.println("Weather constructor called: " + description);
    }

    public void showWeather() {
        System.out.println("General weather: " + description);
    }
}

class Storm extends Weather {
    int windSpeed;

    public Storm(String description, int windSpeed) {
        super(description); // call parent constructor
        this.windSpeed = windSpeed;
        System.out.println("Storm constructor called: wind speed = " + windSpeed);
    }

    @Override
    public void showWeather() {
        System.out.println("Stormy weather: " + description + " with winds " + windSpeed + " km/h");
    }
}

class Thunderstorm extends Storm {
    boolean lightning;

    public Thunderstorm(String description, int windSpeed, boolean lightning) {
        super(description, windSpeed); // call Storm constructor
        this.lightning = lightning;
        System.out.println("Thunderstorm constructor called: lightning = " + lightning);
    }

    @Override
    public void showWeather() {
        System.out.println("Thunderstorm: " + description + ", winds " + windSpeed + " km/h, lightning = " + lightning);
    }
}

class Sunshine extends Weather {
    int temperature;

    public Sunshine(String description, int temperature) {
        super(description);
        this.temperature = temperature;
        System.out.println("Sunshine constructor called: temperature = " + temperature);
    }

    @Override
    public void showWeather() {
        System.out.println("Sunny weather: " + description + ", temperature = " + temperature + "°C");
    }
}

public class HW6_Test {
    public static void main(String[] args) {
        System.out.println("\n--- Creating Thunderstorm (multilevel) ---");
        Weather w1 = new Thunderstorm("Heavy rains", 120, true);

        System.out.println("\n--- Creating Sunshine (hierarchical) ---");
        Weather w2 = new Sunshine("Clear sky", 32);

        System.out.println("\n--- Polymorphic Array ---");
        Weather[] forecasts = {
            new Storm("Moderate storm", 80),
            new Thunderstorm("Severe thunderstorm", 150, true),
            new Sunshine("Bright sunny day", 28)
        };

        for (Weather w : forecasts) {
            w.showWeather(); // polymorphism: correct method called
        }
    }
}