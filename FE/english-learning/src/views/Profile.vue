<template>
  <div class="container mt-5" style="max-width:600px">
    <div class="card shadow p-4">
      <h2 class="fw-bold mb-4">Thông tin cá nhân</h2>

      <div v-if="!authStore.isLoggedIn" class="alert alert-warning">
        Vui lòng <router-link to="/login">đăng nhập</router-link> để xem thông tin.
      </div>

      <div v-else>
        <div class="text-center mb-4">
          <img
            :src="authStore.user?.avatar || 'https://ui-avatars.com/api/?name=' + authStore.user?.fullName"
            class="rounded-circle"
            width="100"
            height="100"
            style="object-fit:cover"
            alt="avatar"
          />
          <h4 class="mt-2">{{ authStore.user?.fullName }}</h4>
          <span class="badge" :class="authStore.isAdmin ? 'bg-danger' : 'bg-primary'">
            {{ authStore.user?.role }}
          </span>
        </div>

        <table class="table">
          <tbody>
            <tr>
              <th>Email</th>
              <td>{{ authStore.user?.email }}</td>
            </tr>
            <tr>
              <th>Vai trò</th>
              <td>{{ authStore.user?.role }}</td>
            </tr>
          </tbody>
        </table>

        <button class="btn btn-danger w-100 mt-3" @click="handleLogout">
          Đăng xuất
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/auth'

const router = useRouter()
const authStore = useAuthStore()

function handleLogout() {
  authStore.logout()
  router.push('/login')
}
</script>
