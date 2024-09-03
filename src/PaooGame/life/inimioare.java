package PaooGame.life;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.Assets.hearts;


public class inimioare {
    public BufferedImage img;
    public int hx,hy;
    public int idi;

    public inimioare(int x,int y,int tip) {
        this.hx=x;
        this.hy=y;
       this.idi=tip;
    if(this.idi==0) {
    img = hearts[1][0];
}
else
    if(this.idi==1) {
        img=hearts[0][0];
    }
    else
        if(this.idi==2) {img=hearts[2][0];}
    }
    public void DrawHearts(Graphics g) {
        g.drawImage(img,hx,hy,35,35,null);
    }


}
