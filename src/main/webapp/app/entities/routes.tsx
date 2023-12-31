import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Professor from './professor';
import Student from './student';
import Groupe from './groupe';
import Tooth from './tooth';
import PW from './pw';
import StudentPW from './student-pw';
import PrivateRoute from 'app/shared/auth/private-route';
import { AUTHORITIES } from 'app/config/constants';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="professor/*" element={
          <PrivateRoute hasAnyAuthorities={[AUTHORITIES.ADMIN]}>
            <Professor />
          </PrivateRoute>} />
        <Route
          path="student/*"
          element={
            <PrivateRoute hasAnyAuthorities={[AUTHORITIES.PROFESSOR]}>
              <Student />
            </PrivateRoute>
          }
        />
        <Route path="groupe/*" element={<Groupe />} />
        <Route path="tooth/*" element={<Tooth />} />
        <Route path="pw/*" element={<PW />} />
        <Route path="student-pw/*" element={<StudentPW />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
