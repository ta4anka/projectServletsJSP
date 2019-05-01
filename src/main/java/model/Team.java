package model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Table(name = "teams")

public class Team extends BaseEntity {
    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "teams_users", joinColumns = {
            @JoinColumn(name = "team_id", insertable = false, updatable = false, nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", insertable = false, nullable = false, updatable = false)})
    private Set<User> users ;
}
