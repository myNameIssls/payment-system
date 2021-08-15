package cn.tyrone.payment.channel.infrastructure.api.citic.service;

import java.io.*;
import java.util.Base64;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 中信银行电子回单解析工具
 */
public class CiticElecReceiptUtil {

    private static final String PATH = "/Users/shanglishuai/temp/temp/";

    public static void parse(String fileContent, String fileName) {
        byte[] fileContentDecode = Base64.getDecoder().decode(fileContent);
        File file = new File(PATH + fileName);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(fileContentDecode);

            ZipFile zipFile = new ZipFile(file);
            Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
            while (enumeration.hasMoreElements()) {
                ZipEntry zipEntry = enumeration.nextElement();
                String pdfName = zipEntry.getName();
                File pdfFile = new File(PATH + pdfName);
                pdfFile.createNewFile();
                InputStream inputStream = zipFile.getInputStream(zipEntry);
                FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);

                int ln;
                byte[] buf = new byte[1024];

                while ((ln = inputStream.read(buf)) != -1) {
                    fileOutputStream.write(buf, 0, ln);
                }
                fileOutputStream.close();
                inputStream.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
