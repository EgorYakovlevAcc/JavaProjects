package Sapper;

import Sapper.sapperJava.Game;
import Sapper.sapperJava.Ranges;
import Sapper.sapperJava.sapper;
import Sapper.sapperJava.Coord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App extends JFrame
{
    private Game game;
    private JPanel panel;
    private JLabel label;

    private int COLS = 10;
    private int ROWS = 10;
    private final int IMAGE_SIZE = 50;
    private int BOMBS = 10;

    //точка входа программы
    public static void main( String[] args )
    {
        App app = new App();
    }

    private App () {
        setLayout(null);
        initLabel();
        initFrame();
    }

    private void start() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initPanel();
    }

    //создание метки и добавление на форму
    private void initLabel() {
        label = new JLabel("Welcome!");
        add (label);
        label.setBounds(300, 10, 100, 20);
    }

    //рисует изображения на форме
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
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton(coord);
                    label.setText(getMessage());
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(coord);
                    label.setText(getMessage());
                }
                if (e.getButton() == MouseEvent.BUTTON2) { // Средняя кнопка
                    game.start();
                    label.setText("Welcome again");
                }
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(Ranges.getSize().x*IMAGE_SIZE, Ranges.getSize().y*IMAGE_SIZE));
        add(panel);
        panel.setBounds(150, 30, 450, 450);
        panel.setVisible(true);
    }

    private String getMessage() {
        switch (game.getState()) {
            case PLAYED: return "Very good!";
            case BOMBED: return "You lose!";
            case WINNER: return "You win!";
            default: return "Welcome!";
        }
    }

    //определение формы
    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sapper");
        final JLabel numberLabel;
        add(numberLabel = new JLabel("Number of bombs: " + BOMBS));
        final JLabel lenLabel;
        add(lenLabel = new JLabel("Length: " + COLS));
        final JLabel widLabel;
        add(widLabel = new JLabel("Width: " + ROWS));
        numberLabel.setBounds(10, 30, 150, 20);
        lenLabel.setBounds(10, 50, 150, 20);
        widLabel.setBounds(10, 70, 150, 20);

        final JButton buttonEnter = new JButton("Enter data");
        JLabel bombFieldLabel = new JLabel("bombs");
        JLabel sizeFieldLabel = new JLabel("size");
        add(sizeFieldLabel);
        add(bombFieldLabel);
        sizeFieldLabel.setBounds(200, 530, 50, 20);
        bombFieldLabel.setBounds(250, 530, 50, 20);
        final JTextField sizeField = new JTextField(5);
        final JTextField bombField = new JTextField(5);

        add(buttonEnter);
        buttonEnter.setBounds(10, 500, 100, 30);
        add(sizeField);
        sizeField.setBounds(200, 500, 30, 30);
        sizeField.setText("10");
        add(bombField);
        bombField.setBounds(250, 500, 30, 30);
        bombField.setText("10");

        buttonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                COLS = Integer.parseInt(sizeField.getText());
                if ((COLS <= 10) && (COLS > 0)) {
                    ROWS = Integer.parseInt(sizeField.getText());
                    BOMBS = Integer.parseInt(bombField.getText());

                    lenLabel.setText("Length: " + COLS);
                    widLabel.setText("Width " + ROWS);
                    numberLabel.setText("Number of bombs " + BOMBS);
                    start();
                }
            }
        });
        setSize(650, 600);
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("BOMBED"));
      //  pack();
        setLocationRelativeTo(null);
    }

    private void setImages() {
        for (sapper sap: sapper.values()) {
            sap.image = getImage(sap.name());
        }
    }

    private Image getImage(String name) {
        String filename = name + ".jpg";
        ImageIcon icon = new ImageIcon("C:/GitHub/JavaProjects/Sapper/GameJava/img/" + filename);
        return icon.getImage();
    }
}
