
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class GUI extends JFrame{

private JPanel pantalla;
private JButton start;
private JButton stop;
private JSlider dormirX;
private JSlider dormirY;
private int posX;
private int posY;
private boolean parar=false;
Thread calculaX;
Thread calculaY;


public GUI(){
this.setLayout(new BorderLayout());

pantalla = new JPanel(){
            @Override
    public void paintComponent(Graphics g){
        g.drawOval(posX, posY, 3, 3);
    }
};

pantalla.setMinimumSize(new Dimension(200,200));
getContentPane().add(pantalla,BorderLayout.CENTER);

JPanel botones = new JPanel();
botones.setLayout(new GridLayout(4,1));

start = new JButton("START");
start.addActionListener( new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                parar = false;
                start.setEnabled(false);
                stop.setEnabled(true);
                calculaX = new Thread(){
                    @Override
                    public void run(){
                        oscilaX();
                    }
                };

                calculaY = new Thread(){
                    @Override
                    public void run(){
                        oscilaY();
                    }
                };

                calculaX.start();
                calculaY.start();

            }
});
botones.add(start);


stop = new JButton("STOP");
stop.addActionListener( new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                parar = true;
                start.setEnabled(true);
                stop.setEnabled(false);

            }
});
botones.add(stop);

dormirX = new JSlider();
botones.add(dormirX);
dormirY = new JSlider();
botones.add(dormirY);

this.add(botones,BorderLayout.EAST);

}



public static void main(String[] args){
    GUI gui = new GUI();
    gui.pack();
    gui.setVisible(true);
}

private void oscilaX(){
    
    for(int t=0;!parar;t++){
        posX=(int)(pantalla.getWidth()*(Math.sin(0.01*t)+1)/2);
        try{
            Thread.sleep(dormirX.getValue());}
        catch(Exception e){}
        pantalla.repaint();
        }
    }
private void oscilaY(){
    
    for(int t=0;!parar;t++){
        posY=(int)(pantalla.getHeight()*(Math.sin(0.03*t)+1)/2);
        try{
            Thread.sleep(dormirY.getValue());}
        catch(Exception e){}
        pantalla.repaint();
        }
    }

}






