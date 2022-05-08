<template>
  <div class="flex flex-col flex-1 w-full mx-auto max-w-235">
    <div class="text-center">
      <p class="font-medium text-3xl mt-12"> 活动 </p>
    </div>
    <MeCampagin />
    <LoveCampagin />
  </div>
</template>

<script setup lang="ts">
  import { MeCampagin, LoveCampagin } from './components';
</script>

<style scoped></style>
