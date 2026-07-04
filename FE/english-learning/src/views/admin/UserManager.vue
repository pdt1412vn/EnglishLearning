<template>
  <div class="container-fluid mt-4">
    <h2 class="fw-bold mb-4">Quản lý người dùng</h2>

    <div v-if="loading" class="text-center py-5">
      <div class="spinner-border text-primary"></div>
    </div>

    <table v-else class="table table-bordered table-hover">
      <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th>Họ tên</th>
          <th>Email</th>
          <th>Vai trò</th>
          <th>Trạng thái</th>
          <th>Thao tác</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="u in users" :key="u.id">
          <td>{{ u.id }}</td>
          <td>{{ u.fullName }}</td>
          <td>{{ u.email }}</td>
          <td>
            <span class="badge" :class="u.role === 'ADMIN' ? 'bg-danger' : 'bg-primary'">
              {{ u.role }}
            </span>
          </td>
          <td>
            <span class="badge" :class="u.status === 1 ? 'bg-success' : 'bg-secondary'">
              {{ u.status === 1 ? 'Hoạt động' : 'Khóa' }}
            </span>
          </td>
          <td>
            <button class="btn btn-sm btn-danger" @click="deleteUser(u.id)">Xóa</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import api from '../../api/axios'

const users = ref([])
const loading = ref(true)

async function load() {
  loading.value = true
  try {
    const res = await api.get('/users')
    users.value = Array.isArray(res.data) ? res.data : (res.data.data || [])
  } finally {
    loading.value = false
  }
}

async function deleteUser(id) {
  if (!confirm('Xóa người dùng này?')) return
  try {
    await api.delete('/users/' + id)
    await load()
  } catch (e) {
    alert('Lỗi xóa: ' + e.message)
  }
}

onMounted(load)
</script>
