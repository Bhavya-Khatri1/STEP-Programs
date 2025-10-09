public class AppConfig {
    private static String appName = "MegaApp";

    public static class NetworkConfig {
        private String host;
        private int port;
        public NetworkConfig(String host, int port) {
            this.host = host;
            this.port = port;
        }
        public void printConfig() {
            System.out.println("App: " + AppConfig.appName + ", Host: " + host + ", Port: " + port);
        }
    }
}