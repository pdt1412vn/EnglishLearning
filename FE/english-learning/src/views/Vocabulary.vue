<template>
  <div>
    <div class="container mt-5">
      <div v-if="loading" class="text-center py-5">
        <div class="spinner-border text-primary"></div>
      </div>

      <div v-else>
        <h2 class="fw-bold mb-4">Từ vựng bài học</h2>

        <div v-if="vocabularies.length === 0" class="alert alert-info">
          Chưa có từ vựng nào.
        </div>

        <div class="row">
          <div
            v-for="vocab in vocabularies"
            :key="vocab.id"
            class="col-md-4 col-sm-6 mb-4"
          >
            <VocabularyCard :vocab="vocab" />
          </div>
        </div>

        <button @click="$router.back()" class="btn btn-secondary mt-2">
          ← Quay lại
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getVocabulary } from '../api/vocabulary'
import VocabularyCard from '../components/VocabularyCard.vue'

const route = useRoute()
const vocabularies = ref([])
const loading = ref(true)

onMounted(async () => {
  try {
    const res = await getVocabulary(route.params.id)
    vocabularies.value = res.data.data
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }
})
</script>
