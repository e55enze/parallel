package lab_7;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MMF_write {
    private static String bigTextFile = "test.txt";

    public static void main(String[] args) throws Exception
    {
        // Create file object
        File file = new File(bigTextFile);

        //Delete the file; we will create a new file
        file.delete();

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw"))
        {
            // Get file channel in read-write mode
            FileChannel fileChannel = randomAccessFile.getChannel();

            // Get direct byte buffer access using channel.map() operation
//            long size_file = 4096 * 8 * 8;
            long size_file = 256 * 8 * 8;
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, size_file);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = "";
            while (!s.equals("Over")) {
                try {
                    s = reader.readLine();
                    buffer.put(s.getBytes());
                }
                catch (IOException i) {
                    System.out.println(i);
                }
            }
        }
    }
}