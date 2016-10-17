package com.RestSecureOath.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.RestSecureOath.domain.Owner;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@RestController
public  class StockPriceServiceImpl extends RemoteServiceServlet   {

	
	 

    @RequestMapping(value="/check")
    public List<Owner> getPrices( String[] symbols) {
        Random rnd = new Random();
        List<Owner> l = new ArrayList<>();
        l.add(new Owner("owner", "password", "email", "firstName", "lastName", 1, null,null));
        return l;
  }

}