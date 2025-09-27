// Parent class
class Content {
    String title;

    Content(String title) {
        this.title = title;
    }

    void showInfo() {
        System.out.println("Now watching: " + title);
    }
}

// Movie class
class Movie extends Content {
    double rating;
    int duration; // in minutes
    boolean hasSubtitles;

    Movie(String title, double rating, int duration, boolean hasSubtitles) {
        super(title);
        this.rating = rating;
        this.duration = duration;
        this.hasSubtitles = hasSubtitles;
    }

    void showMovieDetails() {
        System.out.println(title + " - Rating: " + rating + ", Duration: " + duration 
                           + " mins, Subtitles: " + (hasSubtitles ? "Yes" : "No"));
    }
}

// TV Series class
class TVSeries extends Content {
    int seasons;
    int episodes;

    TVSeries(String title, int seasons, int episodes) {
        super(title);
        this.seasons = seasons;
        this.episodes = episodes;
    }

    void showSeriesDetails() {
        System.out.println(title + " - Seasons: " + seasons + ", Episodes: " + episodes);
    }
}

// Documentary class
class Documentary extends Content {
    String[] tags;

    Documentary(String title, String[] tags) {
        super(title);
        this.tags = tags;
    }

    void showDocumentaryDetails() {
        System.out.print(title + " - Tags: ");
        for (String tag : tags) {
            System.out.print(tag + " ");
        }
        System.out.println();
    }
}

// Class to test
public class ContentTest {
    public static void main(String[] args) {
        Content[] watchList = {
            new Movie("Inception", 8.8, 148, true),
            new TVSeries("Stranger Things", 4, 34),
            new Documentary("Planet Earth", new String[]{"Nature", "Wildlife", "Education"})
        };

        for (Content c : watchList) {
            c.showInfo();

            // Downcasting to access specific features
            if (c instanceof Movie) {
                ((Movie) c).showMovieDetails();
            } else if (c instanceof TVSeries) {
                ((TVSeries) c).showSeriesDetails();
            } else if (c instanceof Documentary) {
                ((Documentary) c).showDocumentaryDetails();
            }
        }
    }
}