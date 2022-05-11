<template>
  <p class="text-3xl text-green-500 mb-2">基本</p>
  <p class="text-gray-400 text-base font-thin mb-4">
    给人留下良好的第一印象：介绍你的竞选目标，吸引人们了解更多。
    这些基本信息将在您的活动页面、活动卡和搜索中代表您的活动。
    活动的基础信息，将作为简略信息展示，总之简要介绍你的活动，尽量做到言简意赅。
  </p>
  <n-form :model="campaignIntroModel">
    <n-form-item label="活动标题" path="title">
      <n-input v-model:value="campaignIntroModel.title" />
    </n-form-item>
    <n-form-item label="活动描述" path="description">
      <n-input type="textarea" v-model:value="campaignIntroModel.description" />
    </n-form-item>
    <n-form-item label="卡片图片" path="posterImg">
      <Upload v-model:filePath="campaignIntroModel.posterImg" :max="1" />
    </n-form-item>
    <n-form-item label="地点" path="location">
      <n-input v-model:value="campaignIntroModel.location" />
    </n-form-item>
    <n-form-item label="分类" path="category">
      <n-select
        v-model:value="campaignIntroModel.categoryId"
        placeholder="选择分类"
        :options="categoryOptions"
      />
    </n-form-item>
    <n-form-item label="截至日期" path="location">
      <n-date-picker v-model:value="campaignIntroModel.endTime" type="datetime" />
    </n-form-item>
  </n-form>
</template>

<script setup lang="ts">
  import { onMounted, reactive, ref } from 'vue';
  import { fetchQueryCategory } from '@/service';
  import { SelectOption } from 'naive-ui';
  import { Condition, Dto } from '@/model';

  const campaignIntroModel = ref<Partial<Dto.CampaignIntro>>({});
  defineExpose({
    campaignIntroModel,
  });

  //
  const categoryOptions = ref<Array<SelectOption>>([]);
  const condition = reactive<Partial<Condition.Common>>({ pageSize: 999 });
  const reload = () => {
    fetchQueryCategory(condition).then((res) => {
      if (res.data != null) {
        categoryOptions.value = res.data.data.map((item) => {
          return {
            label: item.name,
            value: item.id,
          };
        });
      }
    });
  };
  //
  onMounted(() => {
    reload();
  });
</script>

<style scoped></style>
