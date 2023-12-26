import { IStudent } from 'app/shared/model/student.model';
import { IPW } from 'app/shared/model/pw.model';

export interface IStudentPW {
  id?: number;
  time?: string | null;
  imageFrontContentType?: string | null;
  imageFront?: string | null;
  imageSideContentType?: string | null;
  imageSide?: string | null;
  angleLeft?: number | null;
  angleRigth?: number | null;
  angleCenter?: number | null;
  date?: string | null;
  student?: IStudent;
  pw?: IPW;
}

export const defaultValue: Readonly<IStudentPW> = {};
