package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Question {
    private int age;
    private Decision selectedDecision;
    private List<Decision> decisions;
    private String questionText;

    public Question(int age, String questionText) {
        this.age = age;
        this.questionText = questionText;
        this.decisions = new ArrayList<>();
        this.selectedDecision = null;
    }

    public static Question fromJson(Map<String, Object> json) {
        int age = (int) json.get("age");
        String questionText = (String) json.get("questionText");

        Question question = new Question(age, questionText);

        List<Map<String, Object>> decisionsJson = (List<Map<String, Object>>) json.get("decisions");
        for (Map<String, Object> decisionJson : decisionsJson) {
            question.addDecision(Decision.fromJson(decisionJson));
        }

        return question;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Decision getSelectedDecision() {
        return selectedDecision;
    }

    public void setSelectedDecision(Decision selectedDecision) {
        this.selectedDecision = selectedDecision;
    }

    public List<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<Decision> decisions) {
        this.decisions = decisions;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void addDecision(Decision decision) {
        this.decisions.add(decision);
    }

    public void removeDecision(Decision decision) {
        this.decisions.remove(decision);
    }

    @Override
    public String toString() {
        return "Question{" +
                "age=" + age +
                ", selectedDecision=" + selectedDecision +
                ", decisions=" + decisions +
                ", questionText='" + questionText + '\'' +
                '}';
    }
}
