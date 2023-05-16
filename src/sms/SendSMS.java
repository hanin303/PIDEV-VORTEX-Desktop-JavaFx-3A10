/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sms;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entity.Ticket;

/**
 *
 * @author MSI
 */


public class SendSMS {
    
    
    public static final String ACCOUNT_SID = System.getenv("AC0566d87dfe2bc5a33101670c333564b0");
    public static final String AUTH_TOKEN = System.getenv("10fed356d3e8897a61393bc95e67a60f");

    public static void sendSMS(Ticket t) {
        Twilio.init("AC0566d87dfe2bc5a33101670c333564b0", "10fed356d3e8897a61393bc95e67a60f");
        Message message = Message.creator(new PhoneNumber("+21654891319"),
                new PhoneNumber("+15673131185"), "Ticket ajouté : Bonjour Hanin votre paiement est effectuer avec succés ").create();

        System.out.println(message.getSid());
    }
    
}
