package test;

import image.Bitmap;
import color.ARGB;
import draw.Draw;

public class Test
{

    public static void main(String[] args)
    {
        Bitmap bmp = new Bitmap(512, 256, new ARGB(255, 255, 255, 0));
        Draw d = new Draw(bmp);
        d.DrawLine(50, 234, 12, 123, new ARGB(255, 255, 0, 0));
        d.DrawLine(100, 23, 50, 234, new ARGB(255, 255, 0, 0));
        d.DrawLine(100, 23, 50, 234, new ARGB(255, 255, 0, 0));
        d.DrawRectangle(20, 30, 200, 300, new ARGB(255, 0, 255, 0));
        d.Fill(50, 50, new ARGB(255, 0, 255, 255));
        bmp.Save("test.bmp");
    }

}
