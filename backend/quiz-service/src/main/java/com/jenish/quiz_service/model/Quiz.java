package com.jenish.quiz_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    @ElementCollection
    private List<Integer> questionIds;
    private String title;
    private Integer allottedTimeInMinutes;

    @PrePersist
    public void generateCode() {
        if (this.code == null) {
            this.code = UUID.randomUUID().toString()
                    .replace("-", "")
                    .substring(0, 6)
                    .toUpperCase();
        }
    }

}
