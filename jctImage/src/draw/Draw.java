package draw;

import image.BMP;
import image.ColorImage;

import java.util.Stack;

import color.ARGB;
import color.RGB;

public class Draw
{
    private enum ImageType
    {
        BMP;
    }

    private Object draw_obj;
    private ImageType type;

    public Draw(BMP obj)
    {
        LinkImage(obj);
    }

    public void LinkImage(BMP bmp)
    {
        this.draw_obj = bmp;
        this.type = ImageType.BMP;
    }

    public void DrawLine(int x0, int y0, int x1, int y1, RGB rgb)
    {
        DrawLine(x0, y0, x1, y1, new ARGB(255, rgb.R, rgb.G, rgb.B));
    }

    public void DrawLine(int x0, int y0, int x1, int y1, ARGB argb)
    {
        int dx, dy;
        int sx, sy;
        int err, err2;
        if (this.type != ImageType.BMP)
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
            if (this.type == ImageType.BMP)
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
                if (this.type == ImageType.BMP)
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

    public void DrawRectangle(int x0, int y0, int x1, int y1, RGB rgb)
    {
        DrawRectangle(x0, y0, x1, y1, new ARGB(255, rgb.R, rgb.G, rgb.B));
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
        {
            this.DrawPoint(i, _y0, argb);
            this.DrawPoint(i, _y1, argb);
        }
        for (int i = _y0; i < _y1; i++)
        {
            this.DrawPoint(_x0, i, argb);
            this.DrawPoint(_x1, i, argb);
        }
    }

    public void DrawFilledRectangle(int x0, int y0, int x1, int y1, RGB rgb)
    {
        DrawFilledRectangle(x0, y0, x1, y1, new ARGB(255, rgb.R, rgb.G, rgb.B));
    }

    public void DrawFilledRectangle(int x0, int y0, int x1, int y1, ARGB argb)
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
            for (int j = _y0; j < _y1; j++)
            {
                this.DrawPoint(i, j, argb);
                this.DrawPoint(i, j, argb);
            }
    }

    public void DrawEllipse(int x0, int y0, int x1, int y1, RGB rgb)
    {
        DrawEllipse(x0, y0, x1, y1, new ARGB(255, rgb.R, rgb.G, rgb.B));
    }

    public void DrawEllipse(int x0, int y0, int x1, int y1, ARGB argb)
    {
        if (x0 == x1 || y0 == y1)
            return;
        int a = Math.abs(x0 - x1) / 2;
        int b = Math.abs(y0 - y1) / 2;
        int xc = (x0 + x1) / 2;
        int yc = (y0 + y1) / 2;
        int sqa = a * a;
        int sqb = b * b;
        int x = 0;
        int y = b;
        int d = 2 * sqb - 2 * b * sqa + sqa;
        int p = (int) ((double) sqa / Math.sqrt((double) (sqa + sqb)));
        this.DrawPoint(xc + x, yc + y, argb);
        this.DrawPoint(xc - x, yc + y, argb);
        this.DrawPoint(xc + x, yc - y, argb);
        this.DrawPoint(xc - x, yc - y, argb);
        while (x <= p)
        {
            if (d < 0)
                d += 2 * sqb * (2 * x + 3);
            else
            {
                d += 2 * sqb * (2 * x + 3) - 4 * sqa * (y - 1);
                y--;
            }
            x++;
            this.DrawPoint(xc + x, yc + y, argb);
            this.DrawPoint(xc - x, yc + y, argb);
            this.DrawPoint(xc + x, yc - y, argb);
            this.DrawPoint(xc - x, yc - y, argb);
        }
        d = sqb * (x * x + x) + sqa * (y * y - y) - sqa * sqb;
        while (y >= 0)
        {
            this.DrawPoint(xc + x, yc + y, argb);
            this.DrawPoint(xc - x, yc + y, argb);
            this.DrawPoint(xc + x, yc - y, argb);
            this.DrawPoint(xc - x, yc - y, argb);
            y--;
            if (d < 0)
            {
                x++;
                d = d - 2 * sqa * y - sqa + 2 * sqb * x + 2 * sqb;
            }
            else
                d = d - 2 * sqa * y - sqa;
        }
    }

