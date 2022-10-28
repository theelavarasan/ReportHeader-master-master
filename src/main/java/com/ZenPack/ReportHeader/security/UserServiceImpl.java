package com.ZenPack.ReportHeader.security;

import com.ZenPack.ReportHeader.utills.ZenfraFeaturesRestApis;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    ZenfraFeaturesRestApis featureApi;

    @SuppressWarnings("unchecked")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JSONObject userObject = new JSONObject();
        try {

            
            userObject = featureApi.login(username);
            if (userObject == null) {
                throw new UsernameNotFoundException("Invalid username or password.");
            }

            return new org.springframework.security.core.userdetails.User(userObject.get("email").toString(),
                    userObject.get("password").toString(), getAuthority());

        } catch (Exception e) {
            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            String ex = errors.toString();
            ExceptionHandlerMail.errorTriggerMail(ex);
            System.out.println(e.toString());
            return null;
        }

    }
}
