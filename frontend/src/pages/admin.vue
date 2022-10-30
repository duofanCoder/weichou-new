<template>
  <div class="flex flex-col md:(flex-row) h-[calc(100vh-64px)] gap-10">
    <div class="flex flex-col text-lg space-y-5 pt-16 bg-light-50">
      <router-link
        to="/admin"
        class="flex pl-8 py-3 rounded-r-full pr-40 hover:( bg-gray-200)"
        :class="[route.name == 'admin' ? 'menuActive' : '']"
      >
        <i-fluent-people-community-16-filled class="h-7 w-7 self-center mr-3" /><p
          class="whitespace-nowrap"
          >用户管理</p
        >
      </router-link>
      <router-link
        to="/admin/campaign"
        class="flex pl-8 py-3 rounded-r-full pr-40 hover:( bg-gray-200)"
        :class="[route.name == 'admin-campaign' ? 'menuActive' : '']"
        ><i-uil-feedback class="h-7 w-7 self-center mr-3" />
        <p class="whitespace-nowrap">活动管理</p>
      </router-link>
      <router-link
        to="/admin/category"
        class="flex pl-8 py-3 rounded-r-full pr-40 hover:( bg-gray-200)"
        :class="[route.name == 'admin-category' ? 'menuActive' : '']"
        ><i-uil-feedback class="h-7 w-7 self-center mr-3" />
        <p class="whitespace-nowrap">类目管理</p>
      </router-link>
      <div
        class="flex cursor-pointer pl-8 py-3 rounded-r-full pr-40 hover:( bg-gray-200)"
        @click="logout"
        ><i-uil-feedback class="h-7 w-7 self-center mr-3" />
        <p class="whitespace-nowrap">注销</p>
      </div>
    </div>

    <router-view></router-view>
  </div>
</template>
<script setup lang="ts">
  import { useUserStore } from '@/store';
  import { useRoute } from 'vue-router';
  const { logout } = useUserStore();
  const route = useRoute();
</script>
<style scoped>
  .menuActive {
    color: rgba(5, 150, 105);
    --tw-bg-opacity: 1;
    background-color: rgba(209, 250, 229, var(--tw-bg-opacity));
  }
</style>
