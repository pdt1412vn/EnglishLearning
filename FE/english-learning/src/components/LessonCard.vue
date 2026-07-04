<template>
  <div class="card shadow h-100">
    <div class="card-body">
      <div class="d-flex justify-content-between align-items-start">
        <div>
          <h5 class="fw-bold mb-1">{{ lesson.title }}</h5>
          <p class="text-muted small mb-2">{{ lesson.description }}</p>
        </div>
        <span class="badge bg-secondary">{{ lesson.duration }} phút</span>
      </div>

      <div class="d-flex gap-2 mt-3">
        <router-link
          :to="'/vocabulary/' + lesson.id"
          class="btn btn-outline-success btn-sm"
        >
          Từ vựng
        </router-link>
        <router-link
          :to="'/quiz/' + lesson.id"
          class="btn btn-outline-primary btn-sm"
        >
          Quiz
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  lesson: Object,
})
</script>
