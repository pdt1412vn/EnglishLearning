<template>
  <div class="container mt-5" style="max-width:480px">
    <div class="card shadow p-4">
      <h2 class="fw-bold text-center mb-4">Đăng ký tài khoản</h2>

      <div v-if="error" class="alert alert-danger">{{ error }}</div>
      <div v-if="success" class="alert alert-success">{{ success }}</div>

      <form @submit.prevent="handleRegister">
        <div class="mb-3">
          <label class="form-label">Họ và tên</label>
          <input
            v-model="form.fullName"
            type="text"
            class="form-control"
            placeholder="Nhập họ và tên"
            required
          />
        </div>

        <div class="mb-3">
          <label class="form-label">Email</label>
          <input
            v-model="form.email"
            type="email"
            class="form-control"
            placeholder="Nhập email"
            required
          />
        </div>

        <div class="mb-3">
          <label class="form-label">Mật khẩu</label>
          <input
            v-model="form.password"
            type="password"
            class="form-control"
            placeholder="Nhập mật khẩu"
            required
          />
        </div>

        <button type="submit" class="btn btn-primary w-100" :disabled="loading">
          <span v-if="loading" class="spinner-border spinner-border-sm me-2"></span>
          Đăng ký
        </button>
      </form>

      <p class="text-center mt-3 mb-0">
        Đã có tài khoản?
        <router-link to="/login">Đăng nhập</router-link>
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useAuthStore } from '../stores/auth'

const authStore = useAuthStore()
const form = ref({ fullName: '', email: '', password: '' })
const error = ref('')
const success = ref('')
const loading = ref(false)

async function handleRegister() {
  error.value = ''
  success.value = ''
  loading.value = true
  try {
    await authStore.register(form.value)
    success.value = 'Đăng ký thành công! Vui lòng đăng nhập.'
    form.value = { fullName: '', email: '', password: '' }
  } catch (e) {
    error.value = e.response?.data?.message || 'Đăng ký thất bại'
  } finally {
    loading.value = false
  }
}
</script>
