package com.tooth.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.tooth.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ToothDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ToothDTO.class);
        ToothDTO toothDTO1 = new ToothDTO();
        toothDTO1.setId(1L);
        ToothDTO toothDTO2 = new ToothDTO();
        assertThat(toothDTO1).isNotEqualTo(toothDTO2);
        toothDTO2.setId(toothDTO1.getId());
        assertThat(toothDTO1).isEqualTo(toothDTO2);
        toothDTO2.setId(2L);
        assertThat(toothDTO1).isNotEqualTo(toothDTO2);
        toothDTO1.setId(null);
        assertThat(toothDTO1).isNotEqualTo(toothDTO2);
    }
}
