<template>
  <div class="flex flex-col xl:(px-180px)">
    <DetailCarousel />
    <div class="border-t my-10"> </div>
    <div class="flex flex-col-reverse justify-center mt-0 md:(flex-row mt-6) gap-8 mx-auto">
      <n-tabs class="w-100vw px-4 md:( px-0 flex-col space-x-0 w-50vw )" size="large">
        <n-tab-pane name="详情" tab="详情">
          <Story :id="2" />
        </n-tab-pane>
        <n-tab-pane name="常见问题" tab="常见问题" class="space-y-3">
          <FQA :id="2" />
        </n-tab-pane>
        <n-tab-pane name="动态" tab="动态" class="space-y-3">
          <JournalList :id="2" />
        </n-tab-pane>
        <n-tab-pane name="讨论" tab="讨论">
          <CommentList :id="2" />
        </n-tab-pane>
      </n-tabs>
      <div class="md:(flex-col mt-60px)">
        <AvatarCard :id="2" class="w-95vw md:(min-w-64 max-w-64 mx-px ) mb-4 mx-auto" />
        <div
          class="flex w-95vw flex-row space-x-3 md:(flex-col space-x-0 mx-px max-w-64 mx-px) overflow-x-auto mx-auto"
        >
          <template v-for="item in [0, 1, 2, 3]" :key="item">
            <PerkCard class="min-w-64 max-w-64" />
          </template>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
  // import { DetailCarousel } from '@/components';
  // import Story from './tab/storyTab.vue';
  // import JournalList from './tab/journalTab.vue';
  // import FQA from './tab/fqaTab.vue';
  // import CommentList from './tab/commentTab.vue';
</script>
<style scoped></style>
