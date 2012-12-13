import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: u
 * Date: 12/11/12
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Rat {
    int x = 0, y = 3;

    Rat(int x, int y, Graphics g){
        this.x = y;
        this.y = x;
        g.setColor(Color.red);
        g.drawRect(x, y, 10, 10);
        g.fillRect(x, y, 10, 10);

    }

    Rat(int x, int y) {
        this.x = 0;
        this.y = 3;
    }

    void moveUp(){
            y =y-1;
    }

    void moveDown(){
        y = y+1;
    }

    void moveLeft(){
        x = x-1;
    }

    void moveRight() {
        x =x+1;
    }

}

