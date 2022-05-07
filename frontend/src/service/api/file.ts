import { request } from '../request';

export function fetchFileUpload(data: FormData) {
  return request.post<Array<Dto.File>>('/file/upload', data);
}
