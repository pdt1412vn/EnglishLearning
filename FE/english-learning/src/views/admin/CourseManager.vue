<template>
  <div class="container-fluid mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2 class="fw-bold">Quản lý khóa học</h2>
      <button class="btn btn-primary" @click="openCreate">+ Thêm khóa học</button>
    </div>

    <div v-if="loading" class="text-center py-5">
      <div class="spinner-border text-primary"></div>
    </div>

    <table v-else class="table table-bordered table-hover">
      <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th>Tiêu đề</th>
          <th>Cấp độ</th>
          <th>Trạng thái</th>
          <th>Thao tác</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="course in courses" :key="course.id">
          <td>{{ course.id }}</td>
          <td>{{ course.title }}</td>
          <td><span class="badge bg-success">{{ course.level }}</span></td>
          <td><span class="badge" :class="course.status === 1 ? 'bg-primary' : 'bg-secondary'">{{ course.status === 1 ? 'Hiển thị' : 'Ẩn' }}</span></td>
          <td>
            <button class="btn btn-sm btn-warning me-1" @click="openEdit(course)">Sửa</button>
            <button class="btn btn-sm btn-danger" @click="deleteCourse(course.id)">Xóa</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Modal -->
    <div v-if="showModal" class="modal d-block" tabindex="-1" style="background:rgba(0,0,0,0.5)">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ editId ? 'Sửa khóa học' : 'Thêm khóa học' }}</h5>
            <button class="btn-close" @click="showModal = false"></button>
          </div>
          <div class="modal-body">
            <div class="mb-3">
              <label class="form-label">Tiêu đề</label>
              <input v-model="form.title" class="form-control" />
            </div>
            <div class="mb-3">
              <label class="form-label">Mô tả</label>
              <textarea v-model="form.description" class="form-control" rows="3"></textarea>
            </div>
            <div class="mb-3">
              <label class="form-label">Ảnh (URL)</label>
              <input v-model="form.image" class="form-control" />
            </div>
            <div class="mb-3">
              <label class="form-label">Cấp độ</label>
              <select v-model="form.level" class="form-select">
                <option>BEGINNER</option>
                <option>INTERMEDIATE</option>
                <option>ADVANCED</option>
              </select>
            </div>
            <div class="mb-3">
              <label class="form-label">Trạng thái</label>
              <select v-model="form.status" class="form-select">
                <option :value="1">Hiển thị</option>
                <option :value="0">Ẩn</option>
              </select>
            </div>
          </div>
          <div class="modal-footer">
            <button class="btn btn-secondary" @click="showModal = false">Hủy</button>
            <button class="btn btn-primary" @click="saveCourse">Lưu</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getCourses, getCourse } from '../../api/course'
import api from '../../api/axios'

const courses = ref([])
const loading = ref(true)
const showModal = ref(false)
const editId = ref(null)
const form = ref({ title: '', description: '', image: '', level: 'BEGINNER', status: 1 })

async function load() {
  loading.value = true
  try {
    const res = await getCourses()
    courses.value = res.data.data || []
  } finally {
    loading.value = false
  }
}

function openCreate() {
  editId.value = null
  form.value = { title: '', description: '', image: '', level: 'BEGINNER', status: 1 }
  showModal.value = true
}

function openEdit(course) {
  editId.value = course.id
  form.value = { ...course }
  showModal.value = true
}

async function saveCourse() {
  try {
    if (editId.value) {
      await api.put('/courses/' + editId.value, form.value)
    } else {
      await api.post('/courses', form.value)
    }
    showModal.value = false
    await load()
  } catch (e) {
    alert('Lỗi: ' + (e.response?.data?.message || e.message))
  }
}

async function deleteCourse(id) {
  if (!confirm('Xóa khóa học này?')) return
  try {
    await api.delete('/courses/' + id)
    await load()
  } catch (e) {
    alert('Lỗi xóa: ' + e.message)
  }
}

onMounted(load)
</script>
