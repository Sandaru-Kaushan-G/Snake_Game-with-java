import javax.swing.*;

//import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Point food;
    private Random random = new Random();

    // Constants for game grid dimensions
    private final int CELL_SIZE = 20;   // Size of each cell in the grid (20x20 pixels)
    private final int WIDTH = 600;      // Width of the game area in pixels
    private final int HEIGHT = 600;     // Height of the game area in pixels

    private List<Point> snake;    // List of points representing the Snake's body
    private int snakeLength;      // Initial length of the Snake
    private String direction;     // Current direction ("UP", "DOWN", "LEFT", "RIGHT")
    private Timer timer;                // Timer to control the game loop

    public GamePanel() {
        //generate-food
        generateFood();
        // Set up game panel properties
        this.setBackground(Color.BLACK);               // Background color of the game area
        this.setFocusable(true);                       // Allows the panel to receive key events
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));  // Set game panel size
        this.setFocusable(true);
        this.addKeyListener(this);  // Add KeyListener to detect key inputs

        // Initialize the Snake at the center of the game area
        snake = new ArrayList<>();
        snakeLength = 5;  // Start with 5 cells in length
        direction = "RIGHT";  // Initial direction

        // Place the Snake in the center of the grid
        for (int i = 0; i < snakeLength; i++) {
            snake.add(new Point(WIDTH / 2 - i * CELL_SIZE, HEIGHT / 2));
        }

        // Initialize the Timer with a delay (100 ms) and this panel as the listener
        timer = new Timer(100, this);                  // Calls `actionPerformed()` every 100 ms
        timer.start();                                 // Start the timer to begin the game loop
    }




    private void generateFood() {
        int x = random.nextInt(WIDTH / CELL_SIZE) * CELL_SIZE;
        int y = random.nextInt(HEIGHT / CELL_SIZE) * CELL_SIZE;
        food = new Point(x, y);
    }

    private void checkFoodCollision() {
        if (snake.get(0).equals(food)) {
            // Grow the Snake
            snake.add(new Point(food));
            generateFood();
        }
    }

    private void checkCollisions() {
        Point head = snake.get(0);

        // Wall Collision
        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
            gameOver();
        }

        // Self Collision
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver();
            }
        }
    }

    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Game Over!", "Snake Game", JOptionPane.ERROR_MESSAGE);
    }

    // This method is called every time the timer fires (every 100 ms)
    @Override
    public void actionPerformed(ActionEvent e) {
        // Game update logic here: move Snake, check collisions, etc.

        moveSnake();  // Move the Snake based on its direction

        checkFoodCollision();

        checkCollisions();
        // For now, just repaint the panel each time the timer fires
        repaint();
    }

    private void moveSnake() {
        // Get current head position
        Point head = snake.get(0);

        // Calculate new head position based on direction
        int newX = head.x;
        int newY = head.y;

        switch (direction) {
            case "UP":
                newY -= CELL_SIZE;
                break;
            case "DOWN":
                newY += CELL_SIZE;
                break;
            case "LEFT":
                newX -= CELL_SIZE;
                break;
            case "RIGHT":
                newX += CELL_SIZE;
                break;
        }

        // Add new head to the front of the list
        Point newHead = new Point(newX, newY);
        snake.add(0, newHead);

        // Remove the last segment to maintain length
        if (snake.size() > snakeLength) {
            snake.remove(snake.size() - 1);
        }
    }

    // Draw the game grid, Snake, food, etc.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw grid for reference (optional, for easier debugging)
        g.setColor(Color.DARK_GRAY);
        for (int x = 0; x < WIDTH; x += CELL_SIZE) {
            for (int y = 0; y < HEIGHT; y += CELL_SIZE) {
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);  // Draw each cell as a small square
            }
        }

        g.setColor(Color.GREEN);
        for (Point point : snake) {
            g.fillRect(point.x, point.y, CELL_SIZE, CELL_SIZE);
        }

        // Draw Food
        g.setColor(Color.RED);
        g.fillRect(food.x, food.y, CELL_SIZE, CELL_SIZE);

        // Rendering logic for Snake, food, etc., will go here later
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                if (!direction.equals("DOWN")) direction = "UP";
                break;
            case KeyEvent.VK_DOWN:
                if (!direction.equals("UP")) direction = "DOWN";
                break;
            case KeyEvent.VK_LEFT:
                if (!direction.equals("RIGHT")) direction = "LEFT";
                break;
            case KeyEvent.VK_RIGHT:
                if (!direction.equals("LEFT")) direction = "RIGHT";
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}


