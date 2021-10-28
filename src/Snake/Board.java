package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener {

    public int WIDTH = 500;
    public int HEIGHT = 500;
    public int SEGMENT_SIZE = 10;
    public int TD = 100;

    public int x[] = new int[HEIGHT * WIDTH];
    public int y[] = new int[HEIGHT * WIDTH];

    public int size;
    public int foodX;
    public int foodY;

    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;

    private Timer timer;
    public boolean isAlive = true;
    private boolean isSaved = false;
    private int[] rocksY = new int[HEIGHT * WIDTH];
    private int[] rocksX = new int[HEIGHT * WIDTH];
    private int ROCKS = 20;

    public Board() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.StartGame();
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void StartGame() {

        up = false;
        down = false;
        right = false;
        left = false;

        Random random = new Random();
        int r = random.nextInt(4);
        switch (r) {
            case 0:
                up = true;
                break;
            case 1:
                down = true;
                break;
            case 2:
                right = true;
                break;
            case 3:
                left = true;
                break;

        }

        isAlive = true;
        this.size = 2;

        for (int i = 0;
             i <= this.size;
             i++) {
            this.x[i] = 50 - i * this.SEGMENT_SIZE;
            this.y[i] = 50;
        }
        /*
        *TODO : call createFood();
        *
        */

        /*
         *TODO : call createRocks();
         *
         */

        createFood();
        createRocks();



        this.timer = new Timer(TD, (ActionEvent e) -> {
            if (true) {
                /*
                TODO : call eatFood();
                TODO : call Death();
                 */

                moveSnake();
                if (!isAlive && !isSaved) {
                    /*
                    TODO : call gameOver();
                     */

                    isSaved = true;
                    setVisible(false);
                }
            }

            repaint();
        });

        this.timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        if (this.isAlive) {
            g.clearRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.YELLOW);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            g.setColor(Color.RED);
            g.fillRect(this.foodX, this.foodY, this.SEGMENT_SIZE, this.SEGMENT_SIZE);
            for (int i = 0; i < this.ROCKS; i++) {
                g.setColor(Color.GRAY);
                g.fillRect(this.rocksX[i], this.rocksY[i], this.SEGMENT_SIZE, this.SEGMENT_SIZE);
            }
            for (int i = 0; i < this.size; i++) {
                g.setColor(Color.GREEN);
                g.fillRect(this.x[i], this.y[i], this.SEGMENT_SIZE, this.SEGMENT_SIZE);
            }
            Toolkit.getDefaultToolkit().sync();
        }

    }
    /*

     * *TODO : implement msgbox()

     * *TODO : implement gameOver()

     * *TODO : implement eatFood()

     */

    public void createRocks() {
        for (int i = 0; i <= this.ROCKS; i++) {
            int r = (int) (Math.random() * (50));
            this.rocksX[i] = r * this.SEGMENT_SIZE;
            r = (int) (Math.random() * (50));
            this.rocksY[i] = r * this.SEGMENT_SIZE;
        }
    }

    public void createFood() {
        boolean SpaceNotEmpty = true;
        boolean StillGood = true;
        int r = 0;

        while (SpaceNotEmpty) {

            r = (int) (Math.random() * (29));
            this.foodX = r * this.SEGMENT_SIZE;
            r = (int) (Math.random() * (29));
            this.foodY = r * this.SEGMENT_SIZE;

            for (int i = 0; i < this.size; i++) {
                StillGood = this.x[i] == this.foodX && this.y[i] == this.foodY;
            }

            SpaceNotEmpty = StillGood;
        }

    }


    public void moveSnake() {
        for (int i = this.size; i > 0; i--) {
            this.x[i] = this.x[i - 1];
            this.y[i] = this.y[i - 1];
        }

        if (this.up) {
            this.y[0] -= this.SEGMENT_SIZE;
        }

        if (this.down) {
            this.y[0] += this.SEGMENT_SIZE;
        }

        if (this.left) {
            this.x[0] -= this.SEGMENT_SIZE;
        }

        if (this.right) {
            this.x[0] += this.SEGMENT_SIZE;
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W && !down) {
            up = true;
            right = false;
            left = false;
        }

        if (key == KeyEvent.VK_S && !up) {
            down = true;
            right = false;
            left = false;
        }

        if (key == KeyEvent.VK_D && !left) {
            right = true;
            up = false;
            down = false;
        }

        if (key == KeyEvent.VK_A && !right) {
            left = true;
            up = false;
            down = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
