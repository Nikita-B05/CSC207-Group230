package interface_adapter.game_over;

import javax.swing.JOptionPane;

public class GameOverPresenter implements GameOverOutputBoundary {
    private final ViewManager viewManager;

    public GameOverPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Override
    public void prepareSuccessView(GameOverOutputData outputData) {
        GameOverView gameOverView = new GameOverView(outputData);
        viewManager.switchToView(gameOverView);
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
