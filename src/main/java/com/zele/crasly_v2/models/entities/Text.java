package com.zele.crasly_v2.models;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Text {
    private String content;
    //TODO Voice Message
    //TODO File Attachment
    //TODO File Attachment
}