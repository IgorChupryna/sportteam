package entity;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="TOOL")

public class Tool implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOOL_ID")
    private Long id;

    @Column(name="NAME",length = 32)
    private String name;

    public Tool() {
    }

    public Tool(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}