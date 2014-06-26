package com.edu.tpc.service;

/**
 *
 * @author Jaikishan
 */
public interface EmailService {
     public void sendEMail(String sender, String[]recepient, String subject, String message) throws Exception;
}
