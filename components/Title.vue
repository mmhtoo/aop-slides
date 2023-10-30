<script setup lang="ts">
const props = defineProps({
  value : {
    type : String
  }
})
</script>

<template>
  <h1 style="color:#5B9E48">{{ props.value }}</h1>
</template>