import { Dto } from '@/model';
import { request } from '../request';

export function fetchQueryJournal(condition: Partial<Condition.Common>) {
  return request.post<Dto.Page<Dto.Journal>>('/journal/query', condition);
}

export function fetchRemoveJournal(ids: number[]) {
  const searchParams = new URLSearchParams();
  ids.forEach((id) => searchParams.append('primaryKeys', id.toString()));
  return request.post(`/journal/remove?${searchParams.toString()}`);
}

export function fetchSaveJournal(journal: Partial<Dto.Journal>) {
  if (journal.id != undefined) {
    return fetchUpdateJournal(journal);
  }
  return request.post<Dto.Journal>('/journal/save', journal);
}

export function fetchUpdateJournal(journal: Partial<Dto.Journal>) {
  return request.post('/journal/update', journal);
}
