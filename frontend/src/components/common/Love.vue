<template>
  <template v-if="detail" class="text-xs">
    <div
      @click="foucsLove"
      class="cursor-pointer border rounded-lg px-3 py-2 hover:(text-pink-600)"
    >
      <i-mdi-cards-heart v-if="isLove" class="self-center align-middle text-pink-600" />
      <i-mdi-cards-heart-outline v-else class="self-center align-middle" />
      <span v-if="detail" class="px-1 align-middle">
        {{ isLove ? '已关注' : '关注' }}
      </span>
    </div>
  </template>
  <template v-else>
    <div @click="foucsLove" class="cursor-pointer hover:( text-pink-600 )">
      <i-mdi-cards-heart v-if="isLove" class="self-center align-middle text-pink-600" />
      <i-mdi-cards-heart-outline v-else class="self-center align-middle" />
    </div>
  </template>
</template>
<script setup lang="ts">
  import { useUserStore } from '@/store';
  import { ref } from 'vue';

  const { id } = defineProps({
    id: {
      type: Number,
      default: 0,
    },
    detail: {
      type: Boolean,
      default: false,
    },
  });
</script>
<style scoped></style>
