package org.huha.cboardspringbootstarter.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author WangKun
 * @create 2018-12-08
 * @desc
 **/
public class IOUtils {

    /**
     * @auther: WangKun
     * @date: 2018-12-08 16:15
     * @description: InputStream Converts to byte arrays
     */
    public static byte[] readInputStream(InputStream inputStream, String inputStreamName) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[16 * 1024];
        try {
            int bytesRead = inputStream.read(buffer);
            while (bytesRead != -1) {
                outputStream.write(buffer, 0, bytesRead);
                bytesRead = inputStream.read(buffer);
            }
        } catch (Exception e) {
            throw new RuntimeException("Couldn't read input stream " + inputStreamName, e);
        } finally {
            inputStream.close();
        }
        return outputStream.toByteArray();
    }
}
