import { request } from '../request';

export function fetchQueryPerk(condition: Partial<Condition.Common>) {
  return request.post<Dto.Page<Dto.Perk>>('/perk/query', condition);
}

export function fetchRemovePerk(ids: number[]) {
  const searchParams = new URLSearchParams();
  ids.forEach((id) => searchParams.append('primaryKeys', id.toString()));
  return request.post(`/perk/remove?${searchParams.toString()}`);
}

export function fetchSavePerk(perk: Partial<Dto.Perk>) {
  if (perk.id != undefined) {
    return fetchUpdatePerk(perk);
  }
  return request.post('/perk/save', perk);
}

export function fetchUpdatePerk(perk: Partial<Dto.Perk>) {
  return request.post('/perk/update', perk);
}
