package ca.gbc.notificationmicroservices.service;

import ca.gbc.notificationmicroservices.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        log.info("Received message from order-placed topic {}", orderPlacedEvent);

        //send email to costumer
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("qinxi.liu@georgebrown.ca");
            messageHelper.setTo(orderPlacedEvent.getEmail());
            messageHelper.setSubject(String.format("Your Order (%s) was placed successfully",
                    orderPlacedEvent.getOrderNumber()));
            messageHelper.setText(String.format("""
                    Good Day %s %s,
                    
                    Your order with order number %s was successfully placed 
                    
                    Thank-you for your business
                    COMP3095 Qinxi
                    """, orderPlacedEvent.getFirstName(), orderPlacedEvent.getLastName(),orderPlacedEvent.getOrderNumber()
            ));
        };

        try {

            javaMailSender.send(messagePreparator);
            log.info("Order notification successfully sent!!");

        }catch (MailException e){
            log.error("Exception occurred when sending email", e);
            throw new RuntimeException("Exception occurred when attempting to send email", e);
        }
    }
}


