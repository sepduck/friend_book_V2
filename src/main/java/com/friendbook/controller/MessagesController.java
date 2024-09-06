package com.friendbook.controller;

import com.friendbook.dto.MessagesDTO;
import com.friendbook.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessagesController extends BaseReponse {
    @Autowired
    private MessagesService messagesService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> sendMessage(@PathVariable("userId") int userId, @RequestBody MessagesDTO messagesDTO) {
        try {
            return getResponseEntity(messagesService.saveMessage(userId, messagesDTO));
        }catch (Exception e) {
            return getErrorResponseEntity("Error while adding messages", 500);
        }
    }
    @DeleteMapping("/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable("messageId") int messageId) {
        try {
            messagesService.deleteMessage(messageId);
            return getResponseEntity("Message deleted successfully");
        }catch (Exception e) {
            return getErrorResponseEntity("Error while deleting message", 500);
        }
    }
}
