package com.tooth.service.mapper;

import org.junit.jupiter.api.BeforeEach;

class ToothMapperTest {

    private ToothMapper toothMapper;

    @BeforeEach
    public void setUp() {
        toothMapper = new ToothMapperImpl();
    }
}
