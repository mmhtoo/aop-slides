<script setup lang="ts">
import {ref} from 'vue'

const count = ref(0)
</script>

<template>
  <div flex="~" w="min" border="~ main rounded-md">
    <button
      border="r main"
      p="2"
      font="mono"
      outline="!none"
      hover:bg="gray-400 opacity-20"
      @click="--count">
      -
    </button>
    <span m="auto" p="2">{{ count }}</span>
    <button
      border="l main"
      p="2"
      font="mono"
      outline="!none"
      hover:bg="gray-400 opacity-20"
      @click="++count">
      +
    </button>
  </div>
</template>
