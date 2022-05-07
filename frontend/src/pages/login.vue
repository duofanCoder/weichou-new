<template>
  <div class="w-full h-[calc(100vh-4rem)] flex items-center justify-center bg-gray-300">
    <div class="w-55vw">
      <n-grid :cols="2">
        <n-gi>
          <div class="bg-warm-gray-900 h-full flex">
            <img src="@/assets/img/pablo-book-lover.png" class="my-auto"/>
          </div>
        </n-gi>
        <n-gi>
          <div class="bg-white items-center h-full flex flex-col">
            <div class="text-3xl mb-15 mt-20 font-extrabold"> WelCome！</div>
            <div class="px-3">
              <n-tabs
                  class="card-tabs"
                  default-value="login"
                  size="large"
                  animated
                  @before-leave="onTabsChageEvent"
                  style="margin: 0 -4px"
                  pane-style="padding-left: 4px; padding-right: 4px; box-sizing: border-box;"
              >
                <n-tab-pane name="login" tab="登录">
                  <n-form>
                    <n-form-item-row label="用户名">
                      <n-input v-model:value="userActionForm.username"/>
                    </n-form-item-row>
                    <n-form-item-row label="密码">
                      <n-input type="password" v-model:value="userActionForm.password"/>
                    </n-form-item-row>
                  </n-form>
                  <n-button type="primary" @click="submitAction('login')" block secondary strong>
                    登录
                  </n-button>
                </n-tab-pane>
                <n-tab-pane name="register" tab="注册">
                  <n-form>
                    <n-form-item-row label="昵称">
                      <n-input v-model:value="userActionForm.nickname"/>
                    </n-form-item-row>
                    <n-form-item-row label="用户名">
                      <n-input v-model:value="userActionForm.username"/>
                    </n-form-item-row>
                    <n-form-item-row label="邮箱">
                      <n-input v-model:value="userActionForm.email"/>
                    </n-form-item-row>
                    <n-form-item-row label="密码">
                      <n-input v-model:value="userActionForm.password"/>
                    </n-form-item-row>
                    <n-form-item-row label="重复密码">
                      <n-input v-model:value="userActionForm.enPassword"/>
                    </n-form-item-row>
                  </n-form>
                  <n-button type="primary" @click="submitAction('register')" block secondary strong>
                    注册
                  </n-button>
                </n-tab-pane>
              </n-tabs>
            </div>
            <div class="mb-15"></div>
          </div>
        </n-gi>
      </n-grid>
    </div>
  </div>
</template>

<script setup lang="ts">
import {fetchRegisterUser} from '@/service';
import {useUserStore} from '@/store';
import {reactive} from 'vue';

interface UserActionForm {
  username: string;
  nickname: string;
  email: string;
  password: string;
  enPassword: string;
}

const userActionForm = reactive<Partial<UserActionForm>>({});
const userActionFormReset = (userActionForm: Partial<UserActionForm>) => {
  userActionForm.username = 'duofan';
  userActionForm.password = '123456';
  userActionForm.email = '';
  userActionForm.nickname = '';
  userActionForm.enPassword = '';
};
const userStore = useUserStore();

const clickAction = (action: string) => {
  switch (action) {
    case 'login':
      userActionFormReset(userActionForm);
      break;
    case 'register':
      userActionFormReset(userActionForm);
      break;
    default:
      return;
  }
};

const submitAction = (action: string) => {
      switch (action) {
        case 'login':
          if (userActionForm.username != null && userActionForm.password != null) {
            userStore.login(userActionForm.username, userActionForm.password, false);
          }

          break;
        case 'register':
          if (userActionForm.enPassword!=userActionForm.password){
            window.$message.info("两次输入密码不一致！")
            return;
          }
          if (userActionForm.username != null && userActionForm.password != null) {
            fetchRegisterUser(userActionForm).then((res) => {
              if (res.data != null) {
                window.$message.success('注册成功！');
              }
            });
          }
          break;
        default:
          return;
      }
    }
;

const onTabsChageEvent = (name: string, oldName: string) => {
  clickAction(name);
  return true;
};
</script>

<style scoped></style>
