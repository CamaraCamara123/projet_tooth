package com.tooth.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.tooth.domain.StudentPW} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StudentPWDTO implements Serializable {

    private Long id;

    private String time;

    @Lob
    private byte[] imageFront;

    private String imageFrontContentType;

    @Lob
    private byte[] imageSide;

    private String imageSideContentType;
    private Float angleLeft;

    private Float angleRigth;

    private Float angleCenter;

    private Instant date;

    private StudentDTO student;

    private PWDTO pw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public byte[] getImageFront() {
        return imageFront;
    }

    public void setImageFront(byte[] imageFront) {
        this.imageFront = imageFront;
    }

    public String getImageFrontContentType() {
        return imageFrontContentType;
    }

    public void setImageFrontContentType(String imageFrontContentType) {
        this.imageFrontContentType = imageFrontContentType;
    }

    public byte[] getImageSide() {
        return imageSide;
    }

    public void setImageSide(byte[] imageSide) {
        this.imageSide = imageSide;
    }

    public String getImageSideContentType() {
        return imageSideContentType;
    }

    public void setImageSideContentType(String imageSideContentType) {
        this.imageSideContentType = imageSideContentType;
    }

    public Float getAngleLeft() {
        return angleLeft;
    }

    public void setAngleLeft(Float angleLeft) {
        this.angleLeft = angleLeft;
    }

    public Float getAngleRigth() {
        return angleRigth;
    }

    public void setAngleRigth(Float angleRigth) {
        this.angleRigth = angleRigth;
    }

    public Float getAngleCenter() {
        return angleCenter;
    }

    public void setAngleCenter(Float angleCenter) {
        this.angleCenter = angleCenter;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public PWDTO getPw() {
        return pw;
    }

    public void setPw(PWDTO pw) {
        this.pw = pw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentPWDTO)) {
            return false;
        }

        StudentPWDTO studentPWDTO = (StudentPWDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, studentPWDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StudentPWDTO{" +
            "id=" + getId() +
            ", time='" + getTime() + "'" +
            ", imageFront='" + getImageFront() + "'" +
            ", imageSide='" + getImageSide() + "'" +
            ", angleLeft=" + getAngleLeft() +
            ", angleRigth=" + getAngleRigth() +
            ", angleCenter=" + getAngleCenter() +
            ", date='" + getDate() + "'" +
            ", student=" + getStudent() +
            ", pw=" + getPw() +
            "}";
    }
}
