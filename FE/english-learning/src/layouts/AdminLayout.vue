<template>
  <div class="d-flex" style="min-height:100vh">
    <!-- Sidebar -->
    <nav class="bg-dark text-white p-3" style="width:220px;min-height:100vh;flex-shrink:0">
      <div class="fw-bold fs-5 mb-4 text-warning">⚙ Admin Panel</div>
      <ul class="nav flex-column gap-1">
        <li>
          <router-link to="/admin/dashboard" class="nav-link text-white" active-class="text-warning fw-bold">
            📊 Dashboard
          </router-link>
        </li>
        <li>
          <router-link to="/admin/courses" class="nav-link text-white" active-class="text-warning fw-bold">
            📚 Khóa học
          </router-link>
        </li>
        <li>
          <router-link to="/admin/lessons" class="nav-link text-white" active-class="text-warning fw-bold">
            📖 Bài học
          </router-link>
        </li>
        <li>
          <router-link to="/admin/vocabularies" class="nav-link text-white" active-class="text-warning fw-bold">
            📝 Từ vựng
          </router-link>
        </li>
        <li>
          <router-link to="/admin/quizzes" class="nav-link text-white" active-class="text-warning fw-bold">
            ❓ Quiz
          </router-link>
        </li>
        <li>
          <router-link to="/admin/users" class="nav-link text-white" active-class="text-warning fw-bold">
            👥 Người dùng
          </router-link>
        </li>
        <li class="mt-3">
          <router-link to="/" class="nav-link text-white-50">← Về trang chính</router-link>
        </li>
      </ul>
    </nav>

    <!-- Content -->
    <div class="flex-grow-1 p-4 bg-light">
      <RouterView />
    </div>
  </div>
</template>
