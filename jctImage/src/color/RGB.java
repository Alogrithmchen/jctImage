package color;

public class RGB
{

    public int R;
    public int G;
    public int B;

    public RGB()
    {
    }

    public RGB(int r, int g, int b)
    {
        this.R = r;
        this.G = g;
        this.B = b;
    }

    public boolean Equal(RGB rgb)
    {
        if (this.R == rgb.R && this.G == rgb.G && this.B == rgb.B)
            return true;
        else
            return false;
    }

    public static RGB Black = new RGB(0, 0, 0);
    public static RGB White = new RGB(255, 255, 255);
    public static RGB Red = new RGB(255, 0, 0);
    public static RGB Green = new RGB(0, 255, 0);
    public static RGB Blue = new RGB(0, 0, 255);
    public static RGB Yellow = new RGB(255, 255, 0);
    public static RGB Purple = new RGB(255, 0, 255);
    public static RGB Cyan = new RGB(0, 255, 255);

}
