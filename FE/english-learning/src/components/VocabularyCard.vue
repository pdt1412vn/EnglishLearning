<template>
  <div class="card shadow h-100 text-center">
    <div class="card-body">
      <img
        v-if="vocab.image"
        :src="vocab.image"
        class="mb-3 rounded"
        style="width:80px;height:80px;object-fit:cover"
        alt=""
      />

      <h4 class="fw-bold text-primary">{{ vocab.englishWord }}</h4>
      <p class="text-muted mb-1">{{ vocab.pronunciation }}</p>
      <p class="fw-semibold">{{ vocab.vietnameseMeaning }}</p>
      <p v-if="vocab.exampleSentence" class="fst-italic small text-secondary">
        "{{ vocab.exampleSentence }}"
      </p>

      <audio v-if="vocab.audio" :src="vocab.audio" controls class="w-100 mt-2"></audio>
    </div>
  </div>
</template>

<script setup>
defineProps({
  vocab: Object,
})
</script>
