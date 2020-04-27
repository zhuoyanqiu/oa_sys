package com.gec.oasys.util;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class UploadUtil {
    private static Logger log = Logger.getLogger(UploadUtil.class);

    public static String uploadFile(MultipartFile file, String path) throws IOException {
        String name = file.getOriginalFilename();
        String suffixName = name.substring(name.lastIndexOf("."));
        String hash = Integer.toHexString(new
                Random().nextInt());
        String fileName = hash + suffixName;
        File tempFile = new File(path, fileName);
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdirs();
        }
        if (tempFile.exists()) {
            tempFile.delete();
        }
//        tempFile.createNewFile();
        file.transferTo(tempFile);
        return tempFile.getName();
    }
}