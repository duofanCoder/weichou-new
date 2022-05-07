import { useTitle } from '@vueuse/core';
import { Router } from 'vue-router';
import { createPermissionGuard } from './permission';
export function createRouterGuard(router: Router) {
  router.beforeEach(async (to, from, next) => {
    // 开始 loadingBar
    window.$loadingBar?.start();
    await createPermissionGuard(to, from, next, router);
  });
  router.afterEach((to) => {
    // 设置document title
    useTitle(to.meta.title as string);
    // 结束 loadingBar
    window.$loadingBar?.finish();
  });
  // onBeforeRouteUpdate()
}
