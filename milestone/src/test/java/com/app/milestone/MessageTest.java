package com.app.milestone;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MessageTest {
    @Test
    public void sendSms() {

        String api_key = "NCSLQBPIMCSX9XLQ";
        String api_secret = "XQ58SUAXPX1GWVARAUZW803YC5YS4B32";
        Message coolsms = new Message(api_key, api_secret);
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("to", "01021208515");
        params.put("from", "01021208515");
        params.put("type", "SMS");
        params.put("text", "테스트");
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            log.info(obj.toString());
        } catch (CoolsmsException e) {
            log.info(e.getMessage());
            log.info(e.getCode()+"");
        }
    }
}
