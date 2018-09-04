package com.stackroute.tldm.controller;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.model.MessageResponse;
import com.stackroute.tldm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {


    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/response")
    public MessageResponse messageResponse(Message message) throws Exception {
        messageService.saveMessage(message);
        return new MessageResponse(message.getMessageContent());
    }

    @DeleteMapping("/api/v1/message/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable("messageId") String m_id) {
        ResponseEntity<?> responseEntity;
        try {
            if (messageService.deleteMessage(m_id)) {
                responseEntity = new ResponseEntity(HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (MessageNotFoundException e) {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("/api/v1/message/{senderId}/{recieverId}")
    public ResponseEntity<?> getMessagesByUserAndReciever(@PathVariable("senderId") String senderId, @PathVariable("recieverId") String recieverId) {
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(messageService.getMessagesByUserIdAndRecieverId(senderId, recieverId), HttpStatus.OK);
        } catch (MessageNotFoundException e) {
            responseEntity = new ResponseEntity<>("Message Not Found!", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
