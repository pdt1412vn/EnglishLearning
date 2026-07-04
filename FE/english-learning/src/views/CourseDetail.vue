<template>

<div>

<div class="container mt-5">

<div class="row">

<div class="col-md-5">

<img
:src="course.image"
class="img-fluid rounded">

</div>

<div class="col-md-7">

<h2>

{{course.title}}

</h2>

<p>

{{course.description}}

</p>

<h5>

Cấp độ

<span class="badge bg-success">

{{course.level}}

</span>

</h5>

<router-link
:to="'/lesson/'+course.id"
class="btn btn-primary mt-3">

Bắt đầu học

</router-link>

</div>

</div>

</div>

<Footer/>

</div>

</template>

<script setup>

import {ref,onMounted} from "vue";

import {useRoute} from "vue-router";

import Footer from "../components/Footer.vue";

import {getCourse} from "../api/course";

const route=useRoute();

const course=ref({});

const loadCourse=async()=>{

const res=await getCourse(route.params.id);

course.value=res.data.data;

}

onMounted(()=>{

loadCourse();

})

</script>