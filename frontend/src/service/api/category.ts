import { request } from '../request';
import { Condition, Dto } from '@/model';

export function fetchQueryCategory(condition: Partial<Condition.Common>) {
  return request.post<Dto.Page<Dto.Category>>('/category/query', condition);
}

export function fetchRemoveCategory(ids: number[]) {
  const searchParams = new URLSearchParams();
  ids.forEach((id) => searchParams.append('primaryKeys', id.toString()));
  return request.post(`/category/remove?${searchParams.toString()}`);
}

export function fetchSaveCategory(category: Partial<Dto.Category>) {
  return request.post('/category/save', category);
}

export function fetchUpdateCategory(category: Partial<Dto.Category>) {
  return request.post('/category/update', category);
}
