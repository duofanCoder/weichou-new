<template>
  <div class="w-full">
    <div v-for="(item, index) in faqList" :key="index" class="py-4 flex gap-4">
      <div class="flex flex-col w-full gap-4">
        <n-input type="text" v-model:value="faqList[index].question" />
        <n-input type="textarea" v-model:value="faqList[index].answer" />
      </div>
      <div>
        <n-button @click="removeFaq(faqList[index])">删除</n-button>
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
  import { Dto } from '@/model';
  import { fetchRemoveFaq } from '@/service';
  import { ref } from 'vue';
  const faqList = ref<Array<Partial<Dto.Faq>>>(Array(0));

  const addItem = () => {
    faqList.value.push({
      question: '',
      answer: '',
      campaignDetailId: 1,
    });
  };

  const removeFaq = (faq: Partial<Dto.Faq>) => {
    if (faq.id != undefined) {
      fetchRemoveFaq([faq.id]);
    }
    faqList.value = faqList.value.filter((obj) => obj !== faq);
  };
</script>

<style scoped></style>
