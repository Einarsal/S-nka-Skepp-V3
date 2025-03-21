package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GridPanel extends JPanel implements PlayerBoardInterface, OpponentBoardInterface {
    public boolean hasUserBoats;
    public SquareButton currentButton;
    public SquareButton[] squares;

    public GridPanel(int width, int height, boolean isPlayerBoatsBoard) {
        if (isPlayerBoatsBoard) {
            hasUserBoats = true;
        }
        squares = new SquareButton[totalSquares];
        Dimension size = new Dimension(width, height);
       /*

        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        */
        setBackground(UserPlayer.backgroundColor);
        Color borderColor = UserPlayer.backgroundColor;
        int colorIncrease = 50;
        setBorder(BorderFactory.createLineBorder(new Color(borderColor.getRed() + colorIncrease, borderColor.getGreen() + colorIncrease, borderColor.getBlue() + colorIncrease), 1));
        setLayout(new GridLayout(rows, columns));
        addButtons();
    }

    private void addButtons() {
        for (int i = 0; i < totalSquares; i++) {
            addButton(i);
        }
    }

    private void addButton(int index) {
        SquareButton button = new SquareButton(index, rows);
        button.setIndex(index, rows);
        button.addActionListener(e -> buttonClickDetected(e, button));
        squares[index] = button;
        button.addButton(this);
    }

    private void buttonClickDetected(ActionEvent e, SquareButton button) {
        button.clicked = true;
        if (currentButton != null) {
            currentButton.clicked = false;
        }
        currentButton = button;
        button.repaint();
    }


    @Override
    public void checkGuess() {

    }

    @Override
    public SquareButton getButton(int index) {
        return squares[index];
    }

    @Override
    public SquareButton[] getAllSquares() {
        return squares;
    }

    @Override
    public SquareButton getCurrentSquare() {
        return currentButton;
    }


    @Override
    public void placeBoat(SquareButton s) {
        s.isABoat = true;

    }

    @Override
    public void setSize(int width, int height) {
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

    @Override
    public void addToBoard(JPanel board, GridBagConstraints c) {
        board.add(this, c);
    }


}
