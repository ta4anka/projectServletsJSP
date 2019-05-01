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
@Table(name = "customers")
public class Customer extends BaseEntity{
    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "customers_projects", joinColumns = {
            @JoinColumn(name = "customer_id", insertable = false, updatable = false, nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "project_id", insertable = false, nullable = false, updatable = false)})
    private  Set<Project> projects ;
}
