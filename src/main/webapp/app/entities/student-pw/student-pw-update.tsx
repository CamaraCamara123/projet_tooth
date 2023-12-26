import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { IPW } from 'app/shared/model/pw.model';
import { getEntities as getPWs } from 'app/entities/pw/pw.reducer';
import { IStudentPW } from 'app/shared/model/student-pw.model';
import { getEntity, updateEntity, createEntity, reset } from './student-pw.reducer';

export const StudentPWUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const students = useAppSelector(state => state.student.entities);
  const pWS = useAppSelector(state => state.pW.entities);
  const studentPWEntity = useAppSelector(state => state.studentPW.entity);
  const loading = useAppSelector(state => state.studentPW.loading);
  const updating = useAppSelector(state => state.studentPW.updating);
  const updateSuccess = useAppSelector(state => state.studentPW.updateSuccess);

  const handleClose = () => {
    navigate('/student-pw');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getStudents({}));
    dispatch(getPWs({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.angleLeft !== undefined && typeof values.angleLeft !== 'number') {
      values.angleLeft = Number(values.angleLeft);
    }
    if (values.angleRigth !== undefined && typeof values.angleRigth !== 'number') {
      values.angleRigth = Number(values.angleRigth);
    }
    if (values.angleCenter !== undefined && typeof values.angleCenter !== 'number') {
      values.angleCenter = Number(values.angleCenter);
    }
    values.date = convertDateTimeToServer(values.date);

    const entity = {
      ...studentPWEntity,
      ...values,
      student: students.find(it => it.id.toString() === values.student.toString()),
      pw: pWS.find(it => it.id.toString() === values.pw.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          date: displayDefaultDateTime(),
        }
      : {
          ...studentPWEntity,
          date: convertDateTimeFromServer(studentPWEntity.date),
          student: studentPWEntity?.student?.id,
          pw: studentPWEntity?.pw?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="toothApp.studentPW.home.createOrEditLabel" data-cy="StudentPWCreateUpdateHeading">
            <Translate contentKey="toothApp.studentPW.home.createOrEditLabel">Create or edit a StudentPW</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="student-pw-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('toothApp.studentPW.time')} id="student-pw-time" name="time" data-cy="time" type="text" />
              <ValidatedBlobField
                label={translate('toothApp.studentPW.imageFront')}
                id="student-pw-imageFront"
                name="imageFront"
                data-cy="imageFront"
                isImage
                accept="image/*"
              />
              <ValidatedBlobField
                label={translate('toothApp.studentPW.imageSide')}
                id="student-pw-imageSide"
                name="imageSide"
                data-cy="imageSide"
                isImage
                accept="image/*"
              />
              <ValidatedField
                label={translate('toothApp.studentPW.angleLeft')}
                id="student-pw-angleLeft"
                name="angleLeft"
                data-cy="angleLeft"
                type="text"
              />
              <ValidatedField
                label={translate('toothApp.studentPW.angleRigth')}
                id="student-pw-angleRigth"
                name="angleRigth"
                data-cy="angleRigth"
                type="text"
              />
              <ValidatedField
                label={translate('toothApp.studentPW.angleCenter')}
                id="student-pw-angleCenter"
                name="angleCenter"
                data-cy="angleCenter"
                type="text"
              />
              <ValidatedField
                label={translate('toothApp.studentPW.date')}
                id="student-pw-date"
                name="date"
                data-cy="date"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                id="student-pw-student"
                name="student"
                data-cy="student"
                label={translate('toothApp.studentPW.student')}
                type="select"
                required
              >
                <option value="" key="0" />
                {students
                  ? students.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField id="student-pw-pw" name="pw" data-cy="pw" label={translate('toothApp.studentPW.pw')} type="select" required>
                <option value="" key="0" />
                {pWS
                  ? pWS.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.title}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/student-pw" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default StudentPWUpdate;
