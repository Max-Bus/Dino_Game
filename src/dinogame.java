import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class dinogame extends JFrame {
    public dinogame() {
        Dino p = new Dino();
        p.setFocusable(true);
        p.requestFocus();
        add(p);
    }

    public static void main(String[] args) {
        JFrame frame = new dinogame();
        frame.setTitle("Raising Flag");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class Dino extends JPanel {
        int nextshape = 0;
        int score = 0;
        dinosaur dino = new dinosaur(5);
        int speed = 4;
        landscape thebackround = new landscape();
        dino2 d = new dino2(getWidth());
        int x = 0;
        int k = 0;
        int[] list ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int vel =1;
        int timealoft = 0;
        int jump = 5;
        JLabel score1 = new JLabel("score:");
        public Dino() {
            Timer timer = new Timer(400 / speed, new spawnthing());
            timer.start();
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode()==KeyEvent.VK_UP&&jump<6){
                        timealoft=5;
                    }
                    else if(e.getKeyCode()==KeyEvent.VK_DOWN)
                        jump-=10;

                }
            });
            Border b = new LineBorder(Color.BLACK,2);
            score1.setBorder(b);
            add(score1);
            setPreferredSize(getMaximumSize());

        }


        public class spawnthing implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                x -= vel/100;
                k++;
                if (timealoft>0) {
                    dino.jump+=10;
                    timealoft--;
                }
                else if (jump>5){
                    jump-=5;
                }

                Random rand = new Random();
                if (k >1){
                    for (int i = 0; i < 19; i++) {
                        list[i]=list[i+1];
                    }
                    list[19] = rand.nextInt(6);
                    k=0;

                    score++;
                    score1.setText("score:"+score);
                    speed++;
                }
                for (int i = 0; i < 9; i+=3) {
                    if((list[i]==1||list[i]==2)&&(list[i+1]==1||list[i+1]==2)&&(list[i+2]==1||list[i+2]==2)){
                        list[i]=0;
                    }
                }
                repaint();
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawLine(x, getHeight() / 2, x + getWidth(), getHeight()/2);
            for(int i = 0;i<20;i++) {
                if (list[i] == 0||list[i] ==3 ||list[i] ==4||list[i] ==5)
                    g.drawLine((i *getWidth()) / 20, getHeight() / 2, ((i *getWidth()) +getWidth()) / 20, getHeight() / 2);
                if (list[i] == 1) {
                    g.drawRect((i *getWidth()) / 20, (getHeight()/2)-10, (getWidth()) / 20, 10);
                    g.drawLine(((2 * (i *getWidth())) + getWidth()) / 20, getHeight()/2, (i + getWidth()) / 5, getHeight()/2);
                }
                if (list[i] == 2) {
                    g.drawRect((i *getWidth()) / 20, (getHeight()/2)-5, (getWidth()) / 20, 5);
                    g.drawLine((i + getWidth()) / 20, getHeight()/2, (20 * i + getWidth()) / 20, getHeight()/2);
                }

            }



        }
    }
    class dinosaur extends JPanel{
        int jump = 0;
        public dinosaur(int jump){
            this.jump = jump;
            repaint();
        }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.fillRect(getWidth()/10,(getHeight()/2)-jump,5,5);

        }
        public void repaint1(){
            repaint();
        }
        public boolean isdead(int type){
            if (type ==1&&jump<10)
                return true;
            else if (type ==2&&jump<5)
                return true;
            return false;
        }

        public int getJump() {
            return jump;
        }

        public void setJump(int jump) {
            this.jump = jump;
        }
    }
}
