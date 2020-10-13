package com.nju.software.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.nju.software.Bean.Image;
import com.nju.software.Token.UserLoginToken;
import com.nju.software.service.ImageService;
import com.nju.software.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/08/下午2:54
 * @Description:
 */

@Controller
@RequestMapping("image")
public class ImageController {

    @Autowired
    ImageService imageService;
    @Autowired
    private UUID uuid;


    @CrossOrigin
    @UserLoginToken
    @ResponseBody
    @RequestMapping(value = "/addimage")
    public Map<String, String> addImage(@RequestBody Image image, HttpServletRequest httpServletRequest){

        String userId,username;
        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        // 获取 token 中的 user id
        try {
            userId = JWT.decode(token).getAudience().get(0);
            username = JWT.decode(token).getSubject();
            image.setUserId(userId);
            image.setUsername(username);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        String id = uuid.getUUID();
        image.setId(id);
        image.setCreatedAt(new Date().getTime());

        imageService.save(image);

        Map<String,String> sMap = new HashMap<>();
        sMap.put("id",id);
        return sMap;
    }

    @RequestMapping(value = "/upload_image", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImage(@RequestParam("img") MultipartFile file) {

        return null;
    }
}
