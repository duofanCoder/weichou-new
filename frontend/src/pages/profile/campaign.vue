<template>
  <div class="flex flex-col flex-1 w-full mx-auto max-w-235">
    <div class="text-center">
      <p class="font-medium text-3xl mt-12"> 活动 </p>
    </div>

    <div class="border py-5 mt-6 rounded-xl bg-white space-y-3">
      <p class="text-2xl px-6">您发起的活动</p>
      <p class="px-6 tracking-widest mb-4">活动可不是简简单单的发起就会成功的。</p>

      <template v-for="item in myCompaigns" :key="item.id">
        <div
          @contextmenu="handleContextMenu($event, item.id)"
          class="flex text-base rounded-lg mx-6 border-b hover:(bg-true-gray-100 cursor-pointer )"
        >
          <div>
            <img v-if="item.cardImage" :src="item.cardImage" alt="" />
            <img
              v-else
              src="@/assets/material/ipad.jpg"
              class="w-60 h-40 object-cover rounded-lg"
              alt=""
            />
          </div>
          <div class="inline-flex flex-col flex-1 ml-3 pt-2">
            <p class="text-xl">{{ item.title }}</p>
            <p class="text-base text-gray-500 mt-2">
              {{ item.description }}
            </p>
            <div class="mt-auto mb-2 space-x-3">
              <n-tag type="info"> {{ CampaignState[item.state] }} </n-tag>
              <n-time :time="4444221122122" format="yyyy年M月d日h时m分" />
            </div>
          </div>
        </div>
      </template>

      <n-dropdown
        placement="bottom-start"
        trigger="manual"
        :x="xRef"
        :y="yRef"
        :options="options"
        :show="showDropdownRef"
        :on-clickoutside="onClickoutside"
        @select="handleSelect"
      />
    </div>

    <div class="border py-5 mt-6 rounded-xl bg-white space-y-3">
      <p class="text-2xl px-6">您关注的活动</p>
      <p class="px-6 tracking-widest mb-4">活动可不是简简单单的发起就会成功的。</p>

      <div class="flex text-base rounded-lg mx-6 border-b hover:(bg-true-gray-100 cursor-pointer )">
        <!-- <div class="inline-flex self-center text-gray-500 w-50">照片</div> -->
        <!-- <div class="inline-flex self-center align-middle">更改照片可帮助您个性化您的账号</div> -->
        <!-- <div class="ml-auto items-center inline-flex"> -->
        <!-- <n-avatar :size="56" round src="/src/assets/material/avatar.png"> </n-avatar> -->
        <!-- </div> -->
        <div>
          <img src="@/assets/material/ipad.jpg" class="w-60 h-40 object-cover rounded-lg" alt="" />
        </div>
        <div class="inline-flex flex-col flex-1 ml-3 pt-2">
          <p class="text-xl">这是个标题关于这个活动的</p>
          <p class="text-base text-gray-500 mt-2">
            这是这个活动的简单介绍,这是这个活动的简单介绍这是这个活动的简单介绍这是这个活动的简单介绍这是这个活动的简单介绍这是这个活动的简单介绍这</p
          >
          <div class="mt-auto mb-2 inline-flex space-x-3">
            <n-tag type="success"> 进行中 </n-tag>
            <n-time :time="4444221122122" class="self-center" format="yyyy年M月d日h时m分" />
            <i-mdi-cards-heart-outline
              v-if="false"
              class="self-center text-gray-400 cursor-pointer hover:(text-pink-600)"
            />
            <i-mdi-cards-heart v-else class="self-center text-pink-600 cursor-pointer" />
          </div>
        </div>
      </div>

      <div class="flex text-base rounded-lg mx-6 border-b hover:(bg-true-gray-100 cursor-pointer )">
        <!-- <div class="inline-flex self-center text-gray-500 w-50">照片</div> -->
        <!-- <div class="inline-flex self-center align-middle">更改照片可帮助您个性化您的账号</div> -->
        <!-- <div class="ml-auto items-center inline-flex"> -->
        <!-- <n-avatar :size="56" round src="/src/assets/material/avatar.png"> </n-avatar> -->
        <!-- </div> -->
        <div>
          <img src="@/assets/material/ipad.jpg" class="w-60 h-40 object-cover rounded-lg" alt="" />
        </div>
        <div class="inline-flex flex-col flex-1 ml-3 pt-2">
          <p class="text-xl">这是个标题关于这个活动的</p>
          <p class="text-base text-gray-500 mt-2">
            这是这个活动的简单介绍,这是这个活动的简单介绍这是这个活动的简单介绍这是这个活动的简单介绍这是这个活动的简单介绍这是这个活动的简单介绍这</p
          >
          <div class="mt-auto mb-2 space-x-3">
            <n-tag> 已结束 </n-tag>
            <n-time :time="4444221122122" format="yyyy年M月d日h时m分" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
  import { useUserStore } from '@/store';
  import { ref, nextTick, unref, computed } from 'vue';
  import { Love } from '@/components';
  import { CampaignState } from '@/enum';
  import { getCampaignStateVal } from '@/enum';

  //  我的活动区域菜单功能 start
  const xRef = ref(0);
  const yRef = ref(0);
  const showDropdownRef = ref();

  const options = [
    {
      label: '编辑活动',
      key: 'edit',
    },
    {
      label: '删除活动',
      key: 'delete',
    },
  ];

  const handleContextMenu = (e: MouseEvent, id: number) => {
    e.preventDefault();
    showDropdownRef.value = false;
    nextTick().then(() => {
      showDropdownRef.value = true;
      xRef.value = e.clientX;
      yRef.value = e.clientY;
    });
  };

  const handleSelect = (key: string | number) => {
    showDropdownRef.value = false;
  };

  const onClickoutside = () => {
    showDropdownRef.value = false;
  };

  //  我的活动区域菜单功能 end
  const userStore = useUserStore();
  const myCompaigns = unref(userStore.getMyCompaigns);
  const loveCompaigns = unref(userStore.getLoveCompaigns);

  myCompaigns.forEach((c) => console.log(c));
  loveCompaigns.forEach((c) => console.log(c));
</script>

<style scoped></style>
