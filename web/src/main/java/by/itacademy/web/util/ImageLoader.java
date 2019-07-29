package by.itacademy.web.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class ImageLoader {

    private static final String LOCATION = "D:\\pictures\\";

    public String load(MultipartFile file) {
        String filename = file.getOriginalFilename();

        try {
            file.transferTo(new File(LOCATION + filename));
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "pictures/" + filename;
    }
}
