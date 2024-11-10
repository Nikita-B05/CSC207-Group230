package use_case.homepage;

public class HomepageOutputData {
    private final Object logo;
    private final Object character;
    private final String name;
    // If needed, add stats: network, quality of life, happiness

    public HomepageOutputData(Object logo, Object character, String name) {
        this.logo = logo;
        this.character = character;
        this.name = name;
    }

    public Object getLogo() {
        return logo;
    }

    public Object getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }
}
