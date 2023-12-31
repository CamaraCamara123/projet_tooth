import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';
import { AUTHORITIES } from 'app/config/constants';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/professor">
        <Translate contentKey="global.menu.entities.professor" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/student">
        <Translate contentKey="global.menu.entities.student" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/groupe">
        <Translate contentKey="global.menu.entities.groupe" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/tooth">
        <Translate contentKey="global.menu.entities.tooth" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/pw">
        <Translate contentKey="global.menu.entities.pw" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/student-pw">
        <Translate contentKey="global.menu.entities.studentPw" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
