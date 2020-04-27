package com.gec.oasys.controller;

import com.gec.oasys.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 卓炎秋
 * @date : 2020-04-16 11:25
 */
@Controller
@RequestMapping("/upload")
public class UploadFileController {

    @RequestMapping(value = "/image",method = RequestMethod.POST)
    @ResponseBody
    public Map<String ,Object> image(HttpServletRequest request, MultipartFile file){
        HashMap<String, Object> map = new HashMap<>();
        String uploadPath = request.getServletContext().getRealPath("\\upload\\image\\");
        try {
            String image = UploadUtil.uploadFile(file, uploadPath);
            map.put("code",0);
            map.put("image",image);
        }catch (Exception e){
            map.put("code",1);
            e.printStackTrace();
        }
        return  map;
    }

}
