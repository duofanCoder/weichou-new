import { Condition, Dto } from '@/model';

import { request } from '../request';

export function fetchQueryAsset(condition: Partial<Condition.Common>) {
  return request.post<Dto.Page<Dto.Asset>>('/asset/query', condition);
}

export function fetchRemoveAsset(ids: number[]) {
  const searchParams = new URLSearchParams();
  ids.forEach((id) => searchParams.append('primaryKeys', id.toString()));
  return request.post(`/asset/remove?${searchParams.toString()}`);
}

export function fetchSaveAsset(asset: Partial<Dto.Asset>) {
  if (asset.id != undefined) {
    return fetchUpdateAsset(asset);
  }
  return request.post<Dto.Asset>('/asset/save', asset);
}

export function fetchUpdateAsset(asset: Partial<Dto.Asset>) {
  return request.post('/asset/update', asset);
}
