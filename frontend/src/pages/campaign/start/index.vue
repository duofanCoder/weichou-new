<template>
  <div class="h-full mb-auto pt-64px">
    <div class="flex gap-12 mx-auto">
      <n-steps class="w-20 h-70" vertical :current="currentRef">
        <n-step title="基本" />
        <n-step title="内容" />
        <n-step title="回报" />
        <n-step title="筹集" />
        <n-step title="完成" />
      </n-steps>
      <div class="flex flex-col px-12 max-w-200 h-full">
        <n-card class="w-full min-h-150 h-full pb-10">
          <CampaignIntro v-if="currentRef == 1" ref="introRef" />
          <CampaignDetail
            v-if="currentRef == 2"
            ref="detailRef"
            :campaignDetailId="campaignDetailId"
          />
          <CampaignPerk v-if="currentRef == 3" ref="perkRef" :campaignDetailId="campaignDetailId" />

          <div v-if="currentRef == 4">
            <p class="text-3xl text-green-500 mb-2">众筹</p>
            <p class="text-gray-400 text-base font-thin mb-4">
              你想为这次竞选筹集多少资金？你的货币最低目标是1000元。
              有关选择目标金额的帮助，请参阅本文。 请务必记住我们的费用和您的预留资金。
            </p>
            <n-form :model="campaignIntroModel">
              <!-- <n-form-item label="众筹类型" path="isFixedFunding">
      <n-switch :rail-style="railStyle" v-model:value="campaignModel.isFixedFunding">
        <template #checked> 灵活众筹 </template>
        <template #unchecked> 固定众筹 </template>
      </n-switch>
    </n-form-item> -->
              <n-form-item label="目标金额" path="goalRaisedMoney">
                <div class="flex flex-col space-y-4 text-gray-400">
                  <p>
                    你想为这次竞选筹集多少资金？你的货币最低目标是1000元。有关选择目标金额的帮助，请咨询管理员。请务必记住我们的费用和您的预留资金。</p
                  >
                  <n-input-number v-model:value="campaignIntroModel.totalMoney">
                    <template #prefix> ￥ </template>
                  </n-input-number>
                </div>
              </n-form-item>
            </n-form>
          </div>

          <div v-if="currentRef == 5">
            <p class="text-3xl text-green-500 mb-2">活动开展成功！</p>
            <p class="text-gray-400 text-base font-thin mb-4">
              如果你想活动开展成功，那么你就需要勤更新活动的动态咯，这样会有更多人关注项目进展，
              才能更容易获得大家的支持哦。
            </p>
            <n-result class="mt-35" status="success" title="成功"> </n-result>
            <div class="w-full flex justify-center space-x-5"> </div>
          </div>
          <div class="absolute right-5 bottom-5 space-x-5" v-if="currentRef > 0 && currentRef < 5">
            <n-button type="primary" @click="stepHandle(1)">下一步</n-button>
          </div>
        </n-card>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
  import { computed, ref } from 'vue';
  import { CampaignIntro, CampaignDetail, CampaignPerk } from './components';
  import {
    fetchSaveAsset,
    fetchSaveCampaignIntro,
    fetchSaveFaq,
    fetchSaveJournal,
  } from '@/service';
  import { Dto } from '@/model';

  const currentRef = ref(1);
  const introRef = ref(null);
  const detailRef = ref(null);
  const perkRef = ref(null);

  const faqList = ref<Array<Partial<Dto.Faq>>>([]);
  const videoAssetList = ref<Array<Partial<Dto.Asset>>>([]);
  const journalRef = ref<Partial<Dto.Journal>>({});

  const campaignIntroModel = ref<Partial<Dto.CampaignIntro>>({});

  const campaignDetailId = computed(() => {
    return campaignIntroModel.value.campaignDetailId;
  });
  const stepHandle = async (add: number) => {
    if (add < 0) {
      //  后退
    } else {
      //  前进
      switch (currentRef.value) {
        case 1: {
          if (introRef.value != null) {
            Object.assign(campaignIntroModel.value, introRef.value.campaignIntroModel);
          }
          campaignIntroModel.value.isEye = false;
          const { data } = await fetchSaveCampaignIntro(campaignIntroModel.value);
          if (data == null) {
            return;
          }
          Object.assign(campaignIntroModel.value, data);
          break;
        }
        case 2: {
          if (detailRef.value != null) {
            Object.assign(faqList.value, detailRef.value.faqRef.faqList);
            faqList.value.forEach((item) => {
              fetchSaveFaq(item);
            });
            console.log(faqList.value);
            Object.assign(videoAssetList.value, detailRef.value.videoAssetRef.videoAssetList);
            console.log(videoAssetList.value);
            videoAssetList.value.forEach((item) => {
              fetchSaveAsset(item);
            });
            Object.assign(journalRef.value, detailRef.value.journalRef.journalRef);
            console.log(journalRef.value);
            fetchSaveJournal(journalRef.value);
          }
          break;
        }
        case 4: {
          campaignIntroModel.value.isEye = true;
          fetchSaveCampaignIntro(campaignIntroModel.value);
        }
      }
    }
    currentRef.value = currentRef.value + add;
  };
</script>

<style scoped></style>
