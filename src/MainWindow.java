import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow () {
        setSize(320,320);
        setTitle("Snake");
        setLocation(500,200);
        add(new GameField());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main (String[] args){
        MainWindow mainWindow = new MainWindow();
    }
}
