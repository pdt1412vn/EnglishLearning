import { createRouter, createWebHistory } from 'vue-router'

import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Course from '../views/Course.vue'
import Lesson from '../views/Lesson.vue'
import Vocabulary from '../views/Vocabulary.vue'
import Quiz from '../views/Quiz.vue'
import Result from '../views/Result.vue'
import Profile from '../views/Profile.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/login', component: Login },
  { path: '/register', component: Register },
  { path: '/courses', component: Course },
  { path: '/course/:id', component: () => import('../views/CourseDetail.vue') },
  { path: '/lesson/:id', component: Lesson },
  { path: '/vocabulary/:id', component: Vocabulary },
  { path: '/quiz/:id', component: Quiz },
  { path: '/result', name: 'Result', component: Result },
  { path: '/profile', component: Profile },

  // Admin routes
  {
    path: '/admin',
    component: () => import('../layouts/AdminLayout.vue'),
    children: [
      { path: '', redirect: '/admin/dashboard' },
      { path: 'dashboard', component: () => import('../views/admin/Dashboard.vue') },
      { path: 'courses', component: () => import('../views/admin/CourseManager.vue') },
      { path: 'lessons', component: () => import('../views/admin/LessonManager.vue') },
      { path: 'vocabularies', component: () => import('../views/admin/VocabularyManager.vue') },
      { path: 'quizzes', component: () => import('../views/admin/QuizManager.vue') },
      { path: 'users', component: () => import('../views/admin/UserManager.vue') },
    ],
  },
]

export default createRouter({
  history: createWebHistory(),
  routes,
})