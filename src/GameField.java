import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image apple;
    private Image dot;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private Timer timer;
    private int dots;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    public GameField (){
        setBackground(Color.black);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyPressed());
        setFocusable(true);

    }

    public void initGame() {
        dots = 3;
        for (int i = 0; i<dots; i++ ){
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 160;
        }
        timer = new Timer(250, this);
        timer.start();
        createApple();
    }

    public void createApple(){
        appleX = new Random().nextInt(20) * DOT_SIZE;
        appleY = new Random().nextInt(20) * DOT_SIZE;
    }

    public void loadImages () {
        apple = new ImageIcon("apple.png").getImage(); //568622
        dot = new ImageIcon("dot.png").getImage();  //2980
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(apple, appleX, appleY, this);
        for (int i = 0; i < dots; i++) {
            g.drawImage(dot, x[i], y[i], this);
            
        }
    }

    public void move () {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (left)
            x[0] -= DOT_SIZE;
        if (right)
            x[0] += DOT_SIZE;
        if (up)
            y[0] -= DOT_SIZE;
        if (down)
            y[0] += DOT_SIZE;
    }

    public void CheckApple() {
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            createApple();
        }
    }

    public void CheckCollisions() {
        for (int i = dots; i > 0 ; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            CheckApple();
            CheckCollisions();
            move();
        }
        repaint();

    }

    class FieldKeyPressed extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int index = e.getKeyCode();
            if (index == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }

            if (index == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }

            if (index == KeyEvent.VK_UP && !down) {
                up = true;
                left = false;
                right = false;
            }

            if (index == KeyEvent.VK_DOWN && !up) {
                down = true;
                left = false;
                right = false;
            }
        }
    }
}
