package rw.productant.v1.common.entities;

import lombok.Getter;
import lombok.Setter;
import rw.productant.v1.common.audits.TimeStampAudit;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity extends TimeStampAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
}
