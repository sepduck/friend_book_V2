package com.friendbook.service;

import com.friendbook.dto.MessagesDTO;
import com.friendbook.model.Messages;

public interface MessagesService {
    Messages saveMessage(long userId, MessagesDTO messagesDTO);

    void deleteMessage(long messageId);
}
