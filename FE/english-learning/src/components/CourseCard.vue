<template>

<div class="card shadow h-100">

<img
        :src="course.image"
        class="card-img-top"
        style="height:220px;object-fit:cover">

<div class="card-body">

<h5 class="fw-bold">

{{course.title}}

</h5>

<p>

{{course.description}}

</p>

<span
class="badge bg-success">

{{course.level}}

</span>

</div>

<div class="card-footer bg-white">

<router-link
:to="'/course/'+course.id"
class="btn btn-primary w-100">

Xem khóa học

</router-link>

</div>

</div>

</template>

<script setup>

defineProps({

course:Object

})

</script>