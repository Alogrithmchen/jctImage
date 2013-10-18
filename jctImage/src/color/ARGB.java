package color;

public class ARGB extends RGB
{

    public int A;

    public ARGB(int a, int r, int g, int b)
    {
        this.A = a;
        this.R = r;
        this.G = g;
        this.B = b;
    }

    public ARGB Clone()
    {
        return new ARGB(this.A, this.R, this.G, this.B);
    }

    public boolean Equal(ARGB argb)
    {
        if (this.A == argb.A && this.R == argb.R && this.G == argb.G && this.B == argb.B)
            return true;
        else
            return false;
    }

    public int ToInt()
    {
        return (R << 24) | (G << 16) | (B << 8) | A;
    }

    public static ARGB Black = new ARGB(255, 0, 0, 0);
    public static ARGB White = new ARGB(255, 255, 255, 255);
    public static ARGB Red = new ARGB(255, 255, 0, 0);
    public static ARGB Green = new ARGB(255, 0, 255, 0);
    public static ARGB Blue = new ARGB(255, 0, 0, 255);
    public static ARGB Yellow = new ARGB(255, 255, 255, 0);
    public static ARGB Purple = new ARGB(255, 255, 0, 255);
    public static ARGB Cyan = new ARGB(255, 0, 255, 255);

}
