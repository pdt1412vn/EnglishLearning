<template>
  <div>

    <Navbar />

    <!-- Banner -->
    <section class="bg-primary text-white py-5">
      <div class="container text-center">

        <h1 class="display-4 fw-bold">
          English Learning
        </h1>

        <p class="lead">
          Học tiếng Anh dễ dàng cùng hàng trăm bài học miễn phí
        </p>

        <router-link
            class="btn btn-light btn-lg"
            to="/courses">

          Bắt đầu học

        </router-link>

      </div>
    </section>

    <!-- Khóa học -->
    <section class="container my-5">

      <h2 class="mb-4">
        Khóa học nổi bật
      </h2>

      <div class="row">

        <div
            class="col-md-4 mb-4"
            v-for="course in courses"
            :key="course.id">

          <CourseCard
              :course="course"/>

        </div>

      </div>

    </section>

    <Footer/>

  </div>
</template>

<script setup>

import {ref,onMounted} from "vue";

import Navbar from "../components/Navbar.vue";
import Footer from "../components/Footer.vue";
import CourseCard from "../components/CourseCard.vue";

import {getCourses} from "../api/course";

const courses=ref([]);

onMounted(async()=>{

  const res=await getCourses();

  courses.value=res.data.data;

});

</script>