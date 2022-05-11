<template>
  <div>
    <div class="text-2xl font-light mt-12"> 热门分类</div>
    <div class="flex justify-between space-x-6 pt-4 overflow-x-auto">
      <div
        @click="router.push({ name: 'explore', params: { categoryId: item.id } })"
        v-for="item in data"
        :key="item.id"
      >
        <div class="flex flex-col p-5 px-9 rounded-xl hover:( cursor-pointer bg-light-500 )">
          <n-el style="color: var(--primary-color)">
            <n-icon size="70">
              <component :is="iconifyRender(item.icon)" />
            </n-icon>
          </n-el>
          <p class="text-base text-center mt-4"> {{ item.name }} </p>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
  import { onMounted, reactive, ref, toRaw, unref } from 'vue';
  import { fetchQueryCategory } from '@/service';
  import { iconifyRender } from '@/utils';
  import { useRouter } from 'vue-router';
  import { Condition, Dto } from '@/model';

  const router = useRouter();
  const data = ref<Array<Dto.Category>>([]);
  const condition = reactive<Partial<Condition.Common>>({ pageSize: 999 });
  const reload = () => {
    fetchQueryCategory(condition).then((res) => {
      if (res.data != null) {
        data.value = res.data.data;
      }
    });
  };

  onMounted(() => {
    reload();
  });
</script>
<style scoped></style>
