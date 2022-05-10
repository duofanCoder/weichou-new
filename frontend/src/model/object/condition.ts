import { JournalType } from '../enum';

declare namespace Condition {
  interface Common {
    name: string;
    pageSize: number;
    pageNum: number;
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
    title: string;
    isFinish: boolean;
    categoryId: number;
    pageSize: number;
    pageNum: number;
  }

  interface Journal {
    pageSize: number;
    pageNum: number;
    jorunalType: JournalType;
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
