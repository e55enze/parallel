package lab_7;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MMF_read {
    private static String bigTextFile = "test.txt";

    public static void main(String[] args) throws Exception
    {
        try (RandomAccessFile file = new RandomAccessFile(new File(bigTextFile), "r")) {

            //Get file channel in read-only mode
            FileChannel fileChannel = file.getChannel();

            //Get direct byte buffer access using channel.map() operation
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());

            // the buffer now reads the file as if it were loaded in memory.
            System.out.println(buffer.isLoaded());
            System.out.println(buffer.capacity());

            for (int i = 0; i < buffer.limit(); i++) {
                char sym = (char) buffer.get();
                System.out.print(sym);
            }
        }
    }
}