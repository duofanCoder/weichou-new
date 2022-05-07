<template>
  <n-carousel draggable autoplay :show-dots="false" show-arrow>
    <img class="carousel-img" src="@/assets/material/carousel_1.jpg" />
    <img class="carousel-img" src="@/assets/material/carousel_3.png" />
    <img class="carousel-img" src="@/assets/material/carousel_2.png" />
    <img class="carousel-img" src="@/assets/material/carousel_4.png" />

    <template #arrow="{ total, currentIndex, prev, next }">
      <div
        class="w-full mx-auto bg-[#F5F5F5] md:(absolute top-0 h-468px bg-gray-900 bg-opacity-50) cursor-pointer"
      >
        <n-space
          justify="end"
          vertical
          class="h-full text-dark-900 px-4 py-6 md:(w-screen-md px-0 text-light-50) w-screen-sm mx-auto"
        >
          <div class="underline underline-2 underline-offset-2"> 精选 </div>
          <div class="text-xl"> 多功能陆地游泳器 </div>
          <div class="font-thin">随时随地想游泳就游泳🏊‍|可乐、海盐各种口味随心添|可关闭舱门</div>
          <div class="hover:(underline underline-offset-2)">去看看</div>
          <n-space class="space-x-2 items-center">
            <div>
              <i-grommet-icons-form-previous
                @click="prev"
                class="w-8 h-8 align-middle rounded-full border space-x-2 hover:(bg-black text-cool-gray-50) md:hover:(bg-cool-gray-50 text-black)"
              />
            </div>
            <div>
              <i-grommet-icons-form-next
                @click="next"
                class="w-8 h-8 rounded-full border align-middle hover:(bg-black text-cool-gray-50) md:hover:(bg-cool-gray-50 text-black)"
              />
            </div>

            <div class="my-auto"> {{ currentIndex + 1 }}/{{ total }}</div>
          </n-space>
        </n-space>
      </div>
    </template>
  </n-carousel>
</template>

<script setup lang="ts"></script>
<style scoped>
  .carousel-img {
    width: 100%;
    height: 468px;
    object-fit: cover;
  }
</style>
