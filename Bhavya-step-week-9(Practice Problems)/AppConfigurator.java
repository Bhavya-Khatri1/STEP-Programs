public class AppConfigurator {
    public static void main(String[] args) {
        AppConfig.NetworkConfig net = new AppConfig.NetworkConfig("api.example.com", 443);
        net.printConfig();
    }
}