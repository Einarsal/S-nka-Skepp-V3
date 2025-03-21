package MainPackage;

public interface OpponentBoardInterface extends BoardInterface {
    public void checkGuess();

    public SquareButton getButton(int index);
}