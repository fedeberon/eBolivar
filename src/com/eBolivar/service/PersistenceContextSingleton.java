/** @author Santiago Scalzadonna * @version 1.0 */
package com.eBolivar.service;



public class PersistenceContextSingleton {

    private PersistenceContext persistenceContext = new PersistenceContext();

    /** singleton instance */
    private static PersistenceContextSingleton singleton = new PersistenceContextSingleton();

    public static PersistenceContextSingleton getInstance(){
        return singleton;
    }

    /*public void setPersistenceContext(PersistenceContext persistenceContext) {
        this.persistenceContext = persistenceContext;
    }*/

    public Object getBean(String key) {
        return persistenceContext.getBean(key);
    }
}