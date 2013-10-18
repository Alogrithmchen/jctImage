package test;

import image.BMP;
import color.RGB;
import draw.Draw;

public class Test
{

    public static void main(String[] args)
    {
        BMP bmp = new BMP(512, 512, RGB.Black);
        Draw d = new Draw(bmp);
        d.DrawLine(0, 0, 50, 234, RGB.Green);
        d.DrawLine(100, 23, 50, 234, RGB.White);
        d.DrawLine(100, 123, 200, 444, RGB.Blue);
        d.DrawRectangle(20, 30, 200, 300, RGB.Red);
        d.Fill(50, 200, RGB.Green);
        d.DrawFilledEllipse(20, 20, 200, 100, RGB.Purple);
        d.DrawFilledRectangle(200, 400, 230, 500, RGB.Yellow);
        bmp.SetBitCount(24);
        BMP bmp2 = new BMP("test.bmp");
        d.LinkImage(bmp2);
        d.DrawEllipse(50, 50, 240, 240, RGB.Cyan);
        bmp.Save("test.bmp");
        bmp2.Save("test2.bmp");
    }

}
