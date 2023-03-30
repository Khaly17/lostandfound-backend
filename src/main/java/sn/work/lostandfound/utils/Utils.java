package sn.work.lostandfound.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {
    static String BASE_PATH = "C:\\Users\\DIENG Khaly (MS2E)\\Music\\lostandfound-backend\\src\\main\\resources\\images\\";

    public static String fileUpload(MultipartFile file) throws IOException {
        String filename = BASE_PATH + file.getOriginalFilename();
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
}
