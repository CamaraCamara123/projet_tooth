import { ITooth } from 'app/shared/model/tooth.model';
import { IGroupe } from 'app/shared/model/groupe.model';

export interface IPW {
  id?: number;
  title?: string | null;
  objectif?: string | null;
  docsContentType?: string | null;
  docs?: string | null;
  tooth?: ITooth;
  groupes?: IGroupe[] | null;
}

export const defaultValue: Readonly<IPW> = {};
