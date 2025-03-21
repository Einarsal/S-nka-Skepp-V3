package MainPackage;


import Components.ButtonInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;


public class SquareButton extends Components.ClickButton implements CheckGuess, ButtonInterface {

    boolean clicked = false;
    public int index;
    public int row, column;
    boolean isABoat;

    final static Color stampColor = new Color(255, 0, 0, 200);
    final static Color boatColor = new Color(20, 20, 20, 235);

    public SquareButton(int index, int rows) {
        super("" + index + " (" + index % rows + ";" + index / rows + ")");
        setIndex(index, rows);
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(new Font("Times New Roman", Font.BOLD, 11));
        //setBorder(BorderFactory.createLineBorder(UserPlayer.backgroundColor, 2));
        addActionListener(this);

        colors = new ArrayList<>();
        addColor(
                new ModelColor(new Color(255, 0, 0), 0 / 24f),
                new ModelColor(new Color(255, 64, 0), 1 / 24f),
                new ModelColor(new Color(255, 128, 0), 2 / 24f),
                new ModelColor(new Color(255, 192, 0), 3 / 24f),
                new ModelColor(new Color(255, 255, 0), 4 / 24f),
                new ModelColor(new Color(192, 255, 0), 5 / 24f),
                new ModelColor(new Color(128, 255, 0), 6 / 24f),
                new ModelColor(new Color(64, 255, 0), 7 / 24f),
                new ModelColor(new Color(0, 255, 0), 8 / 24f),
                new ModelColor(new Color(0, 255, 64), 9 / 24f),
                new ModelColor(new Color(0, 255, 128), 10 / 24f),
                new ModelColor(new Color(0, 255, 192), 11 / 24f),
                new ModelColor(new Color(0, 255, 255), 12 / 24f),
                new ModelColor(new Color(0, 192, 255), 13 / 24f),
                new ModelColor(new Color(0, 128, 255), 14 / 24f),
                new ModelColor(new Color(0, 64, 255), 15 / 24f),
                new ModelColor(new Color(0, 0, 255), 16 / 24f),
                new ModelColor(new Color(64, 0, 255), 17 / 24f),
                new ModelColor(new Color(128, 0, 255), 18 / 24f),
                new ModelColor(new Color(192, 0, 255), 19 / 24f),
                new ModelColor(new Color(255, 0, 255), 20 / 24f),
                new ModelColor(new Color(255, 0, 192), 21 / 24f),
                new ModelColor(new Color(255, 0, 128), 22 / 24f),
                new ModelColor(new Color(255, 0, 64), 23 / 24f),
                new ModelColor(new Color(255, 0, 0), 24 / 24f)
        );
    }

    private ArrayList<ModelColor> colors;

    public void addColor(ModelColor... color) {
        Collections.addAll(colors, color);
    }

    @Override
    protected void paintComponent(Graphics g) {

        //paintGradient(g);
        paintWater(g);
        if (isABoat) {
            paintBoat(g);
        }
        super.paintComponent(g);
    }

    private void paintBoat(Graphics g) {
        g.setColor(boatColor);
        g.fillOval(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
    }

    private void paintWater(Graphics g) {
        Color waterColor = new Color(46, 155, 238);
        g.setColor(waterColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        setBackground(waterColor);
        setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));

        if (clicked) {
             paintClick(g);
        }
    }

    private void paintGradient(Graphics g) {
        if (!colors.isEmpty()) {
            int width = getWidth();
            int height = getHeight();
            Graphics2D g2 = (Graphics2D) g;
            Color color[] = new Color[colors.size()];
            float position[] = new float[colors.size()];
            for (int i = 0; i < colors.size(); i++) {
                color[i] = colors.get(i).getColor();
                position[i] = colors.get(i).getPosition();
            }
            int sx = 0;
            int sy = 0;
            int ex = width;
            int ey = height;
            LinearGradientPaint gp = new LinearGradientPaint(sx, sy, ex, ey, position, color);
            g2.setPaint(gp);
            g2.fillRoundRect(2, 2, width - 4, height - 4, 10, 10);

            if (clicked) {
                paintClick(g);
            }
        }

    }

    private void paintClick(Graphics g) {
        g.setColor(stampColor);
        g.fillOval(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
    }

    @Override
    public boolean checkGuess(ActionEvent e) {
        System.out.println("click");
        return false;
    }




    public void setIndex(int index, int rows) {
        this.index = index;
        row = index / rows;
        column = index % rows;
    }

    public void setTitle(String title) {
        setText(title + " (" + row + ";" + column + ")");
    }

    @Override
    public void addButton(JPanel p) {
        p.add(this);
    }

    @Override
    public void detectClick() {

    }

    public static int calculateIndex(int row, int column) {
        String sIndex = "" + row + column;
        return Integer.parseInt(sIndex);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
