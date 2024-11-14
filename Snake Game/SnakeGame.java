import javax.swing.JFrame;


public class SnakeGame {
    public static void main(String[] args) {
        // Create a new JFrame object with the title "Snake Game with AI"
        JFrame frame = new JFrame("Snake Game with AI");
        // Create a new GamePanel object
        GamePanel panel = new GamePanel();

        // Add the GamePanel object to the JFrame
        frame.add(panel);
        // Set the default close operation to exit the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of the JFrame to 600x600 pixels
        frame.setSize(600, 600);
        // Make the JFrame visible
        frame.setVisible(true);
    }
}
