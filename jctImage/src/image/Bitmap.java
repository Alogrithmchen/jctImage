package image;

import java.io.FileOutputStream;
import java.util.Arrays;

import color.ARGB;

public class Bitmap extends ColorImage
{

    public Bitmap(int h, int w)
    {
        Create(h, w);
    }

    public Bitmap(int h, int w, ARGB argb)
    {
        Create(h, w);
        InitColor(argb);
    }

    public boolean Save(String path)
    {
        try
        {
            int image_size = this.hight * this.width;
            int file_size = image_size + 14 + 40;
            FileOutputStream save_file = new FileOutputStream(path);
            byte fileheader[] = new byte[14];
            byte infoheader[] = new byte[40];
            byte argb[] = new byte[4];
            Arrays.fill(fileheader, (byte) 0);
            fileheader[0] = 0x42;
            fileheader[1] = 0x4d;
            fileheader[2] = (byte) file_size;
            fileheader[3] = (byte) (file_size >> 8);
            fileheader[4] = (byte) (file_size >> 16);
            fileheader[5] = (byte) (file_size >> 24);
            fileheader[10] = 54;
            save_file.write(fileheader);
            infoheader[0] = 40;
            infoheader[4] = (byte) this.width;
            infoheader[5] = (byte) (this.width >> 8);
            infoheader[6] = (byte) (this.width >> 16);
            infoheader[7] = (byte) (this.width >> 24);
            infoheader[8] = (byte) this.hight;
            infoheader[9] = (byte) (this.hight >> 8);
            infoheader[10] = (byte) (this.hight >> 16);
            infoheader[11] = (byte) (this.hight >> 24);
            infoheader[12] = 1;
            infoheader[14] = 32;
            save_file.write(infoheader);
            for (int i = 0; i < this.hight; i++)
                for (int j = 0; j < this.width; j++)
                {
                    argb[0] = (byte) this.matrix[i][j].B;
                    argb[1] = (byte) this.matrix[i][j].G;
                    argb[2] = (byte) this.matrix[i][j].R;
                    argb[3] = (byte) this.matrix[i][j].A;
                    save_file.write(argb);
                }
            save_file.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void InitColor(ARGB argb)
    {
        for (int i = 0; i < this.hight; i++)
            for (int j = 0; j < this.width; j++)
                this.matrix[i][j] = argb;
    }

    private void Create(int h, int w)
    {
        this.hight = h;
        this.width = w;
        this.matrix = new ARGB[h][w];
    }
}
