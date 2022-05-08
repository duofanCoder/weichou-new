<template>
  <div class="sm:(px-x)">
    <!-- 标题 -->
    <div class="flex flex-row mt-4 w-auto mb-10 text-2xl font-light font-light"> 热门项目 </div>
    <!-- 内容 -->
    <n-grid x-gap="10 s:25 m:30 l:42 xl:42 2xl:42" responsive="screen" cols="1 s:3" y-gap="42">
      <template v-for="item in campaignListRef" :key="item">
        <n-gi>
          <CampaignCard :campaign="item" />
        </n-gi>
      </template>
    </n-grid>
    <div class="flex mt-12">
      <router-link class="mx-auto font-extrabold text-base" to="/explore">
        <n-button class="rounded-xl w-80 py-6">发现更多</n-button>
      </router-link>
    </div>
  </div>
</template>
<script setup lang="ts">
  import { Dto } from '@/model';
  import { fetchQueryCampaignIntro } from '@/service';
  import { ref } from 'vue';
  const campaignListRef = ref<Array<Dto.CampaignIntro>>([]);

  fetchQueryCampaignIntro({ pageSize: 6 }).then((res) => {
    if (res.data != null) {
      campaignListRef.value = res.data.data;
    }
  });
</script>
<style scoped></style>
