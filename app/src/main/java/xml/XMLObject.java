package xml;

public class XMLObject {
    String IPServer;
    int Port;
    String username;
    String password;

    public XMLObject(String IPServer, int port, String username, String password) {
        this.IPServer = IPServer;
        this.Port = port;
        this.username = username;
        this.password = password;
    }

    public XMLObject(String IPServer, int port) {
        this.IPServer = IPServer;
        this.Port = port;
    }

    public XMLObject(String username, String password) {
        this.username = username;
    }


}