    public void DrawFilledEllipse(int x0, int y0, int x1, int y1, RGB rgb)
    {
        DrawFilledEllipse(x0, y0, x1, y1, new ARGB(255, rgb.R, rgb.G, rgb.B));
    }

    public void DrawFilledEllipse(int x0, int y0, int x1, int y1, ARGB argb)
    {
        if (x0 == x1 || y0 == y1)
            return;
        int a = Math.abs(x0 - x1) / 2;
        int b = Math.abs(y0 - y1) / 2;
        int xc = (x0 + x1) / 2;
        int yc = (y0 + y1) / 2;
        int sqa = a * a;
        int sqb = b * b;
        int x = 0;
        int y = b;
        int d = 2 * sqb - 2 * b * sqa + sqa;
        int p = (int) ((double) sqa / Math.sqrt((double) (sqa + sqb)));
        for (int i = xc - x; i <= xc + x; i++)
        {
            this.DrawPoint(i, yc + y, argb);
            this.DrawPoint(i, yc - y, argb);
        }
        while (x <= p)
        {
            if (d < 0)
                d += 2 * sqb * (2 * x + 3);
            else
            {
                d += 2 * sqb * (2 * x + 3) - 4 * sqa * (y - 1);
                y--;
            }
            x++;
            for (int i = xc - x; i <= xc + x; i++)
            {
                this.DrawPoint(i, yc + y, argb);
                this.DrawPoint(i, yc - y, argb);
            }
        }
        d = sqb * (x * x + x) + sqa * (y * y - y) - sqa * sqb;
        while (y >= 0)
        {
            for (int i = xc - x; i <= xc + x; i++)
            {
                this.DrawPoint(i, yc + y, argb);
                this.DrawPoint(i, yc - y, argb);
            }
            y--;
            if (d < 0)
            {
                x++;
                d = d - 2 * sqa * y - sqa + 2 * sqb * x + 2 * sqb;
            }
            else
                d = d - 2 * sqa * y - sqa;
        }
    }

    public void Fill(int x, int y, RGB rgb)
    {
        Fill(x, y, new ARGB(255, rgb.R, rgb.G, rgb.B));
    }

    public void Fill(int x, int y, ARGB argb)
    {
        Stack<Integer> xs = new Stack<Integer>();
        Stack<Integer> ys = new Stack<Integer>();
        ARGB old = this.GetARGBPoint(x, y);
        xs.push(x);
        ys.push(y);
        if (argb == old)
            return;
        while (!xs.isEmpty() && !ys.isEmpty())
        {
            int _x = xs.pop();
            int _y = ys.pop();
            this.DrawPoint(_x, _y, argb);
            if (this.GetARGBPoint(_x - 1, _y).Equal(old))
            {
                xs.push(_x - 1);
                ys.push(_y);
            }
            if (this.GetARGBPoint(_x, _y - 1).Equal(old))
            {
                xs.push(_x);
                ys.push(_y - 1);
            }
            if (this.GetARGBPoint(_x + 1, _y).Equal(old))
            {
                xs.push(_x + 1);
                ys.push(_y);
            }
            if (this.GetARGBPoint(_x, _y + 1).Equal(old))
            {
                xs.push(_x);
                ys.push(_y + 1);
            }
        }
    }

    private void DrawPoint(int x, int y, ARGB argb)
    {
        ColorImage img = ((ColorImage) this.draw_obj);
        if (this.type == ImageType.BMP)
            img.SetPixel(y, x, argb);
        else
            img.SetPixel(x, y, argb);
    }

    private ARGB GetARGBPoint(int x, int y)
    {
        ColorImage img = ((ColorImage) this.draw_obj);
        if (this.type == ImageType.BMP)
            return img.GetPixel(y, x);
        else
            return img.GetPixel(x, y);
    }

}
