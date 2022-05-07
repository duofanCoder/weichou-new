import { App } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import { createRouterGuard } from './grud';
import routes from 'virtual:generated-pages';
export const router = createRouter({
  history: createWebHistory(),
  routes,
});
export const setupRouter = (app: App) => {
  app.use(router);
  createRouterGuard(router);
};
