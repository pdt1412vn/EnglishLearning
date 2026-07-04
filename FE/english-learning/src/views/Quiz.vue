<template>
  <div class="container mt-5">
    <div v-if="loading" class="text-center py-5">
      <div class="spinner-border text-primary"></div>
    </div>

    <div v-else-if="quizzes.length === 0" class="alert alert-info">
      Chưa có câu hỏi nào cho bài học này.
    </div>

    <div v-else>
      <!-- Quiz done -->
      <div v-if="done">
        <div class="text-center">
          <h2 class="fw-bold">🎉 Hoàn thành!</h2>
          <p class="fs-4">
            Bạn đúng <strong>{{ score }}</strong> / <strong>{{ quizzes.length }}</strong> câu
          </p>
          <router-link
            :to="{ name: 'Result', query: { score, total: quizzes.length, lessonId: route.params.id } }"
            class="btn btn-success me-2"
          >
            Xem kết quả
          </router-link>
          <button class="btn btn-outline-primary" @click="restart">Làm lại</button>
        </div>
      </div>

      <!-- Quiz in progress -->
      <div v-else>
        <div class="d-flex justify-content-between mb-3">
          <h4 class="fw-bold">Câu {{ current + 1 }} / {{ quizzes.length }}</h4>
          <span class="badge bg-primary fs-6">Điểm: {{ score }}</span>
        </div>

        <div class="progress mb-4" style="height:8px">
          <div
            class="progress-bar"
            :style="{ width: ((current) / quizzes.length * 100) + '%' }"
          ></div>
        </div>

        <div class="card shadow p-4">
          <h5 class="mb-4">{{ quizzes[current].question }}</h5>

          <div
            v-for="answer in quizzes[current].answers"
            :key="answer.id"
            class="d-grid mb-2"
          >
            <button
              class="btn text-start"
              :class="answerClass(answer)"
              :disabled="answered"
              @click="selectAnswer(answer)"
            >
              {{ answer.answerContent }}
            </button>
          </div>

          <div v-if="answered" class="mt-3">
            <div v-if="selectedAnswer?.correct" class="alert alert-success mb-2">
              ✅ Chính xác!
            </div>
            <div v-else class="alert alert-danger mb-2">
              ❌ Sai rồi!
              <span v-if="quizzes[current].explanation">
                Giải thích: {{ quizzes[current].explanation }}
              </span>
            </div>
            <button class="btn btn-primary" @click="nextQuestion">
              {{ current + 1 < quizzes.length ? 'Câu tiếp theo →' : 'Kết thúc' }}
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getQuiz } from '../api/quiz'

const route = useRoute()
const quizzes = ref([])
const loading = ref(true)
const current = ref(0)
const score = ref(0)
const answered = ref(false)
const selectedAnswer = ref(null)
const done = ref(false)

onMounted(async () => {
  try {
    const res = await getQuiz(route.params.id)
    quizzes.value = res.data.data
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }
})

function selectAnswer(answer) {
  if (answered.value) return
  selectedAnswer.value = answer
  answered.value = true
  if (answer.correct) score.value++
}

function answerClass(answer) {
  if (!answered.value) return 'btn-outline-secondary'
  if (answer.correct) return 'btn-success'
  if (answer.id === selectedAnswer.value?.id) return 'btn-danger'
  return 'btn-outline-secondary'
}

function nextQuestion() {
  if (current.value + 1 < quizzes.value.length) {
    current.value++
    answered.value = false
    selectedAnswer.value = null
  } else {
    done.value = true
  }
}

function restart() {
  current.value = 0
  score.value = 0
  answered.value = false
  selectedAnswer.value = null
  done.value = false
}
</script>
