package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.service.OrderService;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping("/orders")
    private Orders createOrder(@RequestBody OrderInputDTO dto) throws AddressException, MessagingException, IOException{
        Orders createdOrder = service.createOrder(dto);
        sendMail(createdOrder);
        return createdOrder;
    }
    private void sendMail(Orders order) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("pentruOrdere@gmail.com", "confirmation");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("mailuAlaBlanao", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("marianmihai11@yahoo.com"));
        msg.setSubject("Order Confirmation");
        msg.setContent("Order Id: " + order.getOrderId() + "\nDeliveryAddress: " + order.getAddress(), "text/html");
        msg.setSentDate(new Date());
        Transport.send(msg);
    }



































}
