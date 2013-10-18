package image;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

import color.ARGB;
import color.RGB;

public class BMP extends ColorImage
{

    private static final int bf_type = 0;
    private static final int bf_size = 2;
    private static final int bf_offbits = 10;
    private static final int bi_size = 0;
    private static final int bi_width = 4;
    private static final int bi_height = 8;
    private static final int bi_planes = 12;
    private static final int bi_bitcount = 14;
    private static final int bmp_a = 3;
    private static final int bmp_r = 2;
    private static final int bmp_g = 1;
    private static final int bmp_b = 0;

    private int bit_count;

    public BMP(int h, int w)
    {
        Create(h, w);
    }

    public BMP(int h, int w, RGB rgb)
    {
        Create(h, w);
        InitColor(new ARGB(255, rgb.R, rgb.G, rgb.B));
    }

    public BMP(int h, int w, ARGB argb)
    {
        Create(h, w);
        InitColor(argb);
    }

    public BMP(String path)
    {
        if (Open(path) == false)
        {
            this.height = 0;
            this.width = 0;
            this.bit_count = 0;
            this.matrix = null;
        }
    }

    public void SetBitCount(int bc)
    {
        switch (bc)
        {
        case 24:
            this.bit_count = 24;
            break;
        case 32:
            this.bit_count = 32;
            break;
        }
    }

    public int GetBitCount()
    {
        return this.bit_count;
    }

    public boolean Save(String path)
    {
        try
        {
            int image_size = this.height * this.width;
            int file_size = image_size + 14 + 40;
            FileOutputStream save_file = new FileOutputStream(path);
            byte fileheader[] = new byte[14];
            byte infoheader[] = new byte[40];
            byte argb[] = new byte[this.bit_count == 32 ? 4 : 3];
            Arrays.fill(fileheader, (byte) 0);
            fileheader[bf_type] = 0x42;
            fileheader[bf_type + 1] = 0x4d;
            fileheader[bf_size] = (byte) file_size;
            fileheader[bf_size + 1] = (byte) (file_size >> 8);
            fileheader[bf_size + 2] = (byte) (file_size >> 16);
            fileheader[bf_size + 3] = (byte) (file_size >> 24);
            fileheader[bf_offbits] = 54;
            save_file.write(fileheader);
            infoheader[bi_size] = 40;
            infoheader[bi_width] = (byte) this.width;
            infoheader[bi_width + 1] = (byte) (this.width >> 8);
            infoheader[bi_width + 2] = (byte) (this.width >> 16);
            infoheader[bi_width + 3] = (byte) (this.width >> 24);
            infoheader[bi_height] = (byte) this.height;
            infoheader[bi_height + 1] = (byte) (this.height >> 8);
            infoheader[bi_height + 2] = (byte) (this.height >> 16);
            infoheader[bi_height + 3] = (byte) (this.height >> 24);
            infoheader[bi_planes] = 1;
            infoheader[bi_bitcount] = (byte) this.bit_count;
            save_file.write(infoheader);
            for (int i = 0; i < this.height; i++)
                for (int j = 0; j < this.width; j++)
                {
                    argb[bmp_r] = (byte) this.matrix[i][j].R;
                    argb[bmp_g] = (byte) this.matrix[i][j].G;
                    argb[bmp_b] = (byte) this.matrix[i][j].B;
                    if (this.bit_count == 32)
                        argb[bmp_a] = (byte) this.matrix[i][j].A;
                    save_file.write(argb);
                }
            save_file.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Open(String path)
    {
        try
        {
            FileInputStream open_file = new FileInputStream(path);
            byte fileheader[] = new byte[14];
            byte infoheader[] = new byte[40];
            open_file.read(fileheader);
            open_file.read(infoheader);
            if (fileheader[bf_type] != 0x42 || fileheader[bf_type + 1] != 0x4d)
            {
                open_file.close();
                throw new Exception("Not BMP File");
            }
            this.width = infoheader[bi_width + 3];
            this.width = (this.width << 8) + infoheader[bi_width + 2];
            this.width = (this.width << 8) + infoheader[bi_width + 1];
            this.width = (this.width << 8) + infoheader[bi_width];
            this.height = infoheader[bi_height + 3];
            this.height = (this.height << 8) + infoheader[bi_height + 2];
            this.height = (this.height << 8) + infoheader[bi_height + 1];
            this.height = (this.height << 8) + infoheader[bi_height];
            this.bit_count = infoheader[bi_bitcount];
            byte argb[] = new byte[this.bit_count == 32 ? 4 : 3];
            this.matrix = new ARGB[this.height][this.width];
            InitColor(ARGB.Black);
            for (int i = 0; i < this.height; i++)
                for (int j = 0; j < this.width; j++)
                {
                    open_file.read(argb);
                    this.matrix[i][j].R = argb[bmp_r];
                    this.matrix[i][j].G = argb[bmp_g];
                    this.matrix[i][j].B = argb[bmp_b];
                    if (this.bit_count == 32)
                        this.matrix[i][j].A = argb[bmp_a];
                }
            open_file.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void InitColor(ARGB argb)
    {
        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++)
                this.matrix[i][j] = argb.Clone();
    }

    private void Create(int h, int w)
    {
        this.height = h;
        this.width = w;
        this.matrix = new ARGB[h][w];
        this.bit_count = 32;
    }

}
