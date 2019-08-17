package com.example.taxi.domain.dispatch;

import com.example.taxi.common.BaseJpaModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class Dispatch extends BaseJpaModel {

    @Id
    @GeneratedValue
    private Long id;

    @Length(max = 100, message = "Address must be 100 character in length")
    private String address;
    private String assignDriver;

    @Enumerated(EnumType.STRING)
    private DriverStatus driverStatus;
    private LocalDateTime requestAt;
    private LocalDateTime completedAt;

    @Builder
    public Dispatch(String address) {
        this.address = address;
        this.driverStatus = DriverStatus.WAITING;
        this.requestAt = LocalDateTime.now();
    }

    public Dispatch() {
    }

}
