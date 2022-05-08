package com.avantys.user.cmd.domain;

import com.avantys.cqrs.core.domain.BaseEntity;
import com.avantys.user.common.dto.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student extends BaseEntity {
    @Id
    private String studentId;
    private boolean isAccepted;
    private Boolean isAssessed;
    private PaymentMethod paymentMethod;
}
