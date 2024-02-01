<script setup>
import {useCourseStore} from "@/stores/CourseStore.js";
import {onMounted, ref, computed, onBeforeMount} from "vue";
import {useRouter} from "vue-router";
import CourseCard from "@/components/CourseCard.vue";
import jwtDecode from "jwt-decode";

const courseStore = useCourseStore()
const router = useRouter(); // router
const isVisible = ref(false);
const courses = computed(() => courseStore.courses);
const userCourses = computed(() => courseStore.userCourses);
const userId = jwtDecode(localStorage.getItem("accessToken")).userId;
const err = false;

onBeforeMount(() => {
  const userId = jwtDecode(localStorage.getItem("accessToken")).userId;
  console.log(userId);
  console.log('before mount');
});

onMounted(() => {
  const userId = jwtDecode(localStorage.getItem("accessToken")).userId;
  console.log(userId);
  ShowCourses();
  ShowUserCourses(userId);
  console.log('mounted');
  console.log('userCourses = ' + userCourses.data);
});

async function ShowCourses() {
  await courseStore.showCourses();
}

ShowCourses();

async function ShowUserCourses() {
  await courseStore.showUserCourses(userId);
}

ShowUserCourses();
</script>

<template>

  <v-sheet color="secondary">
    <h2 style="text-align: center;" class="mx-auto text-h4 py-10">Verfügbare Kurse: </h2>
    <v-row class="d-flex ma-2 ">
      <v-col v-for="course in courses" :key="course.courseId" cols="12" sm="6" md="4" lg="3">
        <CourseCard
            :courseTitle="course.courseTitle"
            :startDate="course.startDate"
            :description="course.description"
            :courseId="course.courseId"
            :teacher="course.teacher"
            :prize="course.price"
        />
      </v-col>
    </v-row>
  </v-sheet>

  <div>
    <h2 style="text-align: center;" class="mx-auto text-h4">Gebuchte Kurse: </h2>
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