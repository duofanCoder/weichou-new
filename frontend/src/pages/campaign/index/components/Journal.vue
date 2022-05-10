<template>
  <template v-for="(item, index) in props.journalList">
    <n-card v-if="Number(index) !== 0" class=" " hoverable>
      <div
        @click="router.push({ name: 'journal-detail', query: { journalId: item.id } })"
        class="hover:(cursor-pointer)"
      >
        <p class="font-semibold text-2xl">{{ item.title }}</p>
        <img class="h-60 object-cover my-2" :src="item.posterImg" />
        <p>
          {{ item.description }}
        </p>
      </div>
      <div class="border-t my-2"></div>
      <div class="flex space-x-4">
        <div>
          <span><i-mdi-message-outline class="self-center align-middle" /></span>
          <span class="ml-2">500</span>
        </div>

        <div>
          <span class="cursor-pointer">
            <i-fa-thumbs-o-up v-if="true" class="self-center align-middle" />
            <i-fa-thumbs-up v-else class="self-center align-middle" />
          </span>
          <span class="ml-2 align-bottom">290</span>
        </div>
      </div>
    </n-card>
  </template>
</template>
<script setup lang="ts">
  import { Dto, JournalType } from '@/model';

  import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  const props = defineProps({
    journalList: { type: Object },
  });

  const router = useRouter();
</script>
<style scoped></style>
