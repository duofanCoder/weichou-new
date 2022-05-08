<template>
  <div class="flex flex-col xl:(px-180px)">
    <div class="flex flex-col justify-center mt-0 items-center md:(flex-row mt-6 ) gap-8 mx-auto">
      <!-- md:(w-120 h-80) xl:(w-160 h-90) -->
      <Carousel :assetList="campaignDetail?.assetList" />

      <!-- md:(w-120 ) xl:(w-150 ) -->
      <Description :campaignIntro="campaignDetail?.campaignIntro" />
    </div>
    <div class="border-t my-10"> </div>
    <div class="flex flex-col-reverse justify-center mt-0 md:(flex-row mt-6) gap-8 mx-auto">
      <n-tabs class="w-100vw px-4 md:( px-0 flex-col space-x-0 w-50vw )" size="large">
        <n-tab-pane name="详情" tab="详情">
          <Story :story="campaignDetail?.story" />
        </n-tab-pane>
        <n-tab-pane name="常见问题" tab="常见问题" class="space-y-3">
          <FAQ :faqList="campaignDetail?.faqList" />
        </n-tab-pane>
        <n-tab-pane name="动态" tab="动态" class="space-y-3">
          <Journal :id="2" />
        </n-tab-pane>
        <!-- <n-tab-pane name="讨论" tab="讨论">
          <CommentList :id="2" />
        </n-tab-pane> -->
      </n-tabs>
      <div class="md:(flex-col mt-60px)">
        <AvatarCard
          :user="campaignDetail?.campaignIntro.owner"
          class="w-95vw md:(min-w-64 max-w-64 mx-px ) mb-4 mx-auto"
        />
        <div
          class="flex w-95vw flex-row space-x-3 md:(flex-col space-x-0 mx-px max-w-64 mx-px) overflow-x-auto mx-auto"
        >
          <template v-for="item in campaignDetail?.perkList" :key="item.id">
            <PerkCard :perk="item" class="min-w-64 max-w-64" />
          </template>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
  import { Dto } from '@/model';
  import { fetchGetCampaignDetail } from '@/service';
  import { computed, ref } from 'vue';
  import { useRoute } from 'vue-router';
  import { PerkCard, Carousel, Description, AvatarCard, FAQ, Story, Journal } from './components';

  const route = useRoute();
  const campaignId = Number(route.query.campaignId);
  const campaignDetail = ref<Dto.CampaignDetail>();
  fetchGetCampaignDetail(campaignId).then((res) => {
    campaignDetail.value = res.data;
  });
</script>
<style scoped></style>
