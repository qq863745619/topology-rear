package com.nju.software.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.nju.software.Bean.User;
import com.nju.software.Bean2.CmsUser;
import com.nju.software.service.UserService;
import com.nju.software.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private UUID uuid;

    //测试首次前端的profile请求,这是当浏览器保存的token,自动登录
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/profile")
    public User profile(HttpServletRequest httpServletRequest){
        String userId;
        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        // 获取 token 中的 user id
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        CmsUser cmsUser = userService.findCmsUserById(Long.valueOf((userId)));
        User user = new User(cmsUser);
        user.setPassword("");
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/getUser")
    public User findUserById(String id){
        CmsUser cmsUser = userService.findCmsUserById(Long.valueOf((id)));
        User user = new User(cmsUser);
        user.setPassword("");
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/getId")
    public Map<String,Object> getUser(){
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("id","1");
        return sMap;
    }

    @ResponseBody
    @RequestMapping(value = "/addUser")
    public Map<String,String > addUser(@RequestBody User user){
        user.setId(uuid.getUUID());
        String id = userService.save(user);
        Map<String,String> sMap = new HashMap<>();
        sMap.put("id",id);
        return sMap;
    }
}
