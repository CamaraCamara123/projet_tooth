package com.tooth.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PW.
 */
@Entity
@Table(name = "pw")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PW implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "objectif")
    private String objectif;

    @Lob
    @Column(name = "docs")
    private byte[] docs;

    @Column(name = "docs_content_type")
    private String docsContentType;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "pws" }, allowSetters = true)
    private Tooth tooth;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rel_pw__groupe", joinColumns = @JoinColumn(name = "pw_id"), inverseJoinColumns = @JoinColumn(name = "groupe_id"))
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "professor", "students", "pws" }, allowSetters = true)
    private Set<Groupe> groupes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PW id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public PW title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjectif() {
        return this.objectif;
    }

    public PW objectif(String objectif) {
        this.setObjectif(objectif);
        return this;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public byte[] getDocs() {
        return this.docs;
    }

    public PW docs(byte[] docs) {
        this.setDocs(docs);
        return this;
    }

    public void setDocs(byte[] docs) {
        this.docs = docs;
    }

    public String getDocsContentType() {
        return this.docsContentType;
    }

    public PW docsContentType(String docsContentType) {
        this.docsContentType = docsContentType;
        return this;
    }

    public void setDocsContentType(String docsContentType) {
        this.docsContentType = docsContentType;
    }

    public Tooth getTooth() {
        return this.tooth;
    }

    public void setTooth(Tooth tooth) {
        this.tooth = tooth;
    }

    public PW tooth(Tooth tooth) {
        this.setTooth(tooth);
        return this;
    }

    public Set<Groupe> getGroupes() {
        return this.groupes;
    }

    public void setGroupes(Set<Groupe> groupes) {
        this.groupes = groupes;
    }

    public PW groupes(Set<Groupe> groupes) {
        this.setGroupes(groupes);
        return this;
    }

    public PW addGroupe(Groupe groupe) {
        this.groupes.add(groupe);
        return this;
    }

    public PW removeGroupe(Groupe groupe) {
        this.groupes.remove(groupe);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PW)) {
            return false;
        }
        return getId() != null && getId().equals(((PW) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PW{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", objectif='" + getObjectif() + "'" +
            ", docs='" + getDocs() + "'" +
            ", docsContentType='" + getDocsContentType() + "'" +
            "}";
    }
}
