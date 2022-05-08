import { Dto } from '@/model';
import { request } from '../request';

export function fetchQueryCampaignIntro(condition: Partial<Condition.Campaign>) {
  return request.post<Dto.Page<Dto.CampaignIntro>>('/campaignIntro/query', condition);
}

export function fetchRemoveCampaignIntro(ids: number[]) {
  const searchParams = new URLSearchParams();
  ids.forEach((id) => searchParams.append('primaryKeys', id.toString()));
  return request.post(`/campaignIntro/remove?${searchParams.toString()}`);
}

export function fetchSaveCampaignIntro(campaignIntro: Partial<Dto.CampaignIntro>) {
  if (campaignIntro.id != undefined) {
    return fetchUpdateCampaignIntro(campaignIntro);
  }
  return request.post<Dto.CampaignIntro>('/campaignIntro/save', campaignIntro);
}

export function fetchUpdateCampaignIntro(campaignIntro: Partial<Dto.CampaignIntro>) {
  return request.post('/campaignIntro/update', campaignIntro);
}

export function fetchGetMeCampaignIntro() {
  return request.post('/campaignIntro/me');
}
