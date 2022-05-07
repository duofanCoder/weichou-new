import { Dto } from '@/model/object/dto';
import { request } from '../request';

export function fetchQueryFaq(condition: Partial<Condition.Common>) {
  return request.post<Dto.Page<Dto.Faq>>('/faq/query', condition);
}

export function fetchRemoveFaq(ids: number[]) {
  const searchParams = new URLSearchParams();
  ids.forEach((id) => searchParams.append('primaryKeys', id.toString()));
  return request.post(`/faq/remove?${searchParams.toString()}`);
}

export function fetchSaveFaq(faq: Partial<Dto.Faq>) {
  if (faq.id != undefined) {
    return fetchUpdateFaq(faq);
  }
  return request.post('/faq/save', faq);
}

export function fetchUpdateFaq(faq: Partial<Dto.Faq>) {
  return request.post('/faq/update', faq);
}
