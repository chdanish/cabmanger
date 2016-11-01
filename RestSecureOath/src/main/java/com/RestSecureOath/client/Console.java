package com.RestSecureOath.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.RestSecureOath.client.domain.LDriver;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Response;

public class Console<T>{

	/**
	 * 
	 */
	public Console() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public native static void print(Object msg) /*-{
	  console.log(msg);
	}-*/;
	
	public native static void log(String msg) /*-{
	  console.log(msg);
	}-*/;
	
	
	 public static <T extends JavaScriptObject> void log(Response response){
		 print(JsonUtils.<JsArray<T>>safeEval(response.getText()));		 
	 };
	 
	 //Only work if data recieved is list or array 
	 //this will not work for HATAUS
	 public <T extends JavaScriptObject> JsArray<T> deserializablelist(Response response){
		 JsArray<T> data=JsonUtils.<JsArray<T>>safeEval(response.getText());
		 print(JsonUtils.<JsArray<T>>safeEval(response.getText()));
		 
		/* List<T> list = new ArrayList<>();
		 for(int i=0;i<=data.length();i++){
			 //list.add(data.get(i));
			 print(i);
			 print(data.get(i));
		 }*/
		return data;		 
	 }

	 public static final native <T extends JavaScriptObject> T buildCustomer(String json) /*-{
	    return eval('(' + json + ')');
	}-*/;

	

}