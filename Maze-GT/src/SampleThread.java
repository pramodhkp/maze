import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

class SampleThreader extends Applet implements Runnable, KeyListener {

    int i;
    Thread t;
    Rat rat = new Rat(0, 0);
    public void init()
    {
        t = new Thread(this);
        t.start();

        i = 0;
    }

    public void changeGraphics(Graphics g) {
        //rat = new Rat(rat.x*10, rat.y*10, g );
    }


   public void run()
     {

       while(true)
       {
         //g.drawRect(0, i, 20, 20);
           i++;
         repaint();

         try {
           t.sleep(1000);
         } catch (InterruptedException e) { ; }
       }
     }

    public void paint(Graphics g) {
        {
            //g.drawString("i = "+i,10,20);
            changeGraphics(g);
        }
    }


    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        SampleThreader s = new SampleThreader();
        //s.start();
        s.init();
        frame.add(s);
        frame.addKeyListener(s);
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() =='w'){
            System.out.println(e.getKeyChar());
            rat.moveUp();
            System.out.println(rat.x +"," + rat.y);
            //getLocations(rat.x, rat.y);
        }
        else if(e.getKeyChar() =='a'){
            System.out.println(e.getKeyChar());
            rat.moveLeft();
            System.out.println(rat.x +"," + rat.y);
            //getLocations(rat.x, rat.y);

        }
        else if(e.getKeyChar() =='s'){
            System.out.println(e.getKeyChar());
            rat.moveDown();
            System.out.println(rat.x +"," + rat.y);
            //getLocations(rat.x, rat.y);

        }
        else if(e.getKeyChar() =='d'){
            System.out.println(e.getKeyChar());
            rat.moveRight();
            System.out.println(rat.x + "," + rat.y);
            //getLocations(rat.x, rat.y);
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

	