import { NavigationGuardNext, RouteLocationNormalized, Router } from 'vue-router';
import { useUserStore } from '@/store';
import { getToken } from '@/utils';

export async function createPermissionGuard(
  to: RouteLocationNormalized,
  _from: RouteLocationNormalized,
  next: NavigationGuardNext,
  _router: Router
) {
  const needLogin = Boolean(to.meta?.auth);
  const needAdmin = Boolean(to.meta?.admin);
  const userStore = useUserStore();
  const isAdmin = false;
  const isLogin = Boolean(getToken());

  // needLogin:true
  // permission.ts:18 needAdmin:true
  // permission.ts:19 isAdmin:false
  // permission.ts:20 isLogin:false
  // permission.ts:64 未登录状态进入需要登录权限的ADMIN页面
  // permission.ts:106 路由结束
  const actions: [boolean, () => void][] = [
    // 已登录状态跳转登录页，跳转至首页
    [
      isLogin && to.name === 'login' && !isAdmin,
      () => {
        next({ name: 'index' });

        // console.log('已登录状态跳转登录页，跳转至首页');
      },
    ],
    // 不需要登录权限的页面直接通行
    [
      !needLogin && !needAdmin,
      () => {
        next();
        // console.log('不需要登录权限的页面直接通行');
      },
    ],
    // 未登录状态进入需要登录权限的页面
    [
      !isLogin && needLogin && !needAdmin,
      () => {
        const redirect = to.fullPath;
        next({ name: 'login', query: { redirect } });
        // console.log('未登录状态进入需要登录权限的页面');
      },
    ],
    // 登录状态进入需要登录权限的页面，有权限直接通行
    [
      isLogin && needLogin && !needAdmin && !isAdmin,
      () => {
        next();
        // console.log('登录状态进入需要登录权限的页面，有权限直接通行');
      },
    ],

    // 未登录状态进入需要登录权限的ADMIN页面
    [
      !isLogin && needLogin && needAdmin,
      () => {
        const redirect = to.fullPath;
        next({ name: 'login', query: { redirect } });
        // console.log('未登录状态进入需要登录权限的ADMIN页面');
      },
    ],
    // 登录状态进入需要登录权限的ADMIN页面，有权限直接通行
    [
      isLogin && needLogin && needAdmin && isAdmin,
      () => {
        next();
        // console.log('登录状态进入需要登录权限的ADMIN页面，有权限直接通行');
      },
    ],
    [
      isLogin && needLogin && isAdmin,
      () => {
        next({ name: 'admin' });
        // console.log('没找到是直接进管理员');
      },
    ],
    [
      isLogin && needLogin && !isAdmin,
      () => {
        next({ name: 'profile' });
        // console.log('没找到是直接进个人页面');
      },
    ],
    // [
    //   // 登录状态进入需要登录权限的页面，无权限，重定向到无权限页面
    //   isLogin && needLogin && !hasPermission,
    //   () => {
    //     next({ name: routeName('no-permission') });
    //   },
    // ],
  ];

  actions.some((item) => {
    const [flag, action] = item;
    if (flag) {
      action();
    }
    return flag;
  });

  // console.log('路由结束');
}
