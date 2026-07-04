<template>
  <div class="container-fluid mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2 class="fw-bold">Quản lý Quiz</h2>
      <button class="btn btn-primary" @click="openCreate">+ Thêm câu hỏi</button>
    </div>

    <div v-if="loading" class="text-center py-5">
      <div class="spinner-border text-primary"></div>
    </div>

    <table v-else class="table table-bordered table-hover">
      <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th>Câu hỏi</th>
          <th>Bài học ID</th>
          <th>Loại</th>
          <th>Số đáp án</th>
          <th>Thao tác</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="quiz in quizzes" :key="quiz.id">
          <td>{{ quiz.id }}</td>
          <td>{{ quiz.question }}</td>
          <td>{{ quiz.lessonId }}</td>
          <td><span class="badge bg-info text-dark">{{ quiz.quizType }}</span></td>
          <td>{{ quiz.answers?.length || 0 }}</td>
          <td>
            <button class="btn btn-sm btn-warning me-1" @click="openEdit(quiz)">Sửa</button>
            <button class="btn btn-sm btn-danger" @click="deleteQuiz(quiz.id)">Xóa</button>
          </td>
        </tr>
      </tbody>
    </table>

    <div v-if="showModal" class="modal d-block" tabindex="-1" style="background:rgba(0,0,0,0.5)">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ editId ? 'Sửa câu hỏi' : 'Thêm câu hỏi' }}</h5>
            <button class="btn-close" @click="showModal = false"></button>
          </div>
          <div class="modal-body">
            <div class="mb-3">
              <label class="form-label">Bài học ID</label>
              <input v-model.number="form.lessonId" type="number" class="form-control" />
            </div>
            <div class="mb-3">
              <label class="form-label">Câu hỏi</label>
              <textarea v-model="form.question" class="form-control" rows="2"></textarea>
            </div>
            <div class="mb-3">
              <label class="form-label">Loại quiz</label>
              <select v-model="form.quizType" class="form-select">
                <option>MULTIPLE_CHOICE</option>
                <option>TRUE_FALSE</option>
                <option>FILL_IN</option>
              </select>
            </div>
            <div class="mb-3">
              <label class="form-label">Giải thích</label>
              <input v-model="form.explanation" class="form-control" />
            </div>

            <hr />
            <h6>Đáp án</h6>
            <div v-for="(ans, i) in form.answers" :key="i" class="d-flex gap-2 mb-2 align-items-center">
              <input v-model="ans.answerContent" class="form-control" :placeholder="'Đáp án ' + (i+1)" />
              <div class="form-check">
                <input v-model="ans.correct" type="checkbox" class="form-check-input" :id="'correct-' + i" />
                <label class="form-check-label" :for="'correct-' + i">Đúng</label>
              </div>
              <button class="btn btn-sm btn-outline-danger" @click="removeAnswer(i)">✕</button>
            </div>
            <button class="btn btn-outline-secondary btn-sm mt-1" @click="addAnswer">+ Thêm đáp án</button>
          </div>
          <div class="modal-footer">
            <button class="btn btn-secondary" @click="showModal = false">Hủy</button>
            <button class="btn btn-primary" @click="saveQuiz">Lưu</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import api from '../../api/axios'

const quizzes = ref([])
const loading = ref(true)
const showModal = ref(false)
const editId = ref(null)
const form = ref({ lessonId: '', question: '', quizType: 'MULTIPLE_CHOICE', explanation: '', answers: [] })

async function load() {
  loading.value = true
  try {
    const res = await api.get('/quizzes')
    quizzes.value = res.data.data || []
  } finally {
    loading.value = false
  }
}

function openCreate() {
  editId.value = null
  form.value = { lessonId: '', question: '', quizType: 'MULTIPLE_CHOICE', explanation: '', answers: [{ answerContent: '', correct: false }] }
  showModal.value = true
}

function openEdit(quiz) {
  editId.value = quiz.id
  form.value = {
    lessonId: quiz.lessonId,
    question: quiz.question,
    quizType: quiz.quizType,
    explanation: quiz.explanation,
    answers: (quiz.answers || []).map(a => ({ ...a })),
  }
  showModal.value = true
}

function addAnswer() {
  form.value.answers.push({ answerContent: '', correct: false })
}

function removeAnswer(i) {
  form.value.answers.splice(i, 1)
}

async function saveQuiz() {
  try {
    if (editId.value) {
      await api.put('/quizzes/' + editId.value, form.value)
    } else {
      await api.post('/quizzes', form.value)
    }
    showModal.value = false
    await load()
  } catch (e) {
    alert('Lỗi: ' + (e.response?.data?.message || e.message))
  }
}

async function deleteQuiz(id) {
  if (!confirm('Xóa câu hỏi này?')) return
  try {
    await api.delete('/quizzes/' + id)
    await load()
  } catch (e) {
    alert('Lỗi xóa: ' + e.message)
  }
}

onMounted(load)
</script>
