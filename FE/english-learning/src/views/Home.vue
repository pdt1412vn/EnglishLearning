<template>
  <div>
    <!-- Banner -->
    <section class="bg-primary text-white py-5">
      <div class="container text-center">
        <h1 class="display-4 fw-bold">English Learning</h1>
        <p class="lead">Học tiếng Anh dễ dàng cùng hàng trăm bài học miễn phí</p>
        <router-link class="btn btn-light btn-lg" to="/courses">Bắt đầu học</router-link>
      </div>
    </section>

    <!-- Khóa học nổi bật -->
    <section class="container my-5">
      <h2 class="mb-4">Khóa học nổi bật</h2>
      <div v-if="loading" class="text-center py-4">
        <div class="spinner-border text-primary"></div>
      </div>
      <div v-else class="row">
        <div class="col-md-4 mb-4" v-for="course in courses" :key="course.id">
          <CourseCard :course="course" />
        </div>
      </div>
    </section>

    <Footer />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import Footer from '../components/Footer.vue'
import CourseCard from '../components/CourseCard.vue'
import { getCourses } from '../api/course'

const courses = ref([])
const loading = ref(true)

onMounted(async () => {
  try {
    const res = await getCourses()
    courses.value = res.data.data || []
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }
})
</script>