package com.tooth.domain;

import static com.tooth.domain.GroupeTestSamples.*;
import static com.tooth.domain.StudentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.tooth.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Student.class);
        Student student1 = getStudentSample1();
        Student student2 = new Student();
        assertThat(student1).isNotEqualTo(student2);

        student2.setId(student1.getId());
        assertThat(student1).isEqualTo(student2);

        student2 = getStudentSample2();
        assertThat(student1).isNotEqualTo(student2);
    }

    @Test
    void groupeTest() throws Exception {
        Student student = getStudentRandomSampleGenerator();
        Groupe groupeBack = getGroupeRandomSampleGenerator();

        student.setGroupe(groupeBack);
        assertThat(student.getGroupe()).isEqualTo(groupeBack);

        student.groupe(null);
        assertThat(student.getGroupe()).isNull();
    }
}
