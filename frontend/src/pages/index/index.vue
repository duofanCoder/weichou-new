<template>
  <div>
    <Carousel />
    <Introduce />
    <div class="px-5 sm:(px-12) md:(px-20) lg:(px-30) xl:(px-60) 2xl:(px-80)">
      <Campaign />
      <Category />
    </div>
  </div>
</template>
<script setup lang="ts">
  import { Carousel, Introduce, Category, Campaign } from './components';
</script>
<style scoped></style>
