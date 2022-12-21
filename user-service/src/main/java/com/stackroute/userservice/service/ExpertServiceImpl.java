package com.stackroute.userservice.service;

import com.stackroute.userservice.config.MessageConfiguration;
import com.stackroute.userservice.dao.ExpertRepository;
import com.stackroute.userservice.dto.Expert;
import com.stackroute.userservice.exception.ExpertNotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertServiceImpl implements ExpertService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ExpertRepository expertRepo;

    @Override
    public Expert createNewExpert(Expert expert) {

        rabbitTemplate.convertAndSend(MessageConfiguration.EXPERTMESSAGE_EXCHANGE,
                MessageConfiguration.EXPERTROUTING_KEY,expert);
        System.out.println("succesfull");
             return this.expertRepo.save(expert);

    }

    @Override
    public Expert updateExpert(Expert expert) {
        return this.expertRepo.save(expert);

    }

    @Override
    public Expert getExpertByuserEmail(String userEmail) throws ExpertNotFoundException {
        Expert expert = this.expertRepo.findExpertByUserEmail(userEmail);
        if (expert == null )
            throw new ExpertNotFoundException("Expert not found with userEmail:" + userEmail);
        return expert;

    }
}