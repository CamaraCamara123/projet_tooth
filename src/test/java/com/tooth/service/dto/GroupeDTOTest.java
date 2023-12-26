package com.tooth.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.tooth.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GroupeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupeDTO.class);
        GroupeDTO groupeDTO1 = new GroupeDTO();
        groupeDTO1.setId(1L);
        GroupeDTO groupeDTO2 = new GroupeDTO();
        assertThat(groupeDTO1).isNotEqualTo(groupeDTO2);
        groupeDTO2.setId(groupeDTO1.getId());
        assertThat(groupeDTO1).isEqualTo(groupeDTO2);
        groupeDTO2.setId(2L);
        assertThat(groupeDTO1).isNotEqualTo(groupeDTO2);
        groupeDTO1.setId(null);
        assertThat(groupeDTO1).isNotEqualTo(groupeDTO2);
    }
}
