package com.foodminder.FoodMinder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class RabbitMQService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void enviaMensagem(String nomeFila, Object mensagem) {
        this.rabbitTemplate.convertAndSend(nomeFila, mensagem);
    }
}
