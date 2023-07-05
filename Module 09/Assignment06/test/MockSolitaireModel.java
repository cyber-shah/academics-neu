import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModel;

/**
 * Mock model class for testing the controller.
 * It is used to test the controller's ability to transmit the information correctly.
 */
public class MockSolitaireModel implements MarbleSolitaireModel {

    private StringBuilder log;

    public MockSolitaireModel(StringBuilder log) {
        this.log = log;
    }

    @Override
    public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
        log.append(String.format("move (%d, %d) -> (%d, %d)\n", fromRow, fromCol, toRow, toCol));
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public int getBoardSize() {
        return 7;
    }

    @Override
    public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
        return null;
    }

    @Override
    public int getScore() {
        return 0;
    }
}
