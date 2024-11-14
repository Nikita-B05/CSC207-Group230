package entity;

public class Decision {
    private final String type;
    private final String question;
    private final String response;

    public Decision(String type, String question, String response) {
        this.type = type;
        this.question = question;
        this.response = response;
    }

    public String getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public String getResponse() {
        return response;
    }
}
