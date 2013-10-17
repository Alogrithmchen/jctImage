package draw;

import image.Bitmap;
import image.ColorImage;
import color.ARGB;

public class Draw
{
    private enum ImageType
    {
        BITMAP;
    }

    private Object draw_obj;
    private ImageType type;

    public Draw(Bitmap obj)
    {
        this.draw_obj = obj;
        this.type = ImageType.BITMAP;
    }

    public void DrawLine(int x0, int y0, int x1, int y1, ARGB argb)
    {
        int dx, dy;
        int sx, sy;
        int err, err2;
        if (this.type != ImageType.BITMAP)
            return;
        ColorImage img = (ColorImage) draw_obj;
        if (x0 < 0 || y0 < 0 || x1 < 0 || y1 < 0 || x0 > img.width || x1 > img.width || y0 > img.hight || y1 > img.hight)
            return;
        dx = Math.abs(x1 - x0);
        dy = Math.abs(y1 - y0);
        if (x0 < x1)
            sx = 1;
        else
            sx = -1;
        if (y0 < y1)
            sy = 1;
        else
            sy = -1;
        err = dx - dy;
        while (true)
        {
            if (this.type == ImageType.BITMAP)
                img.SetPixel(y0, x0, argb);
            else
                img.SetPixel(x0, y0, argb);
            if (x0 == x1 && y0 == y1)
                break;
            err2 = 2 * err;
            if (err2 > -dy)
            {
                err -= dy;
                x0 += sx;
            }
            if (x0 == x1 && y0 == y1)
            {
                if (this.type == ImageType.BITMAP)
                    img.SetPixel(y0, x0, argb);
                else
                    img.SetPixel(x0, y0, argb);
                break;
            }
            if (err2 < dx)
            {
                err += dx;
                y0 += sy;
            }
        }
    }
}
