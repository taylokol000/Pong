import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {
    final int WIDTH=800;
    final int HEIGHT=600;

    private final int EDGESPACE=25;
    private final int DECORSIZE=25;

    Paddle pPaddle;
    Paddle cPaddle;
    Ball ball;
    Timer timer;
    Game game;

    public Board(Game game){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);

        pPaddle=new Paddle(this,game);
        ball=new Ball(this,game);
        cPaddle=new Paddle(this,game);

        this.game=game;
    }

    public void init(){
        ball.setPosition(WIDTH/2,HEIGHT/2);
        pPaddle.setPosition(EDGESPACE,HEIGHT/2);
        cPaddle.setPosition(WIDTH-EDGESPACE,HEIGHT/2);
        timer=new Timer(1000/60,this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        pPaddle.paint(g);
        cPaddle.paint(g);
        ball.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.checkCollisions(cPaddle);
        ball.checkCollisions(pPaddle);
        ball.move();
        cPaddle.move(ball);
        pPaddle.move();
        repaint();
    }
}
