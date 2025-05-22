package com.zele.crasly_v2.models.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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