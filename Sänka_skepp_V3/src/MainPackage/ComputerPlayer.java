package MainPackage;

import java.util.Random;

public class ComputerPlayer implements PlayerInterface {
    private PlayerBoardInterface playerBoard;
    OpponentBoardInterface opponentBoard;

    public ComputerPlayer( PlayerBoardInterface playerBoard, OpponentBoardInterface opponentBoard) {
        this.playerBoard = playerBoard;
        this.opponentBoard = opponentBoard;
        placeBoats();
        // makeGuess();
    }

    public void placeBoats() {
        SquareButton[] squares = playerBoard.getAllSquares();
        SquareButton[] boatSquares;
        for (Boat b : PlayerInterface.boats) {
            boatSquares = getRandomBoatSquares(squares, b);
            for (SquareButton s : boatSquares) {
                playerBoard.placeBoat(s);
            }
        }
    }

    private SquareButton[] getRandomBoatSquares(SquareButton[] squares, Boat boat) {
        Random random = new Random();
        SquareButton[] boatSquares;
        do {
            int cornerIndex = random.nextInt(squares.length);
            SquareButton cornerSquare = squares[cornerIndex];
            boatSquares = createArrayOfBoatSquares(cornerSquare, squares, boat);
        } while (!boatLocationIsValid(boatSquares));
        return boatSquares;
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

    public void makeGuess() {
        Random rn = new Random();
        int index = rn.nextInt(100);
        SquareButton button = opponentBoard.getButton(index);
        System.out.println("gissade");
        button.setTitle("guessed");
        button.clicked = true;
        button.repaint();
    }
}
