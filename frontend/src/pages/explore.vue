<template>
  <div class="flex flex-col">
    <div class="flex flex-col justify-center items-center h-60 w-full bg-dark-50 text-white">
      <p class="text-3xl font-bold"> 创造性的作品 </p>
      <p class="mt-3 text-sm px-5 md:(px-0)"
        >对于各地的创意寻求者,欢迎选择你感兴趣的项目进行,他们正期待着你的支持。</p
      >
    </div>

    <div class="flex gap-24 mt-16 w-full justify justify-center">
      <!-- PC端 -->
      <div class="flex-col font-semibold hidden sm:(flex)">
        <div class="flex flex-col space-y-3 text-base p-2">
          <p class="mb-4 text-lg">分类</p>
          <n-button
            text
            class="inline-block"
            @click="(condition.categoryId = 0), reloadCampaignIntro()"
            :class="{ 'text-green-500': condition.categoryId == 0 }"
            >所有</n-button
          >
          <n-button
            v-for="item in categoryListRef"
            :key="item.id"
            text
            @click="(condition.categoryId = item.id), reloadCampaignIntro()"
            class="inline-block"
            :class="{ 'text-green-500': condition.categoryId == item.id }"
          >
            {{ item.name }}
          </n-button>
        </div>

        <div class="mt-5 p-2">
          <p class="mb-6 text-xl font-semibold">活动状态</p>
          <n-radio-group v-model:value="radioNumberValueRef" name="campaignState">
            <n-space vertical>
              <n-radio :value="0" name="2"> 进行中 </n-radio>
              <n-radio :value="1" name="3"> 已结束 </n-radio>
            </n-space>
          </n-radio-group>
        </div>
      </div>

      <div class="max-w-140 px-2 lg:(max-w-210)">
        <div class="w-full flex gap-4 sm:(gap-8 px-0)">
          <n-input
            size="large"
            class="text-xl py-1 sm:(w-max-50)"
            type="text"
            v-model:value="condition.title"
            placeholder="搜索你感兴趣的活动"
          >
            <template #prefix>
              <i-ic-baseline-search class="w-6 h-6" />
            </template>
          </n-input>
          <n-button class="h-auto w-28" @click="reloadCampaignIntro">搜索</n-button>
        </div>

        <!-- Mobile端 -->
        <div class="sm:( hidden )">
          <div class="flex flex-wrap mb-2 gap-3 text-base py-2">
            <n-button
              text
              class="rounded-md px-2 hover:( cursor-pointer bg-gray-300)"
              @click="(condition.categoryId = 0), reloadCampaignIntro()"
              :class="{ 'bg-gray-300': condition.categoryId == 0 }"
              >所有</n-button
            >
            <n-button
              v-for="item in categoryListRef"
              :key="item.id"
              text
              class="rounded-md px-2 hover:( cursor-pointer bg-gray-300)"
              @click="(condition.categoryId = item.id), reloadCampaignIntro()"
              :class="{ 'bg-gray-300': condition.categoryId == item.id }"
            >
              {{ item.name }}
            </n-button>
          </div>

          <n-radio-group v-model:value="radioNumberValueRef" name="campaignState">
            <n-space vertical>
              <n-radio :value="0" name="2"> 进行中 </n-radio>
              <n-radio :value="1" name="3"> 已结束 </n-radio>
            </n-space>
          </n-radio-group>
        </div>
        <n-grid cols="1 s:2 m:3 " responsive="screen" :x-gap="12" :y-gap="8">
          <template v-for="item in campaignListRef" :key="item.id">
            <n-grid-item>
              <CampaignCard :campaign="item" class="mt-4 xs:mx-auto" />
            </n-grid-item>
          </template>
        </n-grid>
        <!-- <div
          class="flex text-base h-30 items-center justify-center py-17 font-semibold gap-4 md:(gap-7)"
        >
          <div class="flex pl-2 py-1 rounded-xl hover:(bg-gray-200 cursor-pointer)">
            <i-ic-baseline-arrow-back-ios />
          </div>
          <div class="px-2 w-auto rounded-xl text-center hover:(bg-gray-200 cursor-pointer)">1</div>
          <div class="px-2 w-auto rounded-xl text-center hover:(bg-gray-200 cursor-pointer)">2</div>
          <div class="px-2 w-auto rounded-xl text-center hover:(bg-gray-200 cursor-pointer)">3</div>
          <div class="px-2 w-auto rounded-xl text-center hover:(bg-gray-200 cursor-pointer)">4</div>
          <div class="px-2 w-auto rounded-xl text-center hover:(bg-gray-200 cursor-pointer)">5</div>
          <div class="flex pl-2 py-1 rounded-xl hover:( bg-gray-200 cursor-pointer)">
            <i-ic-baseline-arrow-forward-ios />
          </div>
        </div> -->

        <div
          v-if="paginationInfoRef.pageCount != 0"
          class="flex text-base h-30 items-center justify-center py-17 font-semibold gap-4 md:(gap-7)"
        >
          <n-pagination
            v-model:page="paginationInfoRef.page"
            :page-count="paginationInfoRef.pageCount"
          />
        </div>
        <div class="flex flex-col w-full px-40 pb-17 font-semibold gap-4 md:(gap-7)" v-else>
          <img class="mx-auto w-max-600px h-auto" src="@/assets/img/surr-404.png" />
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
  import { CampaignCard } from '@/components';
  import { Dto } from '@/model';
  import { fetchQueryCampaignIntro, fetchQueryCategory } from '@/service';
  import { PaginationInfo } from 'naive-ui';
  import { onMounted, reactive, ref, watch } from 'vue';
  const campaignListRef = ref<Array<Dto.CampaignIntro>>([]);
  const radioNumberValueRef = ref(0);
  const condition = reactive<Partial<Condition.Campaign>>({
    pageSize: 9,
    pageNum: 0,
    categoryId: 0,
    isFinish: false,
  });
  const paginationInfoRef = reactive<Partial<PaginationInfo>>({});

  const categoryListRef = ref<Array<Dto.Category>>([]);

  const categoryLoad = () => {
    fetchQueryCategory(condition).then((res) => {
      categoryListRef.value = res.data?.data;
    });
  };

  const reloadCampaignIntro = () => {
    fetchQueryCampaignIntro(condition).then((res) => {
      if (res.data != null) {
        campaignListRef.value = res.data.data;
        paginationInfoRef.pageCount = res.data.totalPage;
      }
    });
  };

  onMounted(() => {
    categoryLoad();
    reloadCampaignIntro();
  });

  watch(radioNumberValueRef, (n) => {
    if (n == 0) {
      condition.isFinish = false;
    } else {
      condition.isFinish = true;
    }
    reloadCampaignIntro();
  });
</script>
<style scoped></style>
