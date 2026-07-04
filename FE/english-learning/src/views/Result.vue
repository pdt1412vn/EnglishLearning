<template>
  <div class="container mt-5 text-center">
    <div class="card shadow p-5 mx-auto" style="max-width:500px">
      <div class="display-1 mb-3">
        {{ percent >= 80 ? '🏆' : percent >= 50 ? '👍' : '😢' }}
      </div>

      <h2 class="fw-bold mb-2">Kết quả</h2>

      <p class="fs-4">
        Bạn đúng <strong class="text-success">{{ score }}</strong> /
        <strong>{{ total }}</strong> câu
      </p>

      <div class="progress mb-3" style="height:20px">
        <div
          class="progress-bar"
          :class="percent >= 80 ? 'bg-success' : percent >= 50 ? 'bg-warning' : 'bg-danger'"
          :style="{ width: percent + '%' }"
        >
          {{ percent }}%
        </div>
      </div>

      <p class="text-muted">
        {{ percent >= 80 ? 'Xuất sắc!' : percent >= 50 ? 'Khá tốt!' : 'Cố gắng hơn nhé!' }}
      </p>

      <div class="d-flex gap-2 justify-content-center mt-3">
        <button class="btn btn-outline-primary" @click="$router.back()">Làm lại</button>
        <router-link to="/courses" class="btn btn-primary">Về trang khóa học</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const score = computed(() => Number(route.query.score) || 0)
const total = computed(() => Number(route.query.total) || 0)
const percent = computed(() =>
  total.value > 0 ? Math.round((score.value / total.value) * 100) : 0,
)
</script>
