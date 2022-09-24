package com.lwelnicki.carrental.rental.car.infrastructure;

import com.lwelnicki.carrental.rental.car.domain.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars")
@Getter
class CarEntity {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private CarStatus status;
    @Column(name = "luxury")
    private boolean isLuxury;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID rentierId;
}
