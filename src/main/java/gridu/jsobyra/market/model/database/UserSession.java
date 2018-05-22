package gridu.jsobyra.market.model.database;

public class UserSession {
    private String jsessionID;

    public UserSession(){}

    public UserSession(String jsessionID) {
        this.jsessionID = jsessionID;
    }

    public void setJsessionID(String jsessionID) {
        this.jsessionID = jsessionID;
    }

    public String getJsessionID() {
        return jsessionID;
    }

    @Override
    public String toString() {
        return "{jsessionID='" + jsessionID + '}';
    }
}
