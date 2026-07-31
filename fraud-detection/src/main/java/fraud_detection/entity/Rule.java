package fraud_detection.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_id")
    private Long ruleId;

    @Column(nullable = false)
    private String ruleName;

    @Column(nullable = false)
    private Double thresholdAmount;

    @Column(nullable = false)
    private String severity;

    @Column(nullable = false)
    private Boolean active;
}