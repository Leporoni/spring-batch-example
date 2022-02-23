package com.leporonitech.springbatchexample;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Aircraft implements Serializable {

    private static final long serialVersionUID = 9058350955651091145L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String recordKey;

    @Column(nullable = false)
    private String name;

    private String iataCode;

    private String icaoCode;
}
