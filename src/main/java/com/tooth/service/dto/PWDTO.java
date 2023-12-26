package com.tooth.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.tooth.domain.PW} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PWDTO implements Serializable {

    private Long id;

    private String title;

    private String objectif;

    @Lob
    private byte[] docs;

    private String docsContentType;
    private ToothDTO tooth;

    private Set<GroupeDTO> groupes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public byte[] getDocs() {
        return docs;
    }

    public void setDocs(byte[] docs) {
        this.docs = docs;
    }

    public String getDocsContentType() {
        return docsContentType;
    }

    public void setDocsContentType(String docsContentType) {
        this.docsContentType = docsContentType;
    }

    public ToothDTO getTooth() {
        return tooth;
    }

    public void setTooth(ToothDTO tooth) {
        this.tooth = tooth;
    }

    public Set<GroupeDTO> getGroupes() {
        return groupes;
    }

    public void setGroupes(Set<GroupeDTO> groupes) {
        this.groupes = groupes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PWDTO)) {
            return false;
        }

        PWDTO pWDTO = (PWDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, pWDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PWDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", objectif='" + getObjectif() + "'" +
            ", docs='" + getDocs() + "'" +
            ", tooth=" + getTooth() +
            ", groupes=" + getGroupes() +
            "}";
    }
}
