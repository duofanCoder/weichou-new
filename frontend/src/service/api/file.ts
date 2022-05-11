import { request } from '../request';
import { Condition, Dto } from '@/model';

export function fetchFileUpload(data: FormData) {
  return request.post<Array<Dto.File>>('/file/upload', data);
}
