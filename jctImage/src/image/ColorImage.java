package image;

import color.ARGB;

public class ColorImage
{
    protected int hight;
    protected int width;
    protected ARGB matrix[][];

    public ColorImage()
    {
    }

    public ColorImage(int h, int w)
    {
        Create(h, w);
    }

    public ColorImage(int h, int w, ARGB argb)
    {
        Create(h, w);
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                this.matrix[i][j] = argb;
    }

    public void SetPixel(int x, int y, ARGB argb)
    {
        if (0 <= x && x < this.hight && 0 <= y && y < this.width)
            this.matrix[x][y] = argb;
    }
    
    public ARGB GetPixel(int x, int y)
    {
        if (0 <= x && x < this.hight && 0 <= y && y < this.width)
            return this.matrix[x][y];
        return null;
    }

    private void Create(int h, int w)
    {
        this.hight = h;
        this.width = w;
        this.matrix = new ARGB[h][w];
    }
}
