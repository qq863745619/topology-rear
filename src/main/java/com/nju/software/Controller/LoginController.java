package com.nju.software.Controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.nju.software.Bean.User;
import com.nju.software.Bean2.CmsUser;
import com.nju.software.Token.TokenService;
import com.nju.software.Token.UserLoginToken;
import com.nju.software.service.UserService;
import com.nju.software.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/05/下午3:24
 * @Description:
 */
@RestController
//@RequestMapping("api")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @RequestMapping("/")
    public String  index(Model model){
        return "index";
    }
    //登录

    @CrossOrigin
    @PostMapping("/verify")
    public Object verify(@RequestBody JSONObject jsonObject) throws JSONException {
       // String token = jsonObject.get("token").toString();
       // String userId = JWT.decode(token).getAudience().get(0);
        System.out.println(jsonObject);
        String token = jsonObject.get("token").toString();
        try{
        token = URLDecoder.decode(token, "GBK");}catch (Exception e){

        }
        //System.out.println("+++"+token);
        String key = "\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0";

        //解密
        token = token.replaceAll(" ","+");
        String userId = Security.decrypt(token,key);

        //System.out.println("____"+userId);
        CmsUser user = userService.findCmsUserById(Long.valueOf(userId.replaceAll("\\s*", "").trim()));
        JSONObject jsonObject_temp = new JSONObject();
        if(user == null) {
            jsonObject_temp.put("message","登录失败,用户不存在");
            return jsonObject_temp;
        }else{
            User temp_user = new User(user);


            temp_user.setPassword(user.getPassword());
            String temp_token = tokenService.getToken(temp_user);
            jsonObject.put("token", temp_token);
            temp_user.setPassword("");
            jsonObject.put("user", temp_user);
            return jsonObject;
        }
      //  return null;
    }

    //正式部署前  下面函数也要修改  使用cmsuser表查询，然后转换成user表
    @CrossOrigin
    @PostMapping("/login")
    public Object login(@RequestBody JSONObject jsonObject1) throws JSONException {
        String phone = jsonObject1.get("phone").toString();
        String password = jsonObject1.get("password").toString();
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findUserByPhone(phone);
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!userForBase.getPassword().equals(password)){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
