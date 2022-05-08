import { Dto } from '@/model';
import { request } from '../request';

export function fetchQueryCampaignDetail(condition: Partial<Condition.Campaign>) {
  return request.post<Dto.Page<Dto.CampaignDetail>>('/campaignDetail/query', condition);
}

export function fetchRemoveCampaignDetail(ids: number[]) {
  const searchParams = new URLSearchParams();
  ids.forEach((id) => searchParams.append('primaryKeys', id.toString()));
  return request.post(`/campaignDetail/remove?${searchParams.toString()}`);
}

export function fetchSaveCampaignDetail(campaignDetail: Partial<Dto.CampaignDetail>) {
  if (campaignDetail.id != undefined) {
    return fetchUpdateCampaignDetail(campaignDetail);
  }
  return request.post<Dto.CampaignDetail>('/campaignDetail/save', campaignDetail);
}

export function fetchUpdateCampaignDetail(campaignDetail: Partial<Dto.CampaignDetail>) {
  return request.post('/campaignDetail/update', campaignDetail);
}

export function fetchGetCampaignDetail(id: number) {
  return request.post(`/campaignDetail/${id}`);
}
