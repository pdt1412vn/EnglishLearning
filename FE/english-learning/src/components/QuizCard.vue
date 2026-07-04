<template>
  <div class="card shadow mb-3">
    <div class="card-body">
      <h6 class="fw-bold">{{ quiz.question }}</h6>

      <div v-for="answer in quiz.answers" :key="answer.id" class="mt-2">
        <div
          class="p-2 rounded"
          :class="answer.correct ? 'bg-success text-white' : 'bg-light'"
        >
          {{ answer.answerContent }}
          <span v-if="answer.correct" class="ms-1">✅</span>
        </div>
      </div>

      <p v-if="quiz.explanation" class="mt-2 text-muted small fst-italic">
        Giải thích: {{ quiz.explanation }}
      </p>
    </div>
  </div>
</template>

<script setup>
defineProps({
  quiz: Object,
})
</script>
