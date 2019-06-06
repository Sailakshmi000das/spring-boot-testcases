package com.stackroute.muzixservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Track {

    @Id
    @Column(name="trackid")
    private int trackId;

    @Column(name="trackname")
    private String trackName;

    @Column(name="trackcomments")
    private String trackComments;
}
