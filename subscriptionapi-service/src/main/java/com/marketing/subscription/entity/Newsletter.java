package com.marketing.subscription.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Newsletter")
@Table(name = "newsletter")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Newsletter implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String emailTemplate;

    public Newsletter(String emailTemplate) {
        this.emailTemplate = emailTemplate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Newsletter that = (Newsletter) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
