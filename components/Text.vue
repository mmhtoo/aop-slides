<script setup lang="ts">
const props = defineProps({
  value : {
    type : String
  }
})
</script>

<template>
  <span style="color:#5B9E48;">{{ props.value }}</span>
</template>