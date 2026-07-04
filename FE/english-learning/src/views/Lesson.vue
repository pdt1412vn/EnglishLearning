<template>
  <div>
    <div class="container mt-5">
      <div v-if="loading" class="text-center py-5">
        <div class="spinner-border text-primary"></div>
      </div>

      <div v-else>
        <h2 class="fw-bold mb-4">Bài học của khóa học</h2>

        <div v-if="lessons.length === 0" class="alert alert-info">
          Chưa có bài học nào.
        </div>

        <div class="list-group">
          <div
            v-for="lesson in lessons"
            :key="lesson.id"
            class="list-group-item list-group-item-action d-flex justify-content-between align-items-center"
          >
            <div>
              <h5 class="mb-1">{{ lesson.title }}</h5>
              <small class="text-muted">{{ lesson.description }}</small>
              <div class="mt-1">
                <span class="badge bg-secondary me-2">{{ lesson.duration }} phút</span>
                <span class="badge bg-info">Thứ tự: {{ lesson.lessonOrder }}</span>
              </div>
            </div>

            <div class="d-flex gap-2">
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
                Làm quiz
              </router-link>
            </div>
          </div>
        </div>

        <router-link to="/courses" class="btn btn-secondary mt-4">
          ← Quay lại khóa học
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getLessonByCourse } from '../api/lesson'

const route = useRoute()
const lessons = ref([])
const loading = ref(true)

onMounted(async () => {
  try {
    const res = await getLessonByCourse(route.params.id)
    lessons.value = res.data.data
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }
})
</script>
