import { IUser } from 'app/shared/model/user.model';
import { IGroupe } from 'app/shared/model/groupe.model';

export interface IStudent {
  id?: number;
  number?: string;
  cne?: string;
  cin?: string;
  birthDay?: string;
  user?: IUser;
  groupe?: IGroupe | null;
}

export const defaultValue: Readonly<IStudent> = {};
