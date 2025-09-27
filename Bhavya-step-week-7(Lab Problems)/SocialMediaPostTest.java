// Parent class
class SocialMediaPost {
    String author;
    String content;
    String time;

    SocialMediaPost(String author, String content, String time) {
        this.author = author;
        this.content = content;
        this.time = time;
    }

    // Display method to be overridden
    void display() {
        System.out.println(author + " posted: " + content + " at " + time);
    }
}

// Instagram post
class InstagramPost extends SocialMediaPost {
    int likes;
    String hashtags;

    InstagramPost(String author, String content, String time, int likes, String hashtags) {
        super(author, content, time);
        this.likes = likes;
        this.hashtags = hashtags;
    }

    @Override
    void display() {
        System.out.println("Instagram Post by " + author + ": " + content 
                           + " [" + hashtags + "] Likes: " + likes + " at " + time);
    }
}

// Twitter post
class TwitterPost extends SocialMediaPost {
    int retweets;

    TwitterPost(String author, String content, String time, int retweets) {
        super(author, content, time);
        this.retweets = retweets;
    }

    @Override
    void display() {
        System.out.println("Twitter Post by " + author + ": " + content 
                           + " (Chars: " + content.length() + ") Retweets: " + retweets + " at " + time);
    }
}

// LinkedIn post
class LinkedInPost extends SocialMediaPost {
    int connections;

    LinkedInPost(String author, String content, String time, int connections) {
        super(author, content, time);
        this.connections = connections;
    }

    @Override
    void display() {
        System.out.println("LinkedIn Post by " + author + " [" + connections + " connections]: " + content + " at " + time);
    }
}

// Class to test
public class SocialMediaPostTest {
    public static void main(String[] args) {
        SocialMediaPost insta = new InstagramPost("Alice", "Check out my vacation!", "10:00 AM", 120, "#fun #travel");
        SocialMediaPost twitter = new TwitterPost("Bob", "Java is awesome!", "11:00 AM", 50);
        SocialMediaPost linkedin = new LinkedInPost("Charlie", "Networking event today.", "12:00 PM", 300);

        insta.display();
        twitter.display();
        linkedin.display();
    }
}