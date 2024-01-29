<script setup>
import { useCourseStore } from "@/stores/CourseStore.js";
import {useUserStore} from "@/stores/UserStore.js";
import { createApiUrl } from "@/helper/ApiHelper.js";
import { onMounted, ref, computed, onBeforeMount } from "vue";
import { useRoute, useRouter } from "vue-router";
import CourseCard from "@/components/CourseCard.vue";
import { useAuthStore } from "@/stores/AuthStore.js";

import jwtDecode from "jwt-decode";

const courseStore = useCourseStore()
const router = useRouter(); // router
const isVisible = ref(false);
const userStore = useUserStore();


const courses = computed(() => courseStore.courses);
const userCourses = computed(() => courseStore.userCourses);
const userId = jwtDecode(localStorage.getItem("accessToken")).userId;

onBeforeMount(() => {
  const userId = jwtDecode(localStorage.getItem("accessToken")).userId;
  console.log(userId);
  console.log('before mount');
});


onMounted(() => {
  const userId = jwtDecode(localStorage.getItem("accessToken")).userId;
  console.log(userId);
  showCourses();
  showUserCourses(userId);
  console.log('mounted');
  console.log('userCourses = ' + userCourses.data);
});


const err = false;


async function showCourses() {
  await courseStore.showGuestCourses();
}

showCourses();

async function showUserCourses() {
  await courseStore.showUserCourses(userId);
}

showUserCourses();

</script>

<template>
<div>
  <h2 style="text-align: center;" class="mx-auto">Verfügbare Kurse: </h2>
  <v-row class="d-flex ma-2 ">
    <v-col v-for="course in courses" :key="course.courseId" cols="12" sm="6" md="4" lg="3">
      <CourseCard
              
                  :courseTitle="course.courseTitle"
                  :startDate="course.startDate"
                  :description="course.description"
                  :courseId="course.courseId"
                  :teacher="course.teacher"
                  
      />
    </v-col>
  </v-row>
</div>

<div>
  <h2 style="text-align: center;" class="mx-auto">Gebuchte Kurse: </h2>
  <v-row class="d-flex ma-2 ">
    <v-col v-for="courses in userCourses" :key="courses.courseId" cols="12" sm="6" md="4" lg="3">
      <CourseCard
        :courseTitle="courses.courseTitle"
        :startDate="courses.startDate"
        :description="courses.description"
        :courseId="courses.courseId"
        :teacher="courses.teacher"
      />
    </v-col>
  </v-row>
</div>

</template>
<style scoped>

</style>
