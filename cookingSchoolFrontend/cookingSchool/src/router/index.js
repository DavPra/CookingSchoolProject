import { createRouter, createWebHistory } from 'vue-router'
import AdminLayout from '../layouts/AdminLayout.vue'
import UserLayout from '../layouts/UserLayout.vue'
import HomeView from '../views/user/HomeView.vue'
import CourseView from '../views/user/CourseView.vue'
import RecipeView from '../views/user/RecipeView.vue'
import AboutView from '../views/user/AboutView.vue'
import RegistrationView from '../views/user/RegistrationView.vue'
import AdminView from '../views/admin/AdminView.vue'
import AdminCourseView from '../views/admin/AdminCourseView.vue'
import AdminRecipeView from '../views/admin/AdminRecipeView.vue'
import AdminUserView from '../views/admin/AdminUserView.vue'

const routes = [
    {
      path: '/',
      component: UserLayout,
      children: [
        {
          path: '',
          component: HomeView
        },
        {
          path: '/courses',
          component: CourseView
        },
        {
          path: '/recipes',
          component: RecipeView
        },
        {
          path: '/about',
          component: AboutView
        },
        {
          path: '/register',
          component: RegistrationView
        }
      ]
    },
    {
      path: '/admin',
      component: AdminLayout,
      children: [
        {
          path: '/',
          component: AdminView
        },
        {
          path: '/courses',
          component: AdminCourseView
        },
        {
          path: '/recipes',
          component: AdminRecipeView
        },
        {
          path: '/users',
          component: AdminUserView
        }
      ]
    }
  ]

const router = createRouter({
  routes,
  history: createWebHistory()
})

export {router}