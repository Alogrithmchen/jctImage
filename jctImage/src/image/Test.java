package image;

import color.ARGB;
import draw.Draw;

public class Test
{

    public static void main(String[] args)
    {
        Bitmap bmp = new Bitmap(512, 256, new ARGB(255, 255, 255, 0));
        Draw d = new Draw(bmp);
        d.DrawLine(0, 0, 50, 234, new ARGB(255, 255, 0, 0));
        bmp.Save("test.bmp");
    }

}
