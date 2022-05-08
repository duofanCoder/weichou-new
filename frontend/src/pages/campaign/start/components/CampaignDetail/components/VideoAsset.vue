<template>
  <div class="w-full">
    <div v-for="(item, index) in videoAssetList" :key="index" class="py-4 flex gap-4">
      <div class="flex flex-col w-full gap-4">
        <n-input type="text" v-model:value="videoAssetList[index].url" />
      </div>
      <div>
        <n-button @click="removeViedoAsset(videoAssetList[index])">删除</n-button>
      </div>
    </div>
    <div
      @click="addItem"
      class="w-full border bg-clip-border border-gray-100 h-10 rounded-md text-center hover:(bg-gray-200)"
    >
      新增
    </div>
  </div>
</template>

<script setup lang="ts">
  import { Dto } from '@/model/object/dto';
  import { fetchRemoveAsset } from '@/service';
  import { ref } from 'vue';
  import { AssetType } from '@/model';
  const videoAssetList = ref<Array<Partial<Dto.Asset>>>(Array(0));
  const { campaignDetailId } = defineProps({
    campaignDetailId: {
      type: Number,
    },
  });
  defineExpose({
    videoAssetList,
  });
  const addItem = () => {
    videoAssetList.value.push({
      url: '',
      assetType: AssetType.VIDEO,
      campaignDetailId: campaignDetailId,
    });
  };
  const removeViedoAsset = (faq: Partial<Dto.Asset>) => {
    if (faq.id != undefined) {
      fetchRemoveAsset([faq.id]);
    }
    videoAssetList.value = videoAssetList.value.filter((obj) => obj !== faq);
  };
</script>

<style scoped></style>
