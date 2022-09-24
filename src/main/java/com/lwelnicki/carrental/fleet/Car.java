package com.lwelnicki.carrental.fleet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars")
@Getter
class Car {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private String make;
    private String model;
}
