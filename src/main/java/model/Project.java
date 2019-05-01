package model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Table(name = "projects")
public class Project extends BaseEntity {
    @Column
    private String name;
    @Column
    private BigDecimal budget;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "teams_projects", joinColumns = {
            @JoinColumn(name = "team_id", insertable = false, updatable = false, nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "project_id", insertable = false, nullable = false, updatable = false)})
    private Set<Team> teams ;

}
