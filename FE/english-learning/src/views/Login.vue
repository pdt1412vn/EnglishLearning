<template>
  <div class="container mt-5" style="max-width:480px">
    <div class="card shadow p-4">
      <h2 class="fw-bold text-center mb-4">Đăng nhập</h2>

      <div v-if="error" class="alert alert-danger">{{ error }}</div>

      <form @submit.prevent="handleLogin">
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
          Đăng nhập
        </button>
      </form>

      <p class="text-center mt-3 mb-0">
        Chưa có tài khoản?
        <router-link to="/register">Đăng ký ngay</router-link>
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/auth'

const router = useRouter()
const authStore = useAuthStore()

const form = ref({ email: '', password: '' })
const error = ref('')
const loading = ref(false)

async function handleLogin() {
  error.value = ''
  loading.value = true
  try {
    const data = await authStore.login(form.value)
    if (data.user.role === 'ADMIN') {
      router.push('/admin/dashboard')
    } else {
      router.push('/')
    }
  } catch (e) {
    error.value = e.response?.data?.message || 'Đăng nhập thất bại'
  } finally {
    loading.value = false
  }
}
</script>
