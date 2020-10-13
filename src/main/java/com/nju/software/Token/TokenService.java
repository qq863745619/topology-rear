package com.nju.software.Token;

import com.nju.software.Bean.User;
import com.nju.software.Model.UserModel;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/05/下午1:05
 * @Description:
 */
public interface TokenService {
    public String getToken(User user);
}
