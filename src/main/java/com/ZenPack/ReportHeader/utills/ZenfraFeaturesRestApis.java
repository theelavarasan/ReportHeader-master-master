package com.ZenPack.ReportHeader.utills;

import com.ZenPack.ReportHeader.dao.UserDao;
import com.ZenPack.ReportHeader.security.UserCreateService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;

@Service
public class ZenfraFeaturesRestApis {
    @Autowired
    CommonFunctions functions;

    @Autowired
    UserCreateService service;

    @Autowired
    UserDao userDao;

    public JSONObject login(String email, String password) throws ParseException {

        org.json.simple.JSONObject json = new org.json.simple.JSONObject();

        try {
            json = loginToken(email, password);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            String ex = errors.toString();
            ExceptionHandlerMail.errorTriggerMail(ex);
        }
        return json;
    }

    HttpHeaders createHeaders(String token) {
        return new HttpHeaders() {
            {
                if (token != null) {
                    set("Authorization", token);
                }
                setContentType(MediaType.APPLICATION_JSON);
            }
        };
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    public org.json.simple.JSONObject login(String username) throws ParseException {

        org.json.simple.JSONObject jsonObject = new org.json.simple.JSONObject();
        Object obj = service.getUserByEmail(username);
        jsonObject = functions.convertEntityToJsonObject(obj);

        return jsonObject;

    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject loginToken(String username, String password) throws ParseException {
        org.json.simple.JSONObject jsonObject = new org.json.simple.JSONObject();
        Object object = userDao.login(username, password);
        jsonObject = functions.convertEntityToJsonObject(object);

        return jsonObject;

    }
}
