package com.nju.software.Token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nju.software.Bean.User;
import com.nju.software.Model.UserModel;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/05/下午1:02
 * @Description:
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getId()).withSubject(user.getUsername())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}


