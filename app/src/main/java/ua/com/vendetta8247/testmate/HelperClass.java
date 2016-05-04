package ua.com.vendetta8247.testmate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Y500 on 13.03.2016.
 */
public class HelperClass {
    public static void archivation(String archiveName, String[] srcFiles) {

        String zipFile = archiveName;

        try {

            // create byte buffer
            byte[] buffer = new byte[1024];

            FileOutputStream fos = new FileOutputStream(zipFile);

            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i=0; i < srcFiles.length; i++) {
                File srcFile = new File(srcFiles[i]);
                FileInputStream fis = new FileInputStream(srcFile);
                // begin writing a new ZIP entry, positions the stream to the start of the entry data
                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int length;

                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                zos.closeEntry();

                // close the InputStream
                fis.close();

            }

            // close the ZipOutputStream
            zos.close();

        }
        catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }

    }
    public static void archivation(String archiveName, List<File> srcFiles) {

        String zipFile = archiveName;

        try {

            // create byte buffer
            byte[] buffer = new byte[1024];

            FileOutputStream fos = new FileOutputStream(zipFile);

            ZipOutputStream zos = new ZipOutputStream(fos);


            for(File f : srcFiles)
            {
                FileInputStream fis = new FileInputStream(f);
                System.out.println("fis created for file " + f.getName());
                // begin writing a new ZIP entry, positions the stream to the start of the entry data
                zos.putNextEntry(new ZipEntry(f.getName()));

                int length;

                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                zos.closeEntry();

                // close the InputStream
                fis.close();
            }

            // close the ZipOutputStream
            zos.close();

        }
        catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }

    }

    public static List<File> directoryFiles(String dir, int size)
    {
        List<File> lst = new ArrayList<File>();
        File f = new File(dir);
        String[] files = f.list();
        if (files.length == 0) {
            System.out.println("The directory is empty");
        } else {
            for (String aFile : files) {
                aFile = new String(dir + "/" + aFile);
                //System.out.println(aFile);
                File tmp = new File(aFile);
                //System.out.println(tmp.getName() + " \t" + tmp.length());
                if(tmp.getAbsoluteFile().exists() && tmp.length()/(1024*1024) < size && tmp.isFile())
                {
                   // System.out.println("File exists");
                    lst.add(tmp);
                }
            }
        }
        return lst;
    }
}
