/*package com.RestSecureOath.config;

import org.fusesource.restygwt.client.RestService;

import com.google.gwt.core.shared.GWT;




public interface GwtInit extends RestService {
		public static class Util {
		
	
			private static GwtInit instance;
	
			public static GwtInit getService() {
				if (instance == null) {
				instance = GWT.create(GwtInit.class);
				}
	Resource resource = new Resource(GWT.getModuleBaseURL() + "service");
	        ((RestServiceProxy) instance).setResource(resource);
				return instance;
			}
		}
}
*/