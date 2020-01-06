import java.awt.*;

public class Ball {
    private int x, y;
    private final int DIAMETER=20;
    private final int SPEED=4;
    Game game;

    double dx=SPEED, dy=SPEED;

    Board board;

    public Ball(Board board,Game game){
        x=0;
        y=0;

        //'this' keyword references the object that is executing or calling the this reference

        this.board=board;
        this.game=game;
    }
    double MAXANGLE=5*Math.PI/12;
    public void checkCollisions(Paddle other){
        if(getBounds().intersects(other.getBounds())){
            double paddleY=other.getBounds().getY();
            double paddleC=other.getBounds().getHeight()/2;
            double ballY=y;
            double relativeIntersect=(paddleY+paddleC)-ballY;
            double normalIntersect=relativeIntersect/paddleC;
            double bounceAngle=MAXANGLE+normalIntersect;
            if(x<board.getWidth()/2){
                dx*=-1;
            }
            if(x>board.getWidth()/2){
                dx*=-1;
            }
            dy=SPEED*(Math.random()*2-1);
        }
    }

    public void move(){

        //LEFT AND RIGHT
        if(x<=0||x+DIAMETER>=board.getWidth()){
            dx*=-1;
        }
        if(y<=0||y+DIAMETER>=board.getHeight()){
            dy*=-1;
        }
        x+=dx;
        y+=dy;
    }

    public void setPosition(int x,int y){
        this.x=x-DIAMETER/2;
        this.y=y-DIAMETER/2;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,DIAMETER,DIAMETER);
    }

    public void paint(Graphics g){
        g.fillOval(x,y,DIAMETER,DIAMETER);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getDiam() {
        return DIAMETER;
    }
}
