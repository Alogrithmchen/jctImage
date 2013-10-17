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

    public int ToInt()
    {
        return (R << 24) | (G << 16) | (B << 8) | A;
    }
}
