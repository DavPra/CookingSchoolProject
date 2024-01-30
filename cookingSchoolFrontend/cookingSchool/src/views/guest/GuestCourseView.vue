<script setup>

import { useCourseStore } from "@/stores/CourseStore.js";
import { createApiUrl } from "@/helper/ApiHelper.js";
import { onMounted, ref, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import CourseCard from "@/components/CourseCard.vue";
import { useAuthStore } from "@/stores/AuthStore.js";
import HeaderIcon from "@/components/HeaderIcon.vue";
import NextCourses from "@/components/NextCourses.vue";

const courseStore = useCourseStore()
const router = useRouter(); // router
const isVisible = ref(false);
const authStore = useAuthStore();


const courses = computed(() => courseStore.courses);


onMounted(() => {
  showCourses();
  console.log('mounted');
});


const err = false;


async function showCourses() {
  await courseStore.showGuestCourses();
}

showCourses();

</script>

<template>
  <HeaderIcon></HeaderIcon>
  <div class="container m-5">
    <v-sheet class="ms-6">
      <h1 class="font-weight-medium">Dein Erlebnis bei uns</h1>
      <h2>Was ist alles inkludiert?</h2>

        <p> -Mehrgängiges Menü, wir kochen 3 bis 4 Speisen</p>
        <p> -Jeder Teilnehmer bekommt die super duper tollen Rezepte</p>
        <p> -Unser Zutaten sind biologisch, regional und saisonal</p>
        <p> -ALLE Getränke</p>

    </v-sheet>

  </div>
  <div>
    <NextCourses/>
  </div>



  <div>
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
</template>