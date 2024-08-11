<template>
  <div id="homeView">
    <a-typography style="width: 60%; margin: 0 auto">
      <a-typography-title> 注意： </a-typography-title>
      <a-typography-paragraph>
        提交题目请严格按照以下格式提交：
      </a-typography-paragraph>
      <a-typography-paragraph>
        <a-typography-text mark>类名</a-typography-text
        >为Main,<a-typography-text mark>输入</a-typography-text
        >在main方法中输入，<a-typography-text mark>输出</a-typography-text
        >为System.out.print()
      </a-typography-paragraph>
      <a-typography-title :heading="2">例题：a+b</a-typography-title>
      <a-typography-paragraph>
        <MdEditor :value="example" :readonly="true" />
      </a-typography-paragraph>
    </a-typography>
  </div>
</template>

<script setup lang="ts">
import MdViewer from "@/components/MdViewer.vue";
import MdEditor from "@/components/MdEditor.vue";
import { ref } from "vue";

const example = ref(
  "public class Main {\npublic static void main(String[] args) {\nint a = Integer.parseInt(args[0]);\nint b = Integer.parseInt(args[1]);\nSystem.out.print((a + b));}\n}"
);
</script>
<style></style>
