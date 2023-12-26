package com.tooth.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A StudentPW.
 */
@Entity
@Table(name = "student_pw")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StudentPW implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time")
    private String time;

    @Lob
    @Column(name = "image_front")
    private byte[] imageFront;

    @Column(name = "image_front_content_type")
    private String imageFrontContentType;

    @Lob
    @Column(name = "image_side")
    private byte[] imageSide;

    @Column(name = "image_side_content_type")
    private String imageSideContentType;

    @Column(name = "angle_left")
    private Float angleLeft;

    @Column(name = "angle_rigth")
    private Float angleRigth;

    @Column(name = "angle_center")
    private Float angleCenter;

    @Column(name = "date")
    private Instant date;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "user", "groupe" }, allowSetters = true)
    private Student student;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "tooth", "groupes" }, allowSetters = true)
    private PW pw;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public StudentPW id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return this.time;
    }

    public StudentPW time(String time) {
        this.setTime(time);
        return this;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public byte[] getImageFront() {
        return this.imageFront;
    }

    public StudentPW imageFront(byte[] imageFront) {
        this.setImageFront(imageFront);
        return this;
    }

    public void setImageFront(byte[] imageFront) {
        this.imageFront = imageFront;
    }

    public String getImageFrontContentType() {
        return this.imageFrontContentType;
    }

    public StudentPW imageFrontContentType(String imageFrontContentType) {
        this.imageFrontContentType = imageFrontContentType;
        return this;
    }

    public void setImageFrontContentType(String imageFrontContentType) {
        this.imageFrontContentType = imageFrontContentType;
    }

    public byte[] getImageSide() {
        return this.imageSide;
    }

    public StudentPW imageSide(byte[] imageSide) {
        this.setImageSide(imageSide);
        return this;
    }

    public void setImageSide(byte[] imageSide) {
        this.imageSide = imageSide;
    }

    public String getImageSideContentType() {
        return this.imageSideContentType;
    }

    public StudentPW imageSideContentType(String imageSideContentType) {
        this.imageSideContentType = imageSideContentType;
        return this;
    }

    public void setImageSideContentType(String imageSideContentType) {
        this.imageSideContentType = imageSideContentType;
    }

    public Float getAngleLeft() {
        return this.angleLeft;
    }

    public StudentPW angleLeft(Float angleLeft) {
        this.setAngleLeft(angleLeft);
        return this;
    }

    public void setAngleLeft(Float angleLeft) {
        this.angleLeft = angleLeft;
    }

    public Float getAngleRigth() {
        return this.angleRigth;
    }

    public StudentPW angleRigth(Float angleRigth) {
        this.setAngleRigth(angleRigth);
        return this;
    }

    public void setAngleRigth(Float angleRigth) {
        this.angleRigth = angleRigth;
    }

    public Float getAngleCenter() {
        return this.angleCenter;
    }

    public StudentPW angleCenter(Float angleCenter) {
        this.setAngleCenter(angleCenter);
        return this;
    }

    public void setAngleCenter(Float angleCenter) {
        this.angleCenter = angleCenter;
    }

    public Instant getDate() {
        return this.date;
    }

    public StudentPW date(Instant date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentPW student(Student student) {
        this.setStudent(student);
        return this;
    }

    public PW getPw() {
        return this.pw;
    }

    public void setPw(PW pW) {
        this.pw = pW;
    }

    public StudentPW pw(PW pW) {
        this.setPw(pW);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentPW)) {
            return false;
        }
        return getId() != null && getId().equals(((StudentPW) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StudentPW{" +
            "id=" + getId() +
            ", time='" + getTime() + "'" +
            ", imageFront='" + getImageFront() + "'" +
            ", imageFrontContentType='" + getImageFrontContentType() + "'" +
            ", imageSide='" + getImageSide() + "'" +
            ", imageSideContentType='" + getImageSideContentType() + "'" +
            ", angleLeft=" + getAngleLeft() +
            ", angleRigth=" + getAngleRigth() +
            ", angleCenter=" + getAngleCenter() +
            ", date='" + getDate() + "'" +
            "}";
    }
}
