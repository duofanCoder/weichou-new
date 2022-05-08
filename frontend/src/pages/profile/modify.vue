<template>
  <!-- 修改密码 -->
  <div class="flex flex-col flex-1 w-full mx-auto justify-center max-w-235">
    <div class="flex flex-row items-center text-center">
      <router-link to="/profile">
        <i-ic-baseline-arrow-back class="w-8 h-8 mr-4 align-middle"
      /></router-link>
      <p class="font-medium text-3xl"> {{ info }} </p>
    </div>
    <!-- <n-divider class="w-screen" /> -->
    <div class="border pt-8 mt-6 rounded-xl bg-white max-w-135">
      <p class="text-sm px-6 mb-4">更新{{ info }}</p>
      <!-- w-50 -->
      <div class="flex text-base ml-6 mr-24 py-6 flex-col space-y-3">
        <n-input
          :class="[type == 'name' ? '' : 'hidden']"
          type="text"
          size="large"
          v-model:value="userInfoModel.nickname"
          placeholder="请输入"
        />
        <n-input
          :class="[type == 'wechat' ? '' : 'hidden']"
          type="text"
          size="large"
          v-model:value="userInfoModel.wechat"
          placeholder="请输入"
        />
        <n-input
          :class="[type == 'bilibili' ? '' : 'hidden']"
          type="text"
          size="large"
          v-model:value="userInfoModel.bilibili"
          placeholder="请输入"
        />
        <n-input
          :class="[type == 'mobileNumber' ? '' : 'hidden']"
          type="text"
          size="large"
          v-model:value="userInfoModel.mobile"
          placeholder="请输入"
        />
        <n-radio-group
          size="large"
          :class="[type == 'gender' ? '' : 'hidden']"
          :default-value="defaultGender"
          @update:value="choiceGender"
          class="flex flex-col space-y-6"
        >
          <n-radio value="true">男</n-radio>
          <n-radio value="false">女</n-radio>
        </n-radio-group>
        <n-date-picker
          :class="[type == 'birth' ? '' : 'hidden']"
          :default-value="defaultBirthTimeRef"
          type="date"
          @update:value="selectBirth"
          input-readonly
        />
      </div>

      <div class="flex flex-row-reverse ml-6 mr-24 py-6">
        <!--  @click="saveInfo" -->
        <n-button type="success" class="ml-5" @click="saveUserInfo">保存</n-button>
        <router-link to="/profile"> <n-button quaternary>取消</n-button></router-link>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
  import { fetchUpdateProfile } from '@/service';
  import { useUserStore } from '@/store';
  import { isNull, isUndefined } from '@/utils';
  import { computed, reactive, toRaw } from 'vue';
  import { useRoute } from 'vue-router';

  const { userInfo, updateUserInfo } = useUserStore();
  const userInfoModel = reactive(toRaw(userInfo));

  const defaultBirthTimeRef = computed(() => {
    if (userInfoModel.birth != null) {
      return new Date(userInfoModel.birth).getTime();
    } else {
      return null;
    }
  });
  const selectBirth = (_value: number | null, formattedValue: string | null) => {
    if (formattedValue) {
      userInfoModel.birth = formattedValue;
    }
  };
  const defaultGender = userInfoModel.gender ? 'true' : 'false';
  const choiceGender = (value: string) => {
    if (value == 'true') {
      userInfoModel.gender = true;
    } else {
      userInfoModel.gender = false;
    }
  };

  const route = useRoute();

  const infoMap = new Map([
    ['name', '姓名'],
    ['mobileNumber', '手机号'],
    ['birth', '生日'],
    ['gender', '性别'],
    ['wechat', '微信'],
    ['bilibili', '哔哩哔哩'],
  ]);
  const type = route.query.type?.toString();
  const info = computed(() => {
    if (!isUndefined(type)) {
      return infoMap.get(type as string);
    }
  });
  const saveUserInfo = async () => {
    const data = await fetchUpdateProfile(toRaw(userInfoModel));
    if (data.error == null) {
      updateUserInfo(userInfoModel);
      window.$message.success('信息已更新');
    }
  };
</script>
<style scoped></style>
