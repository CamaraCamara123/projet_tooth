import { IProfessor } from 'app/shared/model/professor.model';
import { IStudent } from 'app/shared/model/student.model';
import { IPW } from 'app/shared/model/pw.model';

export interface IGroupe {
  id?: number;
  code?: string | null;
  year?: string | null;
  professor?: IProfessor;
  students?: IStudent[] | null;
  pws?: IPW[] | null;
}

export const defaultValue: Readonly<IGroupe> = {};
