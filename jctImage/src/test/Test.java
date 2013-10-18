package test;

import image.BMP;
import color.ARGB;
import draw.Draw;

public class Test
{

    public static void main(String[] args)
    {
        BMP bmp = new BMP(512, 256, ARGB.Black);
        Draw d = new Draw(bmp);
        d.DrawLine(50, 234, 12, 123, ARGB.Green);
        d.DrawLine(100, 23, 50, 234, ARGB.White);
        d.DrawLine(100, 123, 200, 444, ARGB.Blue);
        d.DrawRectangle(20, 30, 200, 300, ARGB.Red);
        d.Fill(50, 50, ARGB.Green);
        d.DrawFilledEllipse(20, 20, 200, 100, ARGB.Purple);
        d.DrawFilledRectangle(200, 400, 230, 500, ARGB.Yellow);
        bmp.Save("test.bmp");
    }

}
