package com.nju.software.util;

import org.springframework.stereotype.Component;

@Component
public class UUID {
    public String getUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
    public String getUUID6(){
        return java.util.UUID.randomUUID().toString().replace("-", "").toLowerCase().substring(0,6);
    }
    public String getUUID4(){
        return java.util.UUID.randomUUID().toString().replace("-", "").toLowerCase().substring(0,4);
    }
}
