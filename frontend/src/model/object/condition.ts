import { JournalType } from '../enum';

export declare namespace Condition {
  interface Common {
    name: string;
    pageSize: number;
    pageNum: number;
    journalType: JournalType;
  }
  interface User {
    pageSize: number;
    pageNum: number;
    name: string;
    gander: boolean;
    positionId: number;
    departmentId: number;
  }

  interface Campaign {
    title: string | undefined;
    isFinish: boolean;
    categoryId: number;
    pageSize: number;
    pageNum: number;
  }

  interface Journal {
    pageSize: number;
    pageNum: number;
    journalType: JournalType;
    campaignId: number;
  }

  interface PayOrder {
    pageSize: number;
    pageNum: number;
    ownerId: number;
    detailId: number;
    perkId: number;
  }
}
