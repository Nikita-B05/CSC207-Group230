package use_case.homepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class HomepageInputData {
    private final int character;
    private final String name;
    private final ArrayList<HashMap<String, Objects>> decisions;

    public HomepageInputData(String name, ArrayList<HashMap<String, Objects>> decisions, int character) {
        this.name = name;
        this.decisions = decisions;
        this.character = character;
    }


    public String getName() {
        return name;
    }

    public int getCharacter() {
        return character;
    }

    public ArrayList<HashMap<String, Objects>> getDecisions() {
        return decisions;
    }
}
