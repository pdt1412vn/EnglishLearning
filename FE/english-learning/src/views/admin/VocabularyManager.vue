<template>
  <div class="container-fluid mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2 class="fw-bold">Quản lý từ vựng</h2>
      <button class="btn btn-primary" @click="openCreate">+ Thêm từ vựng</button>
    </div>

    <div v-if="loading" class="text-center py-5">
      <div class="spinner-border text-primary"></div>
    </div>

    <table v-else class="table table-bordered table-hover">
      <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th>Từ tiếng Anh</th>
          <th>Nghĩa tiếng Việt</th>
          <th>Phiên âm</th>
          <th>Bài học ID</th>
          <th>Thao tác</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="vocab in vocabularies" :key="vocab.id">
          <td>{{ vocab.id }}</td>
          <td class="fw-bold text-primary">{{ vocab.englishWord }}</td>
          <td>{{ vocab.vietnameseMeaning }}</td>
          <td>{{ vocab.pronunciation }}</td>
          <td>{{ vocab.lessonId }}</td>
          <td>
            <button class="btn btn-sm btn-warning me-1" @click="openEdit(vocab)">Sửa</button>
            <button class="btn btn-sm btn-danger" @click="deleteVocab(vocab.id)">Xóa</button>
          </td>
        </tr>
      </tbody>
    </table>

    <div v-if="showModal" class="modal d-block" tabindex="-1" style="background:rgba(0,0,0,0.5)">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ editId ? 'Sửa từ vựng' : 'Thêm từ vựng' }}</h5>
            <button class="btn-close" @click="showModal = false"></button>
          </div>
          <div class="modal-body">
            <div class="mb-3">
              <label class="form-label">Bài học ID</label>
              <input v-model.number="form.lessonId" type="number" class="form-control" />
            </div>
            <div class="mb-3">
              <label class="form-label">Từ tiếng Anh</label>
              <input v-model="form.englishWord" class="form-control" />
            </div>
            <div class="mb-3">
              <label class="form-label">Nghĩa tiếng Việt</label>
              <input v-model="form.vietnameseMeaning" class="form-control" />
            </div>
            <div class="mb-3">
              <label class="form-label">Phiên âm</label>
              <input v-model="form.pronunciation" class="form-control" placeholder="/prəˌnʌnsiˈeɪʃən/" />
            </div>
            <div class="mb-3">
              <label class="form-label">Ví dụ</label>
              <input v-model="form.exampleSentence" class="form-control" />
            </div>
            <div class="mb-3">
              <label class="form-label">Ảnh (URL)</label>
              <input v-model="form.image" class="form-control" />
            </div>
            <div class="mb-3">
              <label class="form-label">Audio (URL)</label>
              <input v-model="form.audio" class="form-control" />
            </div>
          </div>
          <div class="modal-footer">
            <button class="btn btn-secondary" @click="showModal = false">Hủy</button>
            <button class="btn btn-primary" @click="saveVocab">Lưu</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import api from '../../api/axios'

const vocabularies = ref([])
const loading = ref(true)
const showModal = ref(false)
const editId = ref(null)
const form = ref({ lessonId: '', englishWord: '', vietnameseMeaning: '', pronunciation: '', exampleSentence: '', image: '', audio: '' })

async function load() {
  loading.value = true
  try {
    const res = await api.get('/vocabularies')
    vocabularies.value = res.data.data || []
  } finally {
    loading.value = false
  }
}

function openCreate() {
  editId.value = null
  form.value = { lessonId: '', englishWord: '', vietnameseMeaning: '', pronunciation: '', exampleSentence: '', image: '', audio: '' }
  showModal.value = true
}

function openEdit(vocab) {
  editId.value = vocab.id
  form.value = { ...vocab }
  showModal.value = true
}

async function saveVocab() {
  try {
    if (editId.value) {
      await api.put('/vocabularies/' + editId.value, form.value)
    } else {
      await api.post('/vocabularies', form.value)
    }
    showModal.value = false
    await load()
  } catch (e) {
    alert('Lỗi: ' + (e.response?.data?.message || e.message))
  }
}

async function deleteVocab(id) {
  if (!confirm('Xóa từ vựng này?')) return
  try {
    await api.delete('/vocabularies/' + id)
    await load()
  } catch (e) {
    alert('Lỗi xóa: ' + e.message)
  }
}

onMounted(load)
</script>
