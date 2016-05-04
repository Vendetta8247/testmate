package ua.com.vendetta8247.testmate;

import java.io.*;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        String[] files = {"D://done.png", "D://done1.png", "D://done3.png"};
        String archiveName = "D://testing.zip";
        String archiveName2 = "D://testing2.zip";
        String archiveName3 = "D://testing3.zip";
        HelperClass.archivation(archiveName, files);

        List<File> lst = HelperClass.directoryFiles("C://TMP", 50);
        HelperClass.archivation("D://ARCHIVE.zip", lst);
        for(File file : lst)
        {
            System.out.println(file.getName());
        }
        try {
            RandomAccessFile f = new RandomAccessFile(archiveName, "r");
            RandomAccessFile f2 = new RandomAccessFile(archiveName2, "rw");
            RandomAccessFile f3 = new RandomAccessFile(archiveName3, "rw");
            byte[] b = new byte[(int)f.length()];
            f.read(b);
            for(int i = 0; i<b.length;i++)
            {
                //System.out.print(b[i] + " ");
                if(i%32==0 && i != 0)
                //    System.out.println("");
                b[i] = (byte)(b[i]^(byte)259);
            }
            f2.write(b);

            for(int i = 0; i<b.length;i++)
            {
                b[i] = (byte)(b[i]^(byte)259);
            }
            f3.write(b);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}


//5 = 0101
//8 = 1000
