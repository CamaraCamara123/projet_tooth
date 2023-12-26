package com.tooth.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.tooth.domain.Groupe} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GroupeDTO implements Serializable {

    private Long id;

    private String code;

    private String year;

    private ProfessorDTO professor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ProfessorDTO getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorDTO professor) {
        this.professor = professor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupeDTO)) {
            return false;
        }

        GroupeDTO groupeDTO = (GroupeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, groupeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GroupeDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", year='" + getYear() + "'" +
            ", professor=" + getProfessor() +
            "}";
    }
}
