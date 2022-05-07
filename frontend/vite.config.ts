import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import AutoImport from 'unplugin-auto-import/vite';
import Components from 'unplugin-vue-components/vite';
import { NaiveUiResolver } from 'unplugin-vue-components/resolvers';
import { resolve } from 'path';
import Icons from 'unplugin-icons/vite';
import IconsResolver from 'unplugin-icons/resolver';
import WindiCSS from 'vite-plugin-windicss';
import Pages from 'vite-plugin-pages';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    WindiCSS(),
    AutoImport({
      resolvers: [NaiveUiResolver()],
    }),
    Icons({
      autoInstall: true,
    }),
    Components({
      resolvers: [IconsResolver(), NaiveUiResolver()],
    }),
    Pages({
      extendRoute(route, parent) {
        if (route.name == 'admin-login') {
          return route;
        }
        if (route.name != undefined && route.name.indexOf('admin') != -1) {
          return {
            ...route,
            meta: { admin: true, auth: true },
          };
        }
        if (route.path.indexOf('/profile') == -1) {
          return route;
        }

        // Augment the route with meta that indicates that the route requires authentication.
        return {
          ...route,
          meta: { auth: true, admin: false },
        };
      },
    }),
  ],
  server: {
    fs: {
      strict: false,
    },
    host: '127.0.0.1',
    port: 3000,
    open: true,
  },
  resolve: {
    alias: {
      '@': resolve(__dirname, './src'),
    },
  },
});
