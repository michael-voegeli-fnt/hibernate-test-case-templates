package org.hibernate.bugs;

import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Entity
public class TaskEntity {

    @Id
    @Column
    private int id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "T_LOCALIZED_LABEL", joinColumns = @JoinColumn(name = "OBJECT_UUID"))
    @Where(clause = "IDENTIFIER ='task:name'")
    private Set<LocalizedLabel> name = new CopyOnWriteArraySet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "T_LOCALIZED_LABEL", joinColumns = @JoinColumn(name = "OBJECT_UUID"))
    @Where(clause = "IDENTIFIER ='task:type'")
    private Set<LocalizedLabel> type = new CopyOnWriteArraySet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<LocalizedLabel> getName() {
        return name;
    }

    public void setName(Set<LocalizedLabel> name) {
        this.name = name;
    }

    public Set<LocalizedLabel> getType() {
        return type;
    }

    public void setType(Set<LocalizedLabel> type) {
        this.type = type;
    }
}
