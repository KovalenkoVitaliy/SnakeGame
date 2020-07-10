import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private List<GameObject> snakeParts = new ArrayList<GameObject>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN =  "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        GameObject gmb1 = new GameObject(x,y);
        GameObject gmb2 = new GameObject(x + 1,y);
        GameObject gmb3 = new GameObject(x + 2,y);
        snakeParts.add(gmb1);
        snakeParts.add(gmb2);
        snakeParts.add(gmb3);
    }

    public int getLength() {
        return snakeParts.size();
    }


    public void setDirection(Direction direction) {
        if ((direction == Direction.LEFT && this.direction == Direction.RIGHT) || (direction == Direction.RIGHT && this.direction == Direction.LEFT) || (direction == Direction.UP && this.direction == Direction.DOWN) || (direction == Direction.DOWN && this.direction == Direction.UP)) {

        } else if (this.direction == Direction.LEFT && snakeParts.get(0).x == snakeParts.get(1).x ) {

        } else if (this.direction == Direction.RIGHT && snakeParts.get(0).x == snakeParts.get(1).x ) {

        } else if (this.direction == Direction.UP && snakeParts.get(0).y == snakeParts.get(1).y) {

        }else if (this.direction == Direction.DOWN && snakeParts.get(0).y == snakeParts.get(1).y) {

        }else {
            this.direction = direction;
        }
    }

    public boolean checkCollision(GameObject gameObject) {
        boolean check = false;
        for (int j = 0; j < snakeParts.size(); j ++) {
            if (gameObject.x == snakeParts.get(j).x && gameObject.y == snakeParts.get(j).y) {
                check = true;
                break;
            } else {
                check = false;
            }
        }
        return check;
    }

    public void move(Apple apple) {
        GameObject gmdHead = createNewHead();
        if (checkCollision(gmdHead)) {
            isAlive = false;
        }
        if (gmdHead.x < 0 || gmdHead.x >= 15 || gmdHead.y < 0 || gmdHead.y >= 15) {
            isAlive = false;
        }
        if (isAlive) {
            snakeParts.add(0,gmdHead);
            if (gmdHead.x == apple.x && gmdHead.y == apple.y) {
                apple.isAlive = false;
            }
            if (apple.isAlive) {
                removeTail();
            } else {

            }
        }
    }

    public GameObject createNewHead() {
        GameObject gmdHead = null;
        if (direction == Direction.LEFT) {
            gmdHead = new GameObject((this.snakeParts.get(0).x - 1), this.snakeParts.get(0).y);
        } else if (direction == Direction.RIGHT) {
            gmdHead = new GameObject((this.snakeParts.get(0).x + 1), this.snakeParts.get(0).y);
        } else if (direction == Direction.UP) {
            gmdHead = new GameObject((this.snakeParts.get(0).x), this.snakeParts.get(0).y - 1);
        } else {
            gmdHead = new GameObject((this.snakeParts.get(0).x), this.snakeParts.get(0).y + 1);
        }
        return gmdHead;
    }

    public void removeTail() {

        snakeParts.remove(snakeParts.size()-1);
    }

    public void draw(Game game) {
        for (int i = 0; i <snakeParts.size(); i++) {
            if (i==0) {
                if (this.isAlive) {
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, HEAD_SIGN, Color.BLACK, 75);
                } else {
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, HEAD_SIGN, Color.RED, 75);
                }
            } else {
                if (this.isAlive) {
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.BLACK, 75);
                } else {
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.RED, 75);
                }
            }
        }

    }


}
