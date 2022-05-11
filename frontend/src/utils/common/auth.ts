import { Dto } from '@/model';
import { EnumStorageKey } from '@/model/enum/common';
import { setLocal, getLocal, removeLocal } from '../storage';

/** 设置token */
export function setToken(token: string) {
  setLocal(EnumStorageKey.token, token);
}

/** 获取token */
export function getToken() {
  return getLocal<string>(EnumStorageKey.token) || '';
}

/** 去除token */
export function removeToken() {
  removeLocal(EnumStorageKey.token);
}

/** 获取refresh token */
export function getRefreshToken() {
  return getLocal<string>(EnumStorageKey['refresh-koken']) || '';
}

/** 设置refresh token */
export function setRefreshToken(token: string) {
  setLocal(EnumStorageKey['refresh-koken'], token);
}

/** 去除refresh token */
export function removeRefreshToken() {
  removeLocal(EnumStorageKey['refresh-koken']);
}
export function getUserInfo() {
  const emptyInfo: Dto.User = {
    id: 0,
    username: '',
    nickname: '',
    mobile: '',
    avatar: '',
    birth: new Date(),
    gender: false,
    email: '',
    password: '',
    wechat: '',
    bilibili: '',
  };
  const userInfo: Dto.User = getLocal<Dto.User>(EnumStorageKey['user-info']) || emptyInfo;
  return userInfo;
}

/** 获取用户信息 */
export function setUserInfo(userInfo: Dto.User) {
  setLocal(EnumStorageKey['user-info'], userInfo);
}

/** 去除用户信息 */
export function removeUserInfo() {
  removeLocal(EnumStorageKey['user-info']);
}

/** 去除用户相关缓存 */
export function clearAuthStorage() {
  removeToken();
  removeRefreshToken();
  removeUserInfo();
}
