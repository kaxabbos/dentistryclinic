package com.mus.model;

import com.mus.model.enums.StatusOrdering;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Ordering {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctors doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users client;
    @ManyToOne(fetch = FetchType.LAZY)
    private Notes note;
    private String date;
    private String time;
    @Enumerated(EnumType.STRING)
    private StatusOrdering statusOrdering;

    public Ordering(Doctors doctor, Users client, Notes note, String date, String time) {
        this.doctor = doctor;
        this.client = client;
        this.note = note;
        this.date = date;
        this.time = time;
        this.statusOrdering = StatusOrdering.WAITING;
    }
}
