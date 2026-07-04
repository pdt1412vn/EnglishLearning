<template>

  <div>

    <div class="container mt-5">

      <h2 class="fw-bold mb-4">
        Danh sách khóa học
      </h2>

      <div class="row">

        <div
            class="col-lg-4 col-md-6 mb-4"
            v-for="course in courses"
            :key="course.id">

          <CourseCard
              :course="course"/>

        </div>

      </div>

    </div>

    <Footer/>

  </div>

</template>

<script setup>

import {ref,onMounted} from "vue";

import Footer from "../components/Footer.vue";
import CourseCard from "../components/CourseCard.vue";

import {getCourses} from "../api/course";

const courses=ref([]);

const loadCourses=async()=>{

  try{

    const response=await getCourses();

    courses.value=response.data.data;

  }catch(e){

    console.log(e);

  }

}

onMounted(()=>{

  loadCourses();

})

</script>