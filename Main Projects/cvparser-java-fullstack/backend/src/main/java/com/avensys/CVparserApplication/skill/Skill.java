package com.avensys.CVparserApplication.skill;

import com.avensys.CVparserApplication.resume.Resume;
import jakarta.persistence.*;

@Entity(name = "Skill")
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "resume_id", referencedColumnName = "id")
    private Resume resume;

    public Skill() {
    }

    public Skill(String name) {
        this.name = name;
    }

    public Skill(long id, String name, Resume resume) {
        this.id = id;
        this.name = name;
        this.resume = resume;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }


}
