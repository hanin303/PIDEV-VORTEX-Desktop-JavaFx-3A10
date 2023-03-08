/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms;

/**
 *
 * @author hanin
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entity.Ticket;

public class SendSMS {
    
    
    public static final String ACCOUNT_SID = System.getenv("AC0566d87dfe2bc5a33101670c333564b0");
    public static final String AUTH_TOKEN = System.getenv("bffb84d60bb901344ed03befcbf2ff5f");

    public static void sendSMS(Ticket t) {
        Twilio.init("AC0566d87dfe2bc5a33101670c333564b0", "bffb84d60bb901344ed03befcbf2ff5f");
        Message message = Message.creator(new PhoneNumber("+21654891319"),
                new PhoneNumber("+15673131185"), "Ticket ajouté : votre paiement est effectuer avec succés ").create();

        System.out.println(message.getSid());
    }
    
}
