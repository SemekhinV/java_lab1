import GameRule.CrossManager;
import Figure.Rectangles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Scanner;


public class Game extends JComponent {

    static JComboBox combobox1;
    static JComboBox combobox2;

    static JRadioButton radiobutton1;
    static JRadioButton radiobutton2;
    private static CrossManager GameWatcher;

    Game(){
        GameWatcher = new CrossManager();
    }

    static class GameField extends JPanel {

        Rectangles FieldMaster;
        CrossManager watcher;
        Timer timer;

        int x;
        int y;
        int dx;
        int dy;
        int speed;
        int i;

        boolean active = false;
        boolean firstTime = true;
        boolean isPaintActive = false;
        boolean timerIsRunning;

        public GameField(CrossManager watcher) {

            this.watcher = watcher;
            Scanner scanner = new Scanner(System.in);

            i = 0;

            timer = new Timer(20, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    {
                        if ( (int) watcher.getRectList().get(0).getX() < 0 || (int) watcher.getRectList().get(0).getX() > 750) {
                            watcher.getRectList().get(0).setSpeedX(-watcher.getRectList().get(0).getSpeedX());
                        }
                        if ( (int) watcher.getRectList().get(0).getY() < 0 || (int) watcher.getRectList().get(0).getY() > 750) {
                            watcher.getRectList().get(0).setSpeedY(-watcher.getRectList().get(0).getSpeedY());
                        }
                        watcher.getRectList().get(0).setCords(watcher.getRectList().get(0).getX() +
                                watcher.getRectList().get(0).getSpeedX(), watcher.getRectList().get(0).getY() +
                                watcher.getRectList().get(0).getSpeedY());
                        repaint();
                    }
                }
            });

        }

            public void TimerStart(){
            timer.start();
            }
            public void TimerStop(){
            timer.stop();
            }

        @Override
        public void paint(Graphics g){
            try{
                watcher.CreateFigure(42);
                for(int i = 0; i < watcher.getFigureCount(); i++) {
                    g.setColor(watcher.getRectList().get(i).getColor());
                    g.drawRect(watcher.getCords(i)[0],watcher.getCords(i)[1],
                            watcher.getCords(i)[2], watcher.getCords(i)[3]);
                    g.fillRect(watcher.getCords(i)[0],watcher.getCords(i)[1],
                            watcher.getCords(i)[2], watcher.getCords(i)[3]);
                }
            }finally {
                g.dispose();
            }
        }

        private void getParameters() {
            speed = Integer.parseInt((String) Objects.requireNonNull(combobox1.getSelectedItem()));
            if (firstTime) { //
                x = getWidth() / 2;
                y = getHeight() / 2;
                firstTime = false;
            }

            if (radiobutton1.isSelected()) {
                if (dy > 0) {
                    dy = -speed;
                } else {
                    dy = speed;
                }
            } else {
                if (dy > 0) {
                    dy = speed;
                } else {
                    dy = -speed;
                }
            }
            if (radiobutton2.isSelected()) {
                if (dx > 0) {
                    dx = -speed;
                } else {
                    dx = speed;
                }
            } else {
                if (dx > 0) {
                    dx = speed;
                } else {
                    dx = -speed;
                }
            }
        }
    }



        public static void main(String[] args) {

            JFrame frame = new JFrame("Moving square");
            frame.setSize(new Dimension(800, 800));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JFrame frame1 = new JFrame("Choose");
            frame1.setSize(new Dimension(400, 100));
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setLocationRelativeTo(null);
            frame1.setLayout(new GridBagLayout());

            JMenuBar menuBar = new JMenuBar();
            JMenuBar menuBar1 = new JMenuBar();
            JMenuBar menuBar2 = new JMenuBar();

            JLabel label = new JLabel("Speed:");

            String[] str = {"1", "2", "3", "4", "5", "10"};
            String[] str1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

            combobox1 = new JComboBox(str);
            combobox2 = new JComboBox(str1);
            radiobutton1 = new JRadioButton("Up-Down");
            radiobutton2 = new JRadioButton("Left-Right");
            JButton button = new JButton("Ok");

            frame1.add(label);
            frame1.add(combobox1);
            frame1.add(combobox2);
            frame1.add(radiobutton1);
            frame1.add(radiobutton2);
            frame1.add(button);

            Game g = new Game();
            GameField GameMaster = new GameField(GameWatcher);
            GameMaster.setBackground(Color.BLACK);
            frame.add(GameMaster, BorderLayout.CENTER);


            JMenu menu = new JMenu("Menu");

            JMenuItem showpicture = new JMenuItem("Show picture");
            JMenuItem choose = new JMenuItem("Choose");
            JMenuItem animate = new JMenuItem("Animate");
            JMenuItem stop = new JMenuItem("Stop");
            JMenuItem quit = new JMenuItem("Quit");
            JMenuItem ChooseCountFigure = new JMenuItem("Choose Figure c.");

            menu.add(ChooseCountFigure);
            menu.add(showpicture);
            menu.add(choose);
            menu.add(animate);
            menu.add(stop);
            menu.addSeparator();
            menu.add(quit);


            menuBar.add(menu);

            GameMaster.setVisible(false);


            button.addActionListener(e -> {
                GameMaster.getParameters();
                GameMaster.repaint();
            });


            quit.addActionListener(e -> System.exit(1));


            choose.addActionListener(e -> {
                frame1.setJMenuBar(menuBar1);
                frame1.setVisible(true);
            });

            animate.addActionListener(e -> {
                GameMaster.TimerStart();
                frame.repaint();
            });

            stop.addActionListener(e -> {
                GameMaster.TimerStop();
                frame.repaint();
            });

            showpicture.addActionListener(e -> {
                GameMaster.setVisible(!GameMaster.isPaintActive);
                frame.repaint();
            });

            frame.setJMenuBar(menuBar);
            frame.setVisible(true);

        }
}







