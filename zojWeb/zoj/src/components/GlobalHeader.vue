<template>
  <a-row id="globalHeader" :wrap="false">
    <a-col flex="15%"> </a-col>
    <a-col flex="auto">
      <a-menu
        mode="horizontal"
        :selected-keys="selectedKeys"
        @menu-item-click="doMenuClick"
      >
        <a-menu-item
          key="0"
          :style="{ padding: 0, marginRight: '38px' }"
          disabled
        >
          <div class="title-bar">
            <img class="logo" src="../../public/image.png" />
            <!-- <div class="title">zoj</div> -->
          </div>
        </a-menu-item>
        <a-menu-item v-for="item in visibleRoutes" :key="item.path">
          <icon-mosaic />
          {{ item.name }}
        </a-menu-item>
      </a-menu>
    </a-col>
    <a-col flex="20%" style="margin: auto 0">
      <a-popover title="个人信息">
        <a-button @click="onLogin" class="Name">
          {{ store.state.user?.loginUser?.userName ?? "未登录" }}</a-button
        >
        <template #content>
          <p>
            用户昵称： {{ store.state.user?.loginUser?.userName ?? "未登录" }}
          </p>
          <a-button type="outline" @click="onLoginout">退出登录</a-button>
        </template>
      </a-popover>
    </a-col>
  </a-row>
</template>

<script setup lang="ts">
import { routes } from "../router/routes";
import { useRoute, useRouter } from "vue-router";
import { computed, ref } from "vue";
import { useStore } from "vuex";
import checkAccess from "@/access/checkAccess";
import ACCESS_ENUM from "@/access/accessEnum";
import { UserControllerService } from "../../generated";
const router = useRouter();
const store = useStore();
// 展示在菜单的路由数组
const visibleRoutes = computed(() => {
  return routes.filter((item, index) => {
    if (item.meta?.hideInMenu) {
      return false;
    }
    //根据权限过滤菜单;
    if (
      !checkAccess(store.state.user.loginUser, item?.meta?.access as string)
    ) {
      return false;
    }
    return true;
  });
});

// 默认主页
const selectedKeys = ref(["/"]);

// 路由跳转后，更新选中的菜单项
router.afterEach((to, from, failure) => {
  selectedKeys.value = [to.path];
});
const onLoginout = async () => {
  await UserControllerService.userLogoutUsingPost();
  location.reload();
};
const userName = computed(
  () => store.state.user.loginUser?.userName || "未登录"
);

const onLogin = () => {
  if (userName.value === "未登录") {
    router.push({ name: "用户登录" });
  } else {
    console.log("User is already logged in");
  }
};

const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};
</script>

<style scoped>
.title-bar {
  display: flex;
  align-items: center;
}

.title {
  color: #444;
  margin-left: 16px;
}

.logo {
  height: 48px;
}
.arco-btn-secondary,
.arco-btn-secondary[type="button"],
.arco-btn-secondary[type="submit"] {
  /* color: rgb(80 95 116); */
  background-color: rgb(255 255 255);
  border: 1px solid transparent;
}
.Name:hover {
  color: #22bfa7;
}
</style>
