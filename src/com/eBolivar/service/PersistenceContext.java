/** @author Santiago Scalzadonna * @version 1.0 */ 
package com.eBolivar.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class PersistenceContext {

	private ApplicationContext context;
	
    public Object getBean(String bean){
    	if (context == null){
    		String[] paths = {"persistence.xml"};
        	context = new ClassPathXmlApplicationContext(paths);
    	}
    	
    	return context.getBean(bean);
    	
    }
    
}
