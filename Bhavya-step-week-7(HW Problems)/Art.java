// Base class
class Artwork {
    String title;
    String artist;
    
    Artwork(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }
    
    void displayInfo() {
        System.out.println("Artwork: " + title + " by " + artist);
    }
}

// Subclass 1: Painting
class Painting extends Artwork {
    String brushTechnique;
    String colorPalette;
    
    Painting(String title, String artist, String brushTechnique, String colorPalette) {
        super(title, artist);
        this.brushTechnique = brushTechnique;
        this.colorPalette = colorPalette;
    }
    
    void paintingDetails() {
        System.out.println("Painting uses " + brushTechnique + " with colors: " + colorPalette);
    }
}

// Subclass 2: Sculpture
class Sculpture extends Artwork {
    String material;
    String dimensions;
    
    Sculpture(String title, String artist, String material, String dimensions) {
        super(title, artist);
        this.material = material;
        this.dimensions = dimensions;
    }
    
    void sculptureDetails() {
        System.out.println("Sculpture made of " + material + " | Dimensions: " + dimensions);
    }
}

// Subclass 3: Digital Art
class DigitalArt extends Artwork {
    String resolution;
    String fileFormat;
    
    DigitalArt(String title, String artist, String resolution, String fileFormat) {
        super(title, artist);
        this.resolution = resolution;
        this.fileFormat = fileFormat;
    }
    
    void digitalDetails() {
        System.out.println("Digital Art resolution: " + resolution + " | Format: " + fileFormat);
    }
}

// Subclass 4: Photography
class Photography extends Artwork {
    String cameraSettings;
    String editingSoftware;
    
    Photography(String title, String artist, String cameraSettings, String editingSoftware) {
        super(title, artist);
        this.cameraSettings = cameraSettings;
        this.editingSoftware = editingSoftware;
    }
    
    void photoDetails() {
        System.out.println("Photo shot with " + cameraSettings + " | Edited in: " + editingSoftware);
    }
}

// Driver class
public class Art{
    public static void main(String[] args) {
        // Store artworks in base class references
        Artwork a1 = new Painting("Sunset Bliss", "Alice", "Oil on Canvas", "Warm tones");
        Artwork a2 = new Sculpture("David 2.0", "Bob", "Marble", "6ft tall");
        Artwork a3 = new DigitalArt("Cyber Dreams", "Charlie", "4K", "PNG");
        Artwork a4 = new Photography("Nature Shot", "Diana", "ISO 100, f/1.8", "Photoshop");
        
        // Display general info
        a1.displayInfo();
        a2.displayInfo();
        a3.displayInfo();
        a4.displayInfo();
        
        System.out.println("\n--- Accessing Specific Details via Downcasting ---");
        ((Painting)a1).paintingDetails();
        ((Sculpture)a2).sculptureDetails();
        ((DigitalArt)a3).digitalDetails();
        ((Photography)a4).photoDetails();
    }
}