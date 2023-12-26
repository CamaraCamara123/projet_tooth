import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Professor from './professor';
import Student from './student';
import Groupe from './groupe';
import Tooth from './tooth';
import PW from './pw';
import StudentPW from './student-pw';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="professor/*" element={<Professor />} />
        <Route path="student/*" element={<Student />} />
        <Route path="groupe/*" element={<Groupe />} />
        <Route path="tooth/*" element={<Tooth />} />
        <Route path="pw/*" element={<PW />} />
        <Route path="student-pw/*" element={<StudentPW />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};