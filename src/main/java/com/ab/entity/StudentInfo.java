package com.ab.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ListIndexBase;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Table(name = "STUDENT_COLLECTION_ANNO")
public class StudentInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sno;
    @Column(length = 20)
    private String sname;
    @ElementCollection
    @JoinTable(name = "ANNO_STUDENT_FRIENDS")
    @JoinColumn(name = "STUD_ID",referencedColumnName = "sno") // To create foreign key coloum
    @OrderColumn(name = "FRIEND_INDEX")
    @ListIndexBase(value = 1)
    @Column(name = "FRIEND")
    private List<String>friends;

    @ElementCollection
    @JoinTable(name = "ANNO_STUDENT_VISITED_PLACES")
    @JoinColumn(name = "STUD_V_ID", referencedColumnName = "sno")
    @Column(name = "VISITED_PLACE")
    private List<String>visitedPlaces;
    @ElementCollection
    @JoinTable(name = "ANNO_STUDENT_PHONE_NUMBERS")
    @JoinColumn(name = "STUD_P_NUMBER", referencedColumnName = "sno")
    @Column(name = "PHONE_NUMBER")
    private Set<String>phoneNumbers;
    @ElementCollection
    @JoinTable(name = "ANNO_STUDENT_CERTIFICATES")
    @JoinColumn(name = "STUD_ID_CERTIFICATES", referencedColumnName = "sno")
    @MapKeyColumn(name = "CERTIFICATE_TYPE",length = 20)
    @Column(name = "ID_CERTIFICATES")
    private Map<String,Integer>idCertificates;
}
