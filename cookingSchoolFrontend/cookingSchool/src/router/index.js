import { createRouter, createWebHistory } from 'vue-router'
import AdminLayout from '../layouts/AdminLayout.vue'
import UserLayout from '../layouts/UserLayout.vue'
import GuestLayout from '../layouts/GuestLayout.vue'
import HomeView from '../views/guest/HomeView.vue'
import ProfileView from '../views/user/ProfileView.vue'
import UserCourseView from '../views/user/UserCourseView.vue'
import RecipeView from '../views/user/RecipeView.vue'
import AboutView from '../views/guest/AboutView.vue'
import RegistrationView from '../views/user/RegistrationView.vue'
import AdminCourseView from '../views/admin/AdminCourseView.vue'
import AdminRecipeView from '../views/admin/AdminRecipeView.vue'
import AdminUserView from '../views/admin/AdminUserView.vue'
import UserHomeView from '../views/user/UserHomeView.vue'
import GuestCourseView from '../views/guest/GuestCourseView.vue'
import UserAboutView from '../views/user/UserAboutView.vue'
import LoginView from "../views/guest/LoginView.vue";

//TODO: Kathy - add function that checks if user/admin is logged in and redirects to login page if not

const routes = [
    {
      path: '/user',
      component: UserLayout,
      children: [
        {
          path: '',
          component: UserHomeView
        },
        {
          path: '/profile',
          component: ProfileView
        },
        {
          path: '/courses',
          component: UserCourseView
        },
        {
          path: '/recipes',
          component: RecipeView
        },
        {
          path: '/about',
          component: UserAboutView
        }
      ]
    },
    {
      path: '/admin',
      component: AdminLayout,
      children: [
        {
          path: 'courses',
          component: AdminCourseView
        },
        {
          path: 'recipes',
          component: AdminRecipeView
        },
        {
          path: 'users',
          component: AdminUserView
        }
      ]
    },
    {
      path: '',
      component: GuestLayout,
      children: [
        {
          path: '',
          component: HomeView
        },
        {
          path: '/about',
          component: AboutView
        },
        {
          path: '/register',
          component: RegistrationView
        },
        {
          path: '/courses',
          component: GuestCourseView
        },
        {
          path: '/login',
          component: LoginView
        }
      ]
    }
  ]

const router = createRouter({
  routes,
  history: createWebHistory()
})

export {router}