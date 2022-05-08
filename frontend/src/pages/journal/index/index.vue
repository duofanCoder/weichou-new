<template>
  <div class="w-full h-screen-md px-20">
    <div class="mt-8">
      <n-button @click="router.push({ name: 'journal-edit', query: { campaginId: campaginId } })"
        >新增</n-button
      >
    </div>

    <template v-for="item in journalList">
      <div class="mx-auto max-w-180">
        <div class="flex h-full">
          <div class="flex h-full">
            <img class="w-70 h-40 my-auto" :src="item.posterImg" />
          </div>
          <div class="px-4 py-4 w-max-120">
            <p class="font-semibold text-2xl mb-1">{{ item.title }}</p>
            <n-ellipsis :tooltip="false" :line-clamp="3" class="text-base text-gray-500 mt-2">
              <p class="h-72px">{{ item.description }} </p>
            </n-ellipsis>
            <n-space>
              <n-button
                @click="
                  router.push({
                    name: 'journal-edit',
                    query: { campaginId: campaginId, journalId: item.id },
                  })
                "
                >编辑</n-button
              >
              <n-button @click="removeJournal(item.id)">删除</n-button>
            </n-space>
          </div>
        </div>
      </div>
    </template>
  </div>
</template>
<script setup lang="ts">
  import { Dto, JournalType } from '@/model';
  import { fetchQueryJournal, fetchRemoveJournal } from '@/service';
  import { computed, ref } from 'vue';
  import { useRoute, useRouter } from 'vue-router';

  const router = useRouter();
  const route = useRoute();
  const campaginId = computed(() => {
    return Number(route.query.campaginId);
  });

  const journalList = ref<Array<Dto.Journal>>();

  fetchQueryJournal({
    pageSize: 999,
    journalType: JournalType.JOURNAL,
    campaignId: campaginId.value,
  }).then((res) => {
    if (res.data != null) {
      journalList.value = res.data.data;
    }
  });

  const removeJournal = (id: number) => {
    fetchRemoveJournal([id]);
    journalList.value = journalList.value?.filter((item) => item.id != id);
  };
</script>
<style scoped></style>
