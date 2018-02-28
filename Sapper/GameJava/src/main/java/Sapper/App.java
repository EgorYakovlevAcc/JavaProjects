package Sapper;

import Sapper.sapperJava.Game;
import Sapper.sapperJava.Ranges;
import Sapper.sapperJava.sapper;
import Sapper.sapperJava.Coord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App extends JFrame
{
    private Game game;
    private JPanel panel;
    private JLabel label;

    private final int COLS = 9;
    private final int ROWS = 9;
    private final int IMAGE_SIZE = 50;
    private final int BOMBS = 10;

    public static void main( String[] args )
    {
        new App();
    }

    private App () {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    /**
     *
     */
    private void initLabel() {
        label = new JLabel("Welcome!");
        add (label, BorderLayout.SOUTH);
    }

    /**
     *
     */
    private void initPanel() {
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord: Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getSapper(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX()/IMAGE_SIZE;
                int y = e.getY()/IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                if (e.getButton() == MouseEvent.BUTTON2) // Средняя кнопка
                    game.start();
                label.setText(getMessage());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(Ranges.getSize().x*IMAGE_SIZE, Ranges.getSize().y*IMAGE_SIZE));
        add(panel);
    }

    private String getMessage() {
        switch (game.getState()) {
            case PLAYED: return "Very good!";
            case BOMBED: return "Very lose!";
            case WINNER: return "You win!";
            default: return "Welcome!";
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sapper");
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("BOMBED"));
        pack();
        setLocationRelativeTo(null);
    }

    private void setImages() {
        for (sapper sap: sapper.values()) {
            sap.image = getImage(sap.name());
        }
    }

    private Image getImage(String name) {
        String filename = name + ".jpg";
        ImageIcon icon = new ImageIcon("img/" + filename);
        return icon.getImage();
    }
}
