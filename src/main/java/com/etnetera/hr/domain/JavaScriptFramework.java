package com.etnetera.hr.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

/**
 * Entity describing basic properties of every JavaScript framework.
 *
 * @author Etnetera
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class JavaScriptFramework {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @NotNull
    @Column(name = "version", nullable = false)
    @ElementCollection(fetch = EAGER)
    private Set<String> versions = new HashSet<>();

    @Column
    private Date deprecationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HypeLevelEnum hypeLevel;

    @Override
    public String toString() {
        return "JavaScriptFramework [id=" + id + ", " +
                "name=" + name + ", " +
                "versions=" + versions + ", " +
                "deprecation date=" + deprecationDate + ", " +
                "hype leve=" + hypeLevel + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        return Objects.equals(id, ((JavaScriptFramework) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
