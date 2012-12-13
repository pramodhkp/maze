import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: u
 * Date: 12/8/12
 * Time: 9:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class Maze extends JPanel implements Runnable, KeyListener {
    public Maze() {
        super(true);
    }

    Stack<Cell> cellStack = new Stack<Cell>();
    //ArrayList<Cell> list = new ArrayList<Cell>();
    Rat rat = new Rat(0, 0);
    int ratx, raty;
    int i;
    Thread t;
    int count = 0;



    int maze[][] = new int[42][42];
    int r, c;
    Random rand = new Random();

    public int getRandom(int n) {
        int randomX;
        //Random rand = new Random();
        while(true){
            randomX = rand.nextInt(n);
            if(randomX % 10 ==0 && randomX!=0) {
                return randomX;
            }
            else return -999;
        }

    }

    public void setStart(){
        for(int x = 0; x<41; x++){
            for(int y = 0; y<41; y++){
                maze[x][y] = 1;
            }
        }
        int x, y;
        //Random rand =  new Random();
        /*x = rand.nextInt(41);
        y = rand.nextInt(41);
        while(x%2 ==0 || y%2 ==0){
            x = rand.nextInt(41);
            y = rand.nextInt(41);
        } */
        x = 12; y = 15;
        maze[x][y] = 0;
        r = x; c = y;
        Cell current = new Cell(r,c);
        cellStack.push(current);
        //list.add(current);

        //maze[x][y] = 0;
        System.out.println(x);
        System.out.println(y);

    }

    public Integer[] getRandomDirections() {
        Integer[] directions = new Integer[4];
        for (int i = 0; i < directions.length; i++)
        {
            directions[i] = new Integer(i+1);
        }
        java.util.Collections.shuffle(java.util.Arrays.asList(directions));
        return directions;
    }


    public void mover(int x, int y, Graphics g) {


        Cell current;
        while(!(cellStack.empty())) {
            //int[] directions = { rand.nextInt(4)+1, rand.nextInt(4)+1, rand.nextInt(4)+1, rand.nextInt(4)+1 };
            Integer[] directions = getRandomDirections();
            if(checkNeighbors(x, y)){
                for (Integer direction : directions) {
                    //int b = rand.nextInt(4);
                    switch (direction) {
                        case 1: //Up
                            if ((y - 2) < 0)
                                continue;
                            //System.out.println(maze[x-1][y]);
                            if (maze[x][y - 2] != 0) {
                                System.out.println("Up");
                                maze[x][y - 1] = 0;
                                maze[x][y - 2] = 0;
                                //g.setColor(Color.black);
                                //g.fillRect((x-2)*20, y*20, 20, 20 );
                                current = new Cell(x, y);
                                cellStack.push(current);
                                //list.add(current);
                                mover(x, y - 2, g);

                            }
                            //System.out.println(maze[x-1][y]);
                            break;
                        case 2: //Down
                            if ((y + 2) >= 42)
                                continue;
                            if (maze[x][y + 2] != 0) {
                                System.out.println("Down");
                                maze[x][y + 1] = 0;
                                maze[x][y + 2] = 0;
                                //g.setColor(Color.black);
                                //g.fillRect((x+2)*20, y*20, 20, 20 );
                                current = new Cell(x, y);
                                cellStack.push(current);
                                //list.add(current);
                                mover(x, y + 2, g);
                            }
                            break;
                        case 3: //Right
                            if ((x + 2) >= 42)
                                continue;
                            if (maze[x + 2][y] != 0) {
                                System.out.println("Right");
                                maze[x + 1][y] = 0;
                                maze[x + 2][y] = 0;
                                //g.setColor(Color.black);
                                //g.fillRect((x)*20, (y+2)*20, 20, 20 );
                                current = new Cell(x, y);
                                cellStack.push(current);
                                //list.add(current);
                                mover(x + 2, y, g);
                            }
                            break;
                        case 4: //Left
                            if ((x - 2) < 0)
                                continue;
                            if (maze[x - 2][y] != 0) {
                                System.out.println("Left");
                                maze[x - 1][y] = 0;
                                maze[x - 2][y] = 0;
                                //g.setColor(Color.black);
                                //g.fillRect((x)*20, (y-2)*20, 20, 20 );
                                current = new Cell(x, y);
                                cellStack.push(current);
                                //list.add(current);
                                mover(x - 2, y, g);
                            }
                            break;

                    }
                }

            }
            else {
                current = cellStack.peek();
                //int index = rand.nextInt(list.size());
                //current = list.get(index);
                //list.remove(current);
                cellStack.pop();
                mover(current.x, current.y, g);
                break;
            }

        }


    }


    public void mazeGenerate(Graphics g){
        for(int x = 0; x<41; x++){
            for(int y = 0; y<41; y++){
                //System.out.println(maze[x][y]);
                if(maze[x][y] == 0){
                    g.setColor(Color.white);
                    g.fillRect(x*10, y*10, 10, 10);
                    g.setColor(Color.yellow);
                    g.drawString(String.valueOf(maze[x][y]), (x)*10, (y+1)*10);
                }
                else {
                    g.setColor(Color.black);
                    g.fillRect(x*10, y*10, 10, 10);
                    g.setColor(Color.yellow);
                    g.drawString(String.valueOf(maze[x][y]), (x)*10, (y+1)*10);
                }
            }
        }
    }

    public void borderFiller(Graphics g) {
        for(int x= 0; x<42; x++){
            /*if(x == 3) {
                g.setColor(Color.red);
                g.fillRect(0, x*10, 10, 10);
                continue;
            }*/
            g.setColor(Color.green);
            g.fillRect(0, x*10, 10, 10);
        }
        for(int y =0; y<42; y++) {
            g.setColor(Color.green);
            g.fillRect(y*10, 0, 10, 10);
        }
        for(int y =0; y<42; y++) {
            if( y == 40){
                g.setColor(Color.yellow);
                g.fillRect(410, y*10, 10, 10);
                continue;
            }
            g.setColor(Color.green);
            g.fillRect(410, y*10, 10, 10);
        }
        for(int y =0; y<41; y++) {
            g.setColor(Color.green);
            g.fillRect(y*10, 410, 10, 10);
        }


    }

    boolean checkNeighbors(int x, int y) {
        if(x-2 >=0 && x+2 < 41 && y-2 >=0 && y+2 < 41) {
            if(maze[x-2][y] == 1 && maze[x][y-2] == 1 || maze[x+2][y] == 1 || maze[x][y+2] == 1) {
                return true;
            }
            return false;
        }
        else {
            return false;
        }

    }


    public void paint(Graphics g) {
        if( i == 1){
            setStart();
            mover(r, c, g);
            mazeGenerate(g);
            borderFiller(g);
            //new Rat(0, 30, ratx*10, raty*10, g);
        }
        mazeGenerate(g);
        borderFiller(g);
        new Rat(ratx*10, raty*10, g);
        g.drawString("The score is:" + (400 -count), 600, 600);
        if(ratx == 41 && raty == 40){
            g.drawString("You WON!", 500, 500);
        }

    }

    public void update(Graphics g){
        paint(g);
    }

    public void init()
    {
        t = new Thread(this);
        t.start();
        i = 0;
    }

    public void run() {
        while(true)
        {

            i++;
            repaint();

            try {
                t.sleep(400);
            } catch (InterruptedException e) { ; }
        }

    }



    public static void main(String[] args) {
        Maze canvas = new Maze();
        canvas.init();
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.addKeyListener(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
        /*Maze canvas = new Maze();
        //Rat rat = new Rat();
        JFrame frame = new JFrame();
        frame.setIgnoreRepaint(true);
        //canvas.setIgnoreRepaint(true);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        //frame.pack();
        frame.setVisible(true);*/


    }

    public void getLocations(int x, int y){
        ratx = x;
        raty = y;
        count++;

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() =='w'){
            System.out.println(e.getKeyChar());
            if(rat.y - 1 >= 0 && rat.y - 1 < 42 && maze[rat.x][rat.y - 1] == 0) {
                rat.moveUp();
                System.out.println(rat.x +"," + rat.y);
                getLocations(rat.x, rat.y);
            }
            System.out.println(rat.x +"," + rat.y);
            System.out.println(maze[rat.x][rat.y - 1]);
        }
        else if(e.getKeyChar() =='a'){
            System.out.println(e.getKeyChar());
            if(rat.x - 1 >= 0 && rat.x - 1 < 42 && maze[rat.x -1][rat.y] == 0) {
                rat.moveLeft();
                System.out.println(rat.x +"," + rat.y);
                getLocations(rat.x, rat.y);
            }
            System.out.println(rat.x +"," + rat.y);
            System.out.println(maze[rat.x -1 ][rat.y]);

        }
        else if(e.getKeyChar() =='s'){
            System.out.println(e.getKeyChar());
            if(rat.y + 1 >= 0 && rat.y + 1 < 42 && maze[rat.x][rat.y +1] == 0){
                rat.moveDown();
                System.out.println(rat.x +"," + rat.y);
                getLocations(rat.x, rat.y);
            }
            System.out.println(rat.x +"," + rat.y);
            System.out.println(maze[rat.x][rat.y + 1]);

        }
        else if(e.getKeyChar() =='d'){
            System.out.println(e.getKeyChar());
            if(rat.x + 1 >= 0 && rat.x + 1 < 42 && maze[rat.x +1][rat.y] == 0){
                rat.moveRight();
                System.out.println(rat.x + "," + rat.y);
                getLocations(rat.x, rat.y);
            }
            System.out.println(rat.x +"," + rat.y);
            System.out.println(maze[rat.x][rat.y]);
        }
        else {
            System.out.println("Fuck off");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
