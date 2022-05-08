<template>
  <template v-for="item in faqList" :key="item.id">
    <n-card hoverable>
      <div class="text-base font-semibold md:(text-xl) mb-2">{{ item.question }} </div>
      <div class="text-sm md:( text-base )">{{ item.answer }} </div>
    </n-card>
  </template>
</template>
<script setup lang="ts">
  defineProps({
    faqList: { type: Object },
  });
</script>
<style scoped></style>
