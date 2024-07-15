package com.example.audition.domain.mento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_memto_01")
@Entity
public class Mento {

    @Id @Column(length = 4,nullable = false)
    private String mento_id;

    @Column(length = 20)
    private String mento_name;
}
