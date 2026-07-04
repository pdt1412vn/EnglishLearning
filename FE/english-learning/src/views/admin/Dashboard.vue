<template>
  <div class="container-fluid mt-4">
    <h2 class="fw-bold mb-4">Dashboard Admin</h2>

    <div class="row g-4">
      <div class="col-md-3">
        <div class="card text-white bg-primary shadow">
          <div class="card-body">
            <h5 class="card-title">Khóa học</h5>
            <p class="display-6">{{ stats.courses }}</p>
          </div>
        </div>
      </div>
      <div class="col-md-3">
        <div class="card text-white bg-success shadow">
          <div class="card-body">
            <h5 class="card-title">Bài học</h5>
            <p class="display-6">{{ stats.lessons }}</p>
          </div>
        </div>
      </div>
      <div class="col-md-3">
        <div class="card text-white bg-warning shadow">
          <div class="card-body">
            <h5 class="card-title">Từ vựng</h5>
            <p class="display-6">{{ stats.vocabularies }}</p>
          </div>
        </div>
      </div>
      <div class="col-md-3">
        <div class="card text-white bg-danger shadow">
          <div class="card-body">
            <h5 class="card-title">Quiz</h5>
            <p class="display-6">{{ stats.quizzes }}</p>
          </div>
        </div>
      </div>
    </div>

    <div class="mt-5">
      <h5>Điều hướng nhanh</h5>
      <div class="d-flex gap-2 flex-wrap mt-2">
        <router-link to="/admin/courses" class="btn btn-outline-primary">Quản lý khóa học</router-link>
        <router-link to="/admin/lessons" class="btn btn-outline-success">Quản lý bài học</router-link>
        <router-link to="/admin/vocabularies" class="btn btn-outline-warning">Quản lý từ vựng</router-link>
        <router-link to="/admin/quizzes" class="btn btn-outline-danger">Quản lý quiz</router-link>
        <router-link to="/admin/users" class="btn btn-outline-dark">Quản lý người dùng</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, onMounted } from 'vue'
import { getCourses } from '../../api/course'
import { getLessonByCourse } from '../../api/lesson'

const stats = reactive({ courses: 0, lessons: 0, vocabularies: 0, quizzes: 0 })

onMounted(async () => {
  try {
    const res = await getCourses()
    const courses = res.data.data || []
    stats.courses = courses.length
  } catch (e) {
    console.error(e)
  }
})
</script>
