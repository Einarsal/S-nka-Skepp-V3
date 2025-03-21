package MainPackage;

import Components.ClickButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPlayer extends JPanel implements PlayerInterface {
    final static Color backgroundColor = new Color(25, 25, 25);
    protected int gridWidth, gridHeight, panelHeight, panelWidth;
    final static Border emptyBorder = BorderFactory.createEmptyBorder();
    private final PlayerBoardInterface playerBoard;
    private final OpponentBoardInterface opponentBoard;
    private int boatArrayIndex;
    public static SquareButton currentButton;


    public UserPlayer(int gridWidth, int gridHeight, PlayerBoardInterface playerBoard, OpponentBoardInterface opponentBoard) {
        panelWidth = ((gridWidth * 5) / 3);
        this.gridHeight = gridHeight;
        panelHeight = gridHeight;
        this.playerBoard = playerBoard;
        this.opponentBoard = opponentBoard;
        opponentBoard.setSize(gridWidth, gridHeight);
       playerBoard.setSize(gridWidth, gridHeight);
        //playerBoard.setSize(600, 600);
        Dimension size = new Dimension(panelWidth, gridHeight);
        setAppearance(size);
        createGrid();
    }

    private void createGrid() {
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 1;
        c.ipadx = 4;
        c.ipady = 4;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        playerBoard.addToBoard(this, c);

        c.gridy = 1;
        opponentBoard.addToBoard(this, c);

        c.gridy = 0;
        c.gridx = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1/3f;
        ButtonHolder bh = new ButtonHolder();
        add(bh, c);
    }

    public void placeBoat(){
        if(boatArrayIndex > boats.length-1){
            return;
        }
        SquareButton cornerSquare = playerBoard.getCurrentSquare();
        SquareButton[] squaresInBoat = createArrayOfBoatSquares(cornerSquare, playerBoard.getAllSquares(), boats[boatArrayIndex]);
        if(!boatLocationIsValid(squaresInBoat)){
            return;
        }
        for(SquareButton s : squaresInBoat){
            playerBoard.placeBoat(s);
        }
        boatArrayIndex++;
    }

    private SquareButton[] createArrayOfBoatSquares(SquareButton cornerSquare, SquareButton[] squares, Boat boat) {
        int rows = boat.covRows;
        int columns = boat.covCols;
        int covSquares = rows * columns;
        SquareButton[] boatSquares = new SquareButton[covSquares];
        int i = 0;
        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < columns; k++) {
                System.out.println("" + SquareButton.calculateIndex(j + cornerSquare.row, k + cornerSquare.column));
                try {
                    boatSquares[i] = squares[SquareButton.calculateIndex(j + cornerSquare.row, k + cornerSquare.column)];
                } catch (ArrayIndexOutOfBoundsException e) {
                    return null;
                }
                i++;
            }
        }
        return boatSquares;
    }

    private boolean boatLocationIsValid(SquareButton[] squares) {
        try {
            for (SquareButton s : squares) {
                if (s.isABoat) {
                    return false;
                }
            }
        } catch(NullPointerException e){
            return false;
        }
        return true;
    }

    private void setAppearance(Dimension size) {
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setBackground(backgroundColor);
        setFocusable(true);
        setBorder(emptyBorder);
    }

    private class ButtonHolder extends JPanel{

        ClickButton placeBoatButton, guessButton;
        public ButtonHolder(){
            setLayout(new GridLayout(2,1));
            placeBoatButton = createPlaceBoatButton();
            add(placeBoatButton);
        }

        private ClickButton createPlaceBoatButton(){
            ClickButton b = new ClickButton("Place Boat");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clickDetected(e);
                }
            });
            return b;
        }

        private void clickDetected(ActionEvent e){
            placeBoat();
        }

    }

}
