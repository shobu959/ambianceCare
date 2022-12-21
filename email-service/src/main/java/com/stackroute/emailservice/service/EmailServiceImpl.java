package com.stackroute.emailservice.service;

import com.stackroute.emailservice.entity.EmailRequest;
import com.stackroute.emailservice.entity.EmailResponse;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;



@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender sender;

    @Autowired
    Configuration config;




    @Override
    public EmailResponse sendBookingMail(EmailRequest request) {
        boolean customerMailSent=false;
        boolean expertMailSent=false;

        MimeMessage mailMessage = sender.createMimeMessage();

        Map<String,Object> dataMap = new HashMap<>();

        EmailResponse response = new EmailResponse();

        dataMap.put("bookingId",request.getBookingId());
        dataMap.put("bookedDate",request.getBookedDate());
        dataMap.put("bookingSlot",request.getBookingSlot());
        dataMap.put("expertEmail",request.getExpertEmail());
        dataMap.put("customerEmail",request.getCustomerEmail());
        dataMap.put("customerAddress",request.getCustomerAddress());
        dataMap.put("pincode",request.getPincode());
        dataMap.put("bookedStatus",request.getBookedStatus());
        dataMap.put("serviceName",request.getServiceName());
        dataMap.put("serviceCost",request.getServiceCost());
        dataMap.put("cartValue",request.getCartValue());

        try {

            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());


            Template t = config.getTemplate("CustomerEmail-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, dataMap);


            helper.setTo(request.getCustomerEmail());
            helper.setText(html,true);
            helper.setSubject("AmbienceCare");
            helper.setFrom("gauravdubey024@gmail.com");
            sender.send(mailMessage);

            customerMailSent=true;


                MimeMessageHelper ExpertHelper = new MimeMessageHelper(mailMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        StandardCharsets.UTF_8.name());


                Template expertTemplate = config.getTemplate("ExpertEmail-template.ftl");
                String html1 = FreeMarkerTemplateUtils.processTemplateIntoString(expertTemplate, dataMap);


                ExpertHelper.setTo(request.getExpertEmail());
                ExpertHelper.setText(html1,true);
                ExpertHelper.setSubject("AmbienceCare");
                ExpertHelper.setFrom("gauravdubey024@gmail.com");
                sender.send(mailMessage);



                expertMailSent=true;

                if(customerMailSent && expertMailSent){
                    response.setMessage("mail sent to : " + request.getExpertEmail()+" and "+request.getCustomerEmail());
                    response.setStatus(Boolean.TRUE);
                }

            } catch (MailException | IOException | TemplateException  | MessagingException e) {
            response.setMessage("Mail Sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

        return response;


    }

    public EmailResponse sendCancellationMail(EmailRequest request){
        MimeMessage mailMessage = sender.createMimeMessage();
        boolean expertEmail=false;
        boolean customerEmail=false;

        Map<String,Object> dataMap = new HashMap<>();

        EmailResponse response = new EmailResponse();

        dataMap.put("bookingId",request.getBookingId());
        dataMap.put("bookedDate",request.getBookedDate());
        dataMap.put("bookingSlot",request.getBookingSlot());

        dataMap.put("customerAddress",request.getCustomerAddress());
        dataMap.put("pincode",request.getPincode());
        dataMap.put("bookedStatus",request.getBookedStatus());
        dataMap.put("serviceName",request.getServiceName());
        dataMap.put("serviceCost",request.getServiceCost());
        dataMap.put("cartValue",request.getCartValue());
        try {

            MimeMessageHelper ExpertHelper = new MimeMessageHelper(mailMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Template t = config.getTemplate("Cancellation-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, dataMap);


            ExpertHelper.setTo(request.getCustomerEmail());
            ExpertHelper.setText(html,true);
            ExpertHelper.setSubject("AmbienceCare");
            ExpertHelper.setFrom("gauravdubey024@gmail.com");
            sender.send(mailMessage);


            customerEmail=true;

            ExpertHelper.setTo(request.getExpertEmail());

            sender.send(mailMessage);
            expertEmail=true;

            if(customerEmail && expertEmail){
                response.setStatus(Boolean.TRUE);
                response.setMessage("mail sent to : " + request.getCustomerEmail()+" and "+request.getExpertEmail());

            }

        } catch (MailException | IOException | TemplateException  | MessagingException e) {
            response.setMessage("Mail Sending failure : "+e.getMessage());
            response.setStatus(Boolean.FALSE);
        }
        return response;

    }
    public EmailResponse sendRescheduleMail(EmailRequest request){
        MimeMessage mailMessage = sender.createMimeMessage();
        boolean expertEmail=false;
        boolean customerEmail=false;

        Map<String,Object> dataMap = new HashMap<>();

        EmailResponse response = new EmailResponse();

        dataMap.put("bookingId",request.getBookingId());
        dataMap.put("bookedDate",request.getBookedDate());
        dataMap.put("bookingSlot",request.getBookingSlot());
        //dataMap.put("expertEmail",request.getExpertEmail());
        dataMap.put("customerAddress",request.getCustomerAddress());
        dataMap.put("pincode",request.getPincode());
        dataMap.put("bookedStatus",request.getBookedStatus());
        dataMap.put("serviceName",request.getServiceName());
        dataMap.put("serviceCost",request.getServiceCost());
        dataMap.put("cartValue",request.getCartValue());
        try {

            MimeMessageHelper ExpertHelper = new MimeMessageHelper(mailMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());


            Template t = config.getTemplate("RescheduleMail-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, dataMap);


            ExpertHelper.setTo(request.getCustomerEmail());
            ExpertHelper.setText(html,true);
            ExpertHelper.setSubject("AmbienceCare");
            ExpertHelper.setFrom("gauravdubey024@gmail.com");
            sender.send(mailMessage);


            customerEmail=true;

            ExpertHelper.setTo(request.getExpertEmail());

            sender.send(mailMessage);
            expertEmail=true;

            if(customerEmail && expertEmail){
                response.setStatus(Boolean.TRUE);
                response.setMessage("mail sent to : " + request.getCustomerEmail()+" and "+request.getExpertEmail());

            }

        } catch (MailException | IOException | TemplateException  | MessagingException e) {
            response.setMessage("Mail Sending failure : "+e.getMessage());
            response.setStatus(Boolean.FALSE);
        }
        return response;
    }
}
