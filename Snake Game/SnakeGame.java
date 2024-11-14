import javax.swing.JFrame;


public class SnakeGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game with AI");
        GamePanel panel = new GamePanel();

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}