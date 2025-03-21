package MainPackage;

import javax.swing.*;
import java.awt.*;

public interface BoardInterface {
    int rows = 10;
    int columns = 10;
    int totalSquares = rows * columns;
    public SquareButton getButton(int index);
    public void setSize(int width, int height);
    public void addToBoard(JPanel board, GridBagConstraints c);
    public SquareButton[] getAllSquares();
    public SquareButton getCurrentSquare();

}
