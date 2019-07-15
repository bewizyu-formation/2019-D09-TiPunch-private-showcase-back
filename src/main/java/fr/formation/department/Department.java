package fr.formation.department;

import fr.formation.artistdetail.ArtistDetail;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Type of Department
 */
@Entity
@Table(name="department")
public class Department {

    @Id
    private Long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="code")
    @NotNull
    private String code;

    @Column(name="name_uppercase")
    private String nameUppercase;

    @Column(name="slug")
    private String slug;

    @Column(name="name_soundex")
    private String nameSoundex;

    public Department(){}

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameUppercase() {
        return nameUppercase;
    }

    public void setNameUppercase(String nameUppercase) {
        this.nameUppercase = nameUppercase;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getNameSoundex() {
        return nameSoundex;
    }

    public void setNameSoundex(String nameSoundex) {
        this.nameSoundex = nameSoundex;
    }
}
