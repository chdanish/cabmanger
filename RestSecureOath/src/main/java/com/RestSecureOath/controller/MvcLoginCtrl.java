/*package com.RestSecureOath.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class MvcLoginCtrl {

	@RequestMapping(value = "/login")
	 public ModelAndView getBlog(ModelAndView mv,HttpServletRequest request,HttpServletResponse response) {
		
		CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		// Spring Security will allow the Token to be included in this header name
		response.setHeader("X-CSRF-HEADER", token.getHeaderName());
		// Spring Security will allow the token to be included in this parameter name
		response.setHeader("X-CSRF-PARAM", token.getParameterName());
		// this is the value of the token to be included as either a header or an HTTP parameter
		response.setHeader("X-CSRF-TOKEN", token.getToken());
	    mv.addObject("_csrf", token.getToken());
	    System.out.println(token.getToken());
	    mv.setViewName("login");
	    return mv;
	}
}
*/