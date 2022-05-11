import { Condition, Dto } from '@/model';

import { request } from '../request';

export function fetchQueryPayOrder(condition: Partial<Condition.PayOrder>) {
  return request.post<Dto.Page<Dto.PayOrder>>('/payOrder/query', condition);
}

export function fetchRemovePayOrder(ids: number[]) {
  const searchParams = new URLSearchParams();
  ids.forEach((id) => searchParams.append('primaryKeys', id.toString()));
  return request.post(`/payOrder/remove?${searchParams.toString()}`);
}

export function fetchSavePayOrder(payOrder: Partial<Dto.PayOrder>) {
  return request.post('/payOrder/save', payOrder);
}

export function fetchUpdatePayOrder(payOrder: Partial<Dto.PayOrder>) {
  return request.post('/payOrder/update', payOrder);
}
