<template>
  <div class="flex">
    <div v-if="journalRef != null" class="mx-auto max-w-690px h-full">
      <n-space class="w-full" vertical>
        <div>
          <img :src="journalRef.posterImg" alt="" />
        </div>
        <div class="text-3xl font-bold">
          {{ journalRef.title }}
        </div>
        <div class="w-full flex justify-between">
          <div
            v-if="journalRef.owner != null"
            class="flex items-center mb-2 gap-2 hover:(cursor-pointer)"
          >
            <n-avatar round :size="45" :src="journalRef.owner.avatar" />
            <div class="flex flex-col">
              <p class="font-extrabold hover:(cursor-pointer)">{{ journalRef.owner.nickname }}</p>
              <div> <span>中国,上海</span></div>
            </div>
          </div>
          <p> 发布时间: {{ journalRef.createTime }} </p>
        </div>
      </n-space>

      <md-editor v-model="journalRef.content" :preview-only="true" />
    </div>
  </div>
</template>
<script setup lang="ts">
  import { Dto } from '@/model';
  import { fetchGetJournal } from '@/service';
  import MdEditor from 'md-editor-v3';
  import 'md-editor-v3/lib/style.css';
  import { ref } from 'vue';
  import { useRoute } from 'vue-router';

  const route = useRoute();
  const journalRef = ref<Partial<Dto.Journal>>({ content: '' });
  const journalId = Number(route.query.journalId);
  if (journalId != NaN) {
    fetchGetJournal(journalId).then((res) => {
      if (res.data != null) {
        journalRef.value = res.data;
      }
    });
  }
</script>
<style scoped></style>
