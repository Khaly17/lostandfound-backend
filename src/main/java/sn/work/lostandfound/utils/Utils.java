package sn.work.lostandfound.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    static String BASE_PATH = "C:\\Users\\DIENG Khaly (MS2E)\\Music\\lostandfound-backend\\src\\main\\resources\\images\\";

    public static String fileUpload(MultipartFile file, String path) throws IOException {
        String extension = getFileExtension(file.getOriginalFilename());
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = BASE_PATH + timestamp + "." + extension;
        saveFile(filename, file);
        return filename;
    }
    public static void saveFile(String filename, MultipartFile file) throws IOException {
        File convertFile = new File(filename);
        convertFile.createNewFile();
        try (FileOutputStream fout = new FileOutputStream(convertFile))
        {
            fout.write(file.getBytes());
        }
        catch (Exception exe)
        {
            exe.printStackTrace();
        }
    }

    public static String getFileExtension(String filename) {
        if (filename.lastIndexOf(".") != -1 && filename.lastIndexOf(".") != 0) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}
