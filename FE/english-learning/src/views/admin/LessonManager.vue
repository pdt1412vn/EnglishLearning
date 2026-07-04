<template>
  <div class="container-fluid mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2 class="fw-bold">Quản lý bài học</h2>
      <button class="btn btn-primary" @click="openCreate">+ Thêm bài học</button>
    </div>

    <div class="mb-3">
      <label class="form-label fw-semibold">Lọc theo khóa học:</label>
      <select v-model="selectedCourse" class="form-select w-auto d-inline-block ms-2" @change="filterLessons">
        <option value="">-- Tất cả --</option>
        <option v-for="c in courses" :key="c.id" :value="c.id">{{ c.title }}</option>
      </select>
    </div>

    <div v-if="loading" class="text-center py-5">
      <div class="spinner-border text-primary"></div>
    </div>

    <table v-else class="table table-bordered table-hover">
      <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th>Tiêu đề</th>
          <th>Khóa học ID</th>
          <th>Thứ tự</th>
          <th>Thời lượng</th>
          <th>Thao tác</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="lesson in lessons" :key="lesson.id">
          <td>{{ lesson.id }}</td>
          <td>{{ lesson.title }}</td>
          <td>{{ lesson.courseId }}</td>
          <td>{{ lesson.lessonOrder }}</td>
          <td>{{ lesson.duration }} phút</td>
          <td>
            <button class="btn btn-sm btn-warning me-1" @click="openEdit(lesson)">Sửa</button>
            <button class="btn btn-sm btn-danger" @click="deleteLesson(lesson.id)">Xóa</button>
          </td>
        </tr>
      </tbody>
    </table>

    <div v-if="showModal" class="modal d-block" tabindex="-1" style="background:rgba(0,0,0,0.5)">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ editId ? 'Sửa bài học' : 'Thêm bài học' }}</h5>
            <button class="btn-close" @click="showModal = false"></button>
          </div>
          <div class="modal-body">
            <div class="mb-3">
              <label class="form-label">Khóa học</label>
              <select v-model="form.courseId" class="form-select">
                <option v-for="c in courses" :key="c.id" :value="c.id">{{ c.title }}</option>
              </select>
            </div>
            <div class="mb-3">
              <label class="form-label">Tiêu đề</label>
              <input v-model="form.title" class="form-control" />
            </div>
            <div class="mb-3">
              <label class="form-label">Mô tả</label>
              <textarea v-model="form.description" class="form-control" rows="2"></textarea>
            </div>
            <div class="mb-3">
              <label class="form-label">Video URL</label>
              <input v-model="form.videoUrl" class="form-control" />
            </div>
            <div class="row">
              <div class="col mb-3">
                <label class="form-label">Thứ tự</label>
                <input v-model.number="form.lessonOrder" type="number" class="form-control" />
              </div>
              <div class="col mb-3">
                <label class="form-label">Thời lượng (phút)</label>
                <input v-model.number="form.duration" type="number" class="form-control" />
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button class="btn btn-secondary" @click="showModal = false">Hủy</button>
            <button class="btn btn-primary" @click="saveLesson">Lưu</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getCourses } from '../../api/course'
import { getLessonByCourse } from '../../api/lesson'
import api from '../../api/axios'

const courses = ref([])
const lessons = ref([])
const loading = ref(true)
const showModal = ref(false)
const editId = ref(null)
const selectedCourse = ref('')
const form = ref({ courseId: '', title: '', description: '', videoUrl: '', lessonOrder: 1, duration: 30, status: 1 })

async function load() {
  loading.value = true
  try {
    const [cRes, lRes] = await Promise.all([
      getCourses(),
      api.get('/lessons'),
    ])
    courses.value = cRes.data.data || []
    lessons.value = lRes.data.data || []
  } finally {
    loading.value = false
  }
}

async function filterLessons() {
  loading.value = true
  try {
    if (selectedCourse.value) {
      const res = await getLessonByCourse(selectedCourse.value)
      lessons.value = res.data.data || []
    } else {
      const res = await api.get('/lessons')
      lessons.value = res.data.data || []
    }
  } finally {
    loading.value = false
  }
}

function openCreate() {
  editId.value = null
  form.value = { courseId: courses.value[0]?.id || '', title: '', description: '', videoUrl: '', lessonOrder: 1, duration: 30, status: 1 }
  showModal.value = true
}

function openEdit(lesson) {
  editId.value = lesson.id
  form.value = { ...lesson }
  showModal.value = true
}

async function saveLesson() {
  try {
    if (editId.value) {
      await api.put('/lessons/' + editId.value, form.value)
    } else {
      await api.post('/lessons', form.value)
    }
    showModal.value = false
    await load()
  } catch (e) {
    alert('Lỗi: ' + (e.response?.data?.message || e.message))
  }
}

async function deleteLesson(id) {
  if (!confirm('Xóa bài học này?')) return
  try {
    await api.delete('/lessons/' + id)
    await load()
  } catch (e) {
    alert('Lỗi xóa: ' + e.message)
  }
}

onMounted(load)
</script>
