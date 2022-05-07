<template>
  <div class="h-full mb-auto pt-64px">
    <div class="flex gap-12 mx-auto">
      <n-steps class="w-20 h-70" vertical :current="currentRef">
        <n-step title="基本"/>
        <n-step title="内容"/>
        <n-step title="回报"/>
        <n-step title="筹集"/>
        <n-step title="完成"/>
      </n-steps>
      <div class="flex flex-col px-12 max-w-200 h-full">
        <n-card class="w-full min-h-150 h-full pb-10">
          <CampaignIntro v-if="currentRef==1" ref="introRef"/>
          <CampaignDetail v-if="currentRef==2" ref="detailRef"/>

          <div class="absolute right-5 bottom-5 space-x-5">
            <n-button type="primary" @click="stepHandle(1)">下一步</n-button>
          </div>
        </n-card>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {ref} from 'vue';
import {CampaignIntro, CampaignDetail} from "./components";
import {fetchSaveCampaignIntro} from "@/service";

const currentRef = ref(1);
const introRef = ref(null)
const detailRef = ref(null)

const campaignIntroModel = ref<Partial<Dto.CampaignIntro>>({})
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
        const {data} = await fetchSaveCampaignIntro(campaignIntroModel.value)
        if (data == null) {
          return;
        }
        Object.assign(campaignIntroModel.value, data)
        break;
      }
    }
  }
  currentRef.value = currentRef.value + add;
}

</script>

<style scoped></style>
