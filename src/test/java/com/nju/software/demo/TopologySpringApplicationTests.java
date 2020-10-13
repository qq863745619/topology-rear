package com.nju.software.demo;

import com.nju.software.Bean.User;
import com.nju.software.Bean2.CmsUser;
import com.nju.software.Dao.UserDao;
import com.nju.software.Dao2.CmsUserDao;
import com.nju.software.util.MD5;
import com.nju.software.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
class TopologySpringApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    UserDao userDao;

    @Autowired
    CmsUserDao cmsUserDao;
    @Autowired
    private UUID uuid;

    @Test
    public void test1(){
        User u = new User();
        u.setId(uuid.getUUID());
        u.setPhone("11111");
        u.setUsername("哈哈");
        u.setCreatedAt(new Date().getTime());
        userDao.save(u);

    }

    @Test
    public void a(){
        //Optional<CmsUser> user = cmsUserDao.findById(Long.valueOf(1));
        //user.get();
      //  System.out.println(user);
        System.out.println(decrypt("A24AMA==","DRGY"));
        //B2pVZQ==

    }




    public static  String decrypt(String text,String key){

        //org.apache.commons.codec.binary
        try {
            final Base64 base64 = new Base64();
            String text_new = bind_key(new String(base64.decode(text), "UTF-8"), key);
            int StrLen = text_new.length();
            String temp = "";
            for(int i = 0;i<StrLen;i++)
            {
                temp+=text_new.charAt(i)^text_new.charAt(++i);
            }
            return temp;
        }
        catch (Exception e){

        }

        return "";
    }
    public static String bind_key(String str,String key)  {
        try {
            String encrypt_key = MD5.getMD5(key);
            String temp = "";
            int strLen = str.length();

            for(int i = 0,j=0;i<strLen;i++,j++){
                j = j==32?0:j;
                temp += str.charAt(i) ^ encrypt_key.charAt(j);
            }

            return temp;
        }catch (Exception e){

        }
        return "";
    }

/*
function bind_key($str, $key){
    $encrypt_key = md5($key);

    $tmp = '';
    $strLen = strlen($str);
    for($i=0, $j=0; $i<$strLen; $i++, $j++){
        $j = $j == 32 ? 0 : $j;
        $tmp .= $str[$i] ^ $encrypt_key[$j];
    }
    return $tmp;
}
* */

    /*
    function decrypt($str, $key){
    $str = bind_key(base64_decode($str), $key);
    $strLen = strlen($str);
    $tmp = '';
    for($i=0; $i<$strLen; $i++){
        $tmp .= $str[$i] ^ $str[++$i];
    }
    return $tmp;
}

    * */


}
