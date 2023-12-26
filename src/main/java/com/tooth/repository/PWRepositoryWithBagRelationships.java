package com.tooth.repository;

import com.tooth.domain.PW;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface PWRepositoryWithBagRelationships {
    Optional<PW> fetchBagRelationships(Optional<PW> pW);

    List<PW> fetchBagRelationships(List<PW> pWS);

    Page<PW> fetchBagRelationships(Page<PW> pWS);
}
