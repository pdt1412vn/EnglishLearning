<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
      <router-link class="navbar-brand fw-bold" to="/">English Learning</router-link>

      <div class="d-flex align-items-center gap-2">
        <router-link class="btn btn-outline-light btn-sm" to="/courses">Khóa học</router-link>

        <template v-if="authStore.isLoggedIn">
          <router-link
            v-if="authStore.isAdmin"
            class="btn btn-warning btn-sm"
            to="/admin/dashboard"
          >
            Admin
          </router-link>
          <router-link class="btn btn-outline-light btn-sm" to="/profile">
            {{ authStore.user?.fullName }}
          </router-link>
          <button class="btn btn-danger btn-sm" @click="logout">Đăng xuất</button>
        </template>

        <template v-else>
          <router-link class="btn btn-outline-light btn-sm" to="/register">Đăng ký</router-link>
          <router-link class="btn btn-warning btn-sm" to="/login">Đăng nhập</router-link>
        </template>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/auth'

const router = useRouter()
const authStore = useAuthStore()

function logout() {
  authStore.logout()
  router.push('/login')
}
</script>