package com.RestSecureOath.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@PreAuthorize("hasRole('ROLE_OWNER') OR hasRole('ROLE_ADMIN') AND #oauth2.hasScope('read')")
@RestController
@RequestMapping("/api")
public class Controller {

    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public Map<String, String> getResource() {
        Map<String, String> resource = new HashMap<String, String>();
        resource.put("resource", "here is some resource");
        return resource;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
