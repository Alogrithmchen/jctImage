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

    public void DrawRectangle(int x0, int y0, int x1, int y1, ARGB argb)
    {
        int _x0, _y0, _x1, _y1;
        if (x0 < x1)
        {
            _x0 = x0;
            _x1 = x1;
        }
        else
        {
            _x0 = x1;
            _x1 = x0;
        }
        if (y0 < y1)
        {
            _y0 = y0;
            _y1 = y1;
        }
        else
        {
            _y0 = y1;
            _y1 = y0;
        }
        for (int i = _x0; i < _x1; i++)
            if (this.type == ImageType.BITMAP)
            {
                ((ColorImage) this.draw_obj).SetPixel(_y0, i, argb);
                ((ColorImage) this.draw_obj).SetPixel(_y1, i, argb);
            }
            else
            {
                ((ColorImage) this.draw_obj).SetPixel(i, _y0, argb);
                ((ColorImage) this.draw_obj).SetPixel(i, _y1, argb);
            }
        for (int i = _y0; i < _y1; i++)
            if (this.type == ImageType.BITMAP)
            {
                ((ColorImage) this.draw_obj).SetPixel(i, _x0, argb);
                ((ColorImage) this.draw_obj).SetPixel(i, _x1, argb);
            }
            else
            {
                ((ColorImage) this.draw_obj).SetPixel(_x0, i, argb);
                ((ColorImage) this.draw_obj).SetPixel(_x1, i, argb);
            }
    }
}
