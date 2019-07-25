package by.itacademy.web.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class ImageLoader {

    private static final String LOCATION = "C:\\Users\\Admin\\Desktop\\pict\\";

    public String load(MultipartFile file) {
        String filename = file.getOriginalFilename();

        try {
            file.transferTo(new File(LOCATION + filename));
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/pictures/" + filename;
    }
}
