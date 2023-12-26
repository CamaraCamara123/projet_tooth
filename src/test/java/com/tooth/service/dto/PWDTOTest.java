package com.tooth.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.tooth.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PWDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PWDTO.class);
        PWDTO pWDTO1 = new PWDTO();
        pWDTO1.setId(1L);
        PWDTO pWDTO2 = new PWDTO();
        assertThat(pWDTO1).isNotEqualTo(pWDTO2);
        pWDTO2.setId(pWDTO1.getId());
        assertThat(pWDTO1).isEqualTo(pWDTO2);
        pWDTO2.setId(2L);
        assertThat(pWDTO1).isNotEqualTo(pWDTO2);
        pWDTO1.setId(null);
        assertThat(pWDTO1).isNotEqualTo(pWDTO2);
    }
}
