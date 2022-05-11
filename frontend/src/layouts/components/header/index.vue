<template>
  <div>
    <n-scrollbar x-scrollable>
      <n-space
        v-if="!isMobileRef"
        class="w-full min-w-160 px-6 h-64px border-b"
        justify="space-between"
      >
        <n-space class="h-full px-2 space-x-3" align="center">
          <router-link to="/">
            <Logo class="mr-4 cursor-pointer" />
          </router-link>
          <n-el @click="showDrawerRef = !showDrawerRef">
            <n-button text class="text-base">
              <p class="font-bold">探索</p>
              <i-ic-twotone-keyboard-arrow-down class="align-middle transform" />
            </n-button>
          </n-el>
          <n-el>
            <n-button text class="text-base" @click="handleSelect('wedo')"
              ><p class="font-bold">We Do</p></n-button
            >
          </n-el>
          <n-el>
            <n-button
              text
              class="text-base self-center flex"
              @click="(showSearchRef = true), (showDrawerRef = false)"
            >
              <p class="font-bold">
                <i-ic-baseline-search class="align-middle" />
              </p>
            </n-button>
          </n-el>
        </n-space>
        <n-space class="w-full h-full px-2 space-x-3" align="center">
          <n-el>
            <router-link to="/campaign/start">
              <n-button text class="text-base"><p class="font-bold">开展项目</p></n-button>
            </router-link>
          </n-el>
          <div class="h-full py-3 border-l text-gray-400"></div>
          <n-el class="w-10">
            <n-button v-if="!isLogin" text class="text-base">
              <router-link to="/login">
                <p class="font-bold">登录</p>
              </router-link>
            </n-button>
            <div v-if="isLogin">
              <router-link to="/profile">
                <n-avatar
                  class="w-10 h-10 rounded-full"
                  round
                  :src="userStore.userInfo.avatar"
                ></n-avatar>
              </router-link>
            </div>
          </n-el>
        </n-space>
      </n-space>

      <n-space v-else justify="space-between" align="center" class="w-full h-64px px-6 border-b">
        <div class="flex items-center">
          <n-dropdown
            trigger="click"
            @select="handleSelect"
            placement="bottom-start"
            :options="options"
          >
            <Logo class="mr-4 cursor-pointer" />
          </n-dropdown>
          <i-ic-twotone-keyboard-arrow-down
            class="-ml-3 align-middle transform hover:(-rotate-z-180) duration-300"
          />
        </div>

        <n-el>
          <n-button
            text
            class="text-base self-center flex"
            @click="(showSearchRef = true), (showDrawerRef = false)"
          >
            <p class="font-bold">
              <i-ic-baseline-search class="align-middle" />
            </p>
          </n-button>
        </n-el>
      </n-space>
    </n-scrollbar>

    <!-- search -->
    <div
      v-if="showSearchRef"
      class="absolute w-screen h-64px border-b top-0 text-base left-0 bg-[#fff]"
    >
      <div class="w-screen items-center space-x-3 h-full flex px-4">
        <i-ic-baseline-search class="align-middle" />
        <input
          v-model="searchKeyRef"
          type="text"
          class="outline-none w-full"
          placeholder="搜索"
          @keyup.enter="searchEnterEvent"
        />
        <n-button text @click="showSearchRef = false">
          <i-mdi-close class="align-middle" />
        </n-button>
      </div>
    </div>

    <!-- explore -->
    <div
      :class="{ hidden: !showDrawerRef }"
      id="drawer-target"
      class="absolute w-full h-screen flex overflow-hidden z-50"
    >
    </div>
    <n-drawer
      height="auto"
      v-model:show="showDrawerRef"
      placement="top"
      :trap-focus="false"
      to="#drawer-target"
    >
      <div class="w-screen-lg mx-auto px-4">
        <div class="flex py-12 justify-between">
          <div class="flex flex-col">
            <p class="font-bold mb-1"> 浏览所有项目</p>
            <p
              :style="{
                backgroundImage: `url('/src/assets/img/dropdown_banner_2x.png')`,
              }"
              class="h-full w-386px bg-cover object-cover bg-true-gray-100 text-center flex text-center"
            >
              <router-link
                to="/explore"
                @click="showDrawerRef = false"
                class="mx-auto my-auto font-bold text-xl hover:(text-green-500) cursor-pointer"
              >
                Clever Things For <br />
                Curious Humans
              </router-link>
            </p>
          </div>
          <div class="flex flex-col">
            <p class="font-bold mb-1"> 活动排行榜 </p>
            <p class="w-28 h-24 bg-true-gray-100"> 海报一张 </p>
          </div>
          <div class="flex flex-col">
            <p class="font-bold mb-1"> 优秀团队</p>
            <p class="w-28 h-24 bg-true-gray-100"> 海报一张 </p>
          </div>
          <div class="flex flex-col">
            <p class="font-bold mb-1"> 在售 </p>
            <p class="w-28 h-24 bg-true-gray-100"> 海报一张 </p>
          </div>
          <div class="flex flex-col">
            <p class="font-bold mb-1"> 为你而造</p>
            <p class="w-28 h-24 bg-true-gray-100"> 海报一张 </p>
          </div>
        </div>
      </div>
    </n-drawer>
  </div>
</template>
<script setup lang="ts">
  import { Logo } from '@/components';
  import { useRouterPush } from '@/router/router';
  import { useUserStore } from '@/store';
  import { isMobile } from '@/utils';

  import { computed, ref } from 'vue';

  const showDrawerRef = ref(false);
  const showSearchRef = ref(false);
  const routerPush = useRouterPush();

  const userStore = useUserStore();
  const isMobileRef = isMobile();
  const isLogin = computed(() => Boolean(userStore.isLogin));

  const options = computed(() => {
    if (userStore.isLogin) {
      return [
        {
          label: '主页',
          key: 'index',
        },
        {
          label: '浏览',
          key: 'explore',
        },
        {
          label: 'We Do',
          key: 'wedo',
        },
        {
          label: '我的',
          key: 'profile',
        },
        {
          label: '退出',
          key: 'logout',
        },
      ];
    } else {
      return [
        {
          label: '主页',
          key: 'index',
        },
        {
          label: '浏览',
          key: 'explore',
        },
        {
          label: 'We Do',
          key: 'wedo',
        },
        {
          label: '登录',
          key: 'login',
        },
      ];
    }
  });
  const searchKeyRef = ref('');
  const searchEnterEvent = () => {
    routerPush.routerPush({ name: 'explore', params: { searchKey: searchKeyRef.value } });
    showSearchRef.value = false;
  };
  const handleSelect = (key: string) => {
    if (key == 'wedo') {
      routerPush.routerPush({ name: 'journal-detail', query: { journalId: 23 } });
      return;
    }
    routerPush.routerPush({ name: key });
  };
</script>
<style scoped>
  .hidden {
    display: none;
  }
</style>
