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
@Table(name = "users")
public class User extends BaseEntity {
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String specialty;



    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_skills", joinColumns = {
            @JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "skill_id", insertable = false, nullable = false, updatable = false)})
    private Set<Skill>skills ;



}
