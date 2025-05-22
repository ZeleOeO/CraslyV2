package com.zele.crasly_v2.models.dto.chatmessage;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatMessageViewDTO {
    public Long id;
    public String chatName;
    public String senderName;
    public String chatMessage;
    public HashMap<String, String> replies;
    public String timestamp;
}
