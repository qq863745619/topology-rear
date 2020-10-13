package com.nju.software.Controller;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.nju.software.Bean.ReportList;
import com.nju.software.Token.UserLoginToken;
import com.nju.software.service.ReportListService;
import com.nju.software.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import com.auth0.jwt.JWT;

@Controller
@RequestMapping("report_list")
public class ReportListController {
    @Autowired
    public ReportListService reportListService;


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/get/{id}")
    public ReportList findReportListByTopologyId(@PathVariable(name = "id") String id){
        return reportListService.findReportListById(id);
    }

    @CrossOrigin
    @UserLoginToken
    @ResponseBody
    @RequestMapping(value = "/save")
    public Map<String,Object> save(@RequestBody ReportList reportList, HttpServletRequest httpServletRequest){

        String userId = reportList.getUserId();//第一次保存时userId会为空，
        if(userId==""){
            String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
            // 获取 token 中的 user id
            try {
                userId = JWT.decode(token).getAudience().get(0);
                reportList.setUserId(userId);
            } catch (JWTDecodeException j) {
                throw new RuntimeException("401");
            }
        }
        reportListService.save(reportList);

        Map<String,Object> sMap = new HashMap<>();
        sMap.put("id",reportList.getId());
        return sMap;

    }
}
