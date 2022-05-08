<template>
  <p class="text-3xl text-green-500 mb-2">回报</p>
  <p class="text-gray-400 text-base font-thin mb-4">
    津贴是向支持者提供的奖励，以换取他们的支持。你可以编辑你的特惠细节，直到申请特惠为止。
    访问帮助中心，了解您可以提供的各种福利。
    很好，你的活动被很多人看好，我们都希望有一个方式支持你，能够把活动做的更好，授人玫瑰手留余香，也别忘了回报哦。
  </p>
  <n-button type="primary" @click="showModalRef = true" class="mb-4">新增</n-button>
  <n-table :bordered="false" :single-line="false">
    <thead>
      <tr>
        <th>标题</th>
        <th>金额</th>
        <th>描述</th>
        <th>图片</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="item in perkList" :key="item.id">
        <td>{{ item.title }}</td>
        <td>{{ item.price }}</td>
        <td>{{ item.description }}</td>
        <td> <n-image width="100" :src="item.posterImg" /></td>
        <td>
          <n-button type="primary" @click="removePerk(item.id)">删除</n-button>
        </td>
      </tr>
    </tbody>
  </n-table>

  <n-modal v-model:show="showModalRef">
    <n-card
      style="width: 600px"
      title="新增支持激励"
      :bordered="false"
      size="huge"
      role="dialog"
      aria-modal="true"
    >
      <n-space vertical class="space-y-3">
        <div>
          <span>标题</span>
          <n-input placeholder="输入标题" v-model:value="newPerk.title" />
        </div>
        <div>
          <span>描述</span>
          <n-input type="textarea" placeholder="输入描述" v-model:value="newPerk.description" />
        </div>
        <div>
          <span>图片</span>
          <Upload v-model:filePath="newPerk.posterImg" :max="1" />
        </div>
        <n-input-number placeholder="输入费用" v-model:value="newPerk.price">
          <template #prefix> ￥ </template>
        </n-input-number>
        <div class="w-full flex">
          <n-button class="ml-auto" type="primary" @click="savePerk"> 添加</n-button>
        </div>
      </n-space>
    </n-card>
  </n-modal>
</template>
<script setup lang="ts">
  import { Dto } from '@/model';
  import { fetchRemovePerk, fetchSavePerk } from '@/service';
  import { ref, watch } from 'vue';
  const { campaignDetailId } = defineProps({
    campaignDetailId: {
      type: Number,
    },
  });
  const showModalRef = ref(false);
  const perkList = ref<Array<Partial<Dto.Perk>>>(Array(0));
  const newPerk = ref<Partial<Dto.Perk>>({ campaignDetailId: campaignDetailId });
  const removePerk = (id: number | undefined) => {
    if (id != undefined) {
      fetchRemovePerk([id]);
    }
    perkList.value = perkList.value.filter((obj) => obj.id !== id);
  };

  const savePerk = () => {
    fetchSavePerk(newPerk.value).then((res) => {
      if (res.data != null) {
        newPerk.value.id = res.data.id;
        perkList.value.push(newPerk.value);
      }
      showModalRef.value = false;
    });
  };
  watch(showModalRef, (n) => {
    if (n == true) {
      console.log('还原数据');
      newPerk.value = { campaignDetailId: campaignDetailId };
    }
  });
</script>
<style scoped></style>
