package com.tooth.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.tooth.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StudentPWDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentPWDTO.class);
        StudentPWDTO studentPWDTO1 = new StudentPWDTO();
        studentPWDTO1.setId(1L);
        StudentPWDTO studentPWDTO2 = new StudentPWDTO();
        assertThat(studentPWDTO1).isNotEqualTo(studentPWDTO2);
        studentPWDTO2.setId(studentPWDTO1.getId());
        assertThat(studentPWDTO1).isEqualTo(studentPWDTO2);
        studentPWDTO2.setId(2L);
        assertThat(studentPWDTO1).isNotEqualTo(studentPWDTO2);
        studentPWDTO1.setId(null);
        assertThat(studentPWDTO1).isNotEqualTo(studentPWDTO2);
    }
}
