package com.example.taxi.domain.dispatch;

import com.example.taxi.common.BaseJpaModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.internal.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class Dispatch extends BaseJpaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max = 100, message = "Address must be 100 character in length")
    private String address;
    private String assignDriver;

    @Enumerated(EnumType.STRING)
    private DriverStatus driverStatus;
    private LocalDateTime requestAt;
    private LocalDateTime completedAt;

    @Builder(builderMethodName = "ByCreateBuilder")
    public Dispatch(String address) {
        Assert.notNull(address, "address must not be null");
        this.address = address;
        this.driverStatus = DriverStatus.WAITING;
        this.requestAt = LocalDateTime.now();
    }

    public Dispatch() {
    }

}
