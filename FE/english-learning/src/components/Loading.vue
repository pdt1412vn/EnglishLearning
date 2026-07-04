<template>
  <div class="d-flex justify-content-center align-items-center py-5">
    <div class="spinner-border text-primary me-3" role="status"></div>
    <span class="text-muted">{{ message }}</span>
  </div>
</template>

<script setup>
defineProps({
  message: {
    type: String,
    default: 'Đang tải...',
  },
})
</script>
