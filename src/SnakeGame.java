import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private static final int GOAL = 28;
    private int score;



    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        snake = new Snake(WIDTH / 2, HEIGHT / 2 );
        //apple = new Apple(5,5);
        createNewApple();
        isGameStopped = false;
        drawScene();
        //Apple app = new Apple(7,7);
        //app.draw(this);
        turnDelay =300;
        setTurnTimer(turnDelay);
        score = 0;
        setScore(score);

    }

    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.DARKSEAGREEN, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    public void onTurn(int time) {
        snake.move(apple);
        if (!apple.isAlive) {
            score = score + 5;
            setScore(score);
            turnDelay = turnDelay - 10;
            setTurnTimer(turnDelay);
        }
        if (!apple.isAlive) {
            createNewApple();
        }
        if (snake.isAlive == false) {
            gameOver();
        }
        if (snake.getLength() > GOAL) {
            win();
        }

        drawScene();
    }

    public void onKeyPress(Key key) {
        if (key == Key.LEFT) {
            snake.setDirection(Direction.LEFT);
        }  else if (key == Key.RIGHT) {
            snake.setDirection(Direction.RIGHT);
        } else if (key == Key.UP) {
            snake.setDirection(Direction.UP);
        } else if (key == Key.DOWN) {
            snake.setDirection(Direction.DOWN);
        } else {
        }
        if (key.equals(Key.SPACE) && isGameStopped == true) {
            createGame();
        }
    }

    private void createNewApple() {
        int xrand = getRandomNumber(WIDTH);
        int yrand = getRandomNumber(HEIGHT);
        apple = new Apple(xrand, yrand);
        while (true) {
            if (snake.checkCollision(apple)) {
                xrand = getRandomNumber(WIDTH);
                yrand = getRandomNumber(HEIGHT);
                apple = new Apple(xrand, yrand);
            } else {
                break;
            }
        }
    }

    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.AZURE, "GAME OVER", Color.RED,20);
    }

    private void win(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.AZURE, "YOU WIN", Color.RED,20);
    }

}
