import { AssetType, JournalType } from '@/model';

export enum CampaignState {
  DRAFT_FIRST = '草稿',
  AUDIT_FIRST = '审核中',
  REJECT_FIRST = '审核未通过',
  UNDERWAY_FIRST = '孵化中',
  SUCCESS_FIRST = '孵化成功',
  FAIL_FIRST = '审核中',
  FINISH_FIRST = '孵化已过时',
  DRAFT_SECOND = '草稿',
  AUDIT_SECOND = '审核中',
  REJECT_SECOND = '审核未通过',
  UNDERWAY_SECOND = '众筹中',
  SUCCESS_SECOND = '众筹成功',
  FAIL_SECOND = '众筹失败',
  FINISH_SECOND = '众筹已过时',
}

export declare namespace Dto {
  // header
  interface File {
    size: number;
    suffix: string;
    createTime: string;
    updateTime: string;
    uploadStartTime: string;
    height: string;
    originalFileName: string;
    fullFilePath: string;
    filePath: string;
    uploadEndTime: string;
  }

  interface Token {
    prefix: string;
    expiration: number;
    token: string;
  }

  interface Page<T> {
    currentPage: number;
    data: Array<T>;
    totalPage: number;
  }

  interface User {
    id: number;
    username: string;
    nickname: string;
    password: string;
    email: string;
    wechat: string;
    bilibili: string;
    gender: boolean;
    avatar: string;
    birth: Date;
    mobile: string;
  }

  interface CampaignIntro {
    id: number;
    categoryId: number;
    posterImg: string;
    title: string;
    description: string;
    location: string;
    totalMoney: number;
    currentMoney: number;
    category: Category;
    endTime: Date;
    supportCount: number;
    campaignDetailId: number;
    isEye: boolean;
    owner: User;
    createTime: Date;
  }

  interface Asset {
    id: number;
    assetType: AssetType;
    url: string;
    campaignDetailId: number;
  }

  enum SourceType {
    video,
    image,
  }

  interface Journal {
    id: number;
    createTime: Date;
    title: string;
    description: string;
    journalType: JournalType;
    content: string;
    campaignDetailId: number;
    posterImg: string;
    owner: User;
  }

  interface Faq {
    id: number;
    question: string;
    answer: string;
    campaignDetailId: number;
  }

  interface CampaignDetail {
    id: number;
    assetIds: number[];
    faqsIds: number[];
    journalIds: number[];
    perkIds: number[];
    story: Journal;
    perkList: Array<Perk>;
    assetList: Array<Asset>;
    faqList: Array<Faq>;
    journalList: Array<Journal>;
    campaignIntro: CampaignIntro;
  }

  interface File {
    size: number;
    suffix: string;
    createTime: string;
    updateTime: string;
    uploadStartTime: string;
    height: string;
    originalFileName: string;
    fullFilePath: string;
    filePath: string;
    uploadEndTime: string;
  }

  interface Category {
    id: number;
    name: string;
    icon: string;
  }

  interface Perk {
    id: number;
    title: string;
    price: number;
    description: string;
    posterImg: string;
    campaignDetailId: number;
  }

  interface CampaignCard {
    id: number;
    cardImg: string;
    title: string;
    description: string;
    raisedMoney: number;
    goalRaisedMoney: number;
    categaryName: string;
    deadline: Date;
    isAttention: boolean;
    isInProgress: boolean;
  }

  interface PayOrder {
    id: number;
    createTime: string;
    updateTime: string;
    campaignDetail: CampaignDetail;
    campaignIntro: CampaignIntro;
    perk: Perk;
    owner: User;

    campaignDetailId: number;
    perkId: number;
    ownerId: number;
  }
}
