package com.friendbook.service.impl;

import com.friendbook.dto.MessagesDTO;
import com.friendbook.model.Messages;
import com.friendbook.model.Users;
import com.friendbook.repository.MessagesRepository;
import com.friendbook.service.AuthenticationService;
import com.friendbook.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagesServiceImpl extends BaseService implements MessagesService {
    @Autowired
    private MessagesRepository messagesRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Override
    public Messages saveMessage(long userId, MessagesDTO messagesDTO) {
        Users sender = getCurrentUser(authenticationService);
        Users receiver = authenticationService.getFindByUserId(userId);
        Messages messages = new Messages();
        messages.setSenderId(sender);
        messages.setReceiverId(receiver);
        messages.setContent(messagesDTO.getContent());
        return messagesRepository.save(messages);
    }

    @Override
    public void deleteMessage(long messageId) {
        messagesRepository.deleteById(messageId);
    }
}
