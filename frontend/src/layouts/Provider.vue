<template>
  <n-config-provider :locale="zhCN" :date-locale="dateZhCN">
    <n-dialog-provider>
      <n-message-provider>
        <n-notification-provider>
          <n-loading-bar-provider>
            <div class="min-h-screen flex flex-col justify-between">
              <Header />
              <App class="h-full w-full flex flex-col"></App>
              <Footer />
            </div>
          </n-loading-bar-provider>
        </n-notification-provider>
      </n-message-provider>
    </n-dialog-provider>
  </n-config-provider>
</template>

<script lang="ts" setup>
  import { zhCN, dateZhCN } from 'naive-ui';
  import App from '@/App.vue';

  import { Header, Footer } from './components';
</script>
<style></style>
