<script setup>
import { useCourseStore } from "@/stores/CourseStore.js";
import { createApiUrl } from "@/helper/ApiHelper.js";
import { onMounted, ref, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import CourseForm from "@/components/CourseForm.vue";
import CourseCard from "@/components/CourseCard.vue";
import { useAuthStore } from "@/stores/AuthStore.js";

const courseStore = useCourseStore()
const router = useRouter(); // router
const isVisible = ref(false);
const authStore = useAuthStore();


const courses = computed(() => courseStore.courses);


onMounted(() => {
  showCourses();
  showUserCourses();
  console.log('mounted');
});


const err = false;


async function showCourses() {
  await courseStore.showCourses();
}

showCourses();

async function showUSerCourses() {
  await courseStore.showUserCourses();
}

showUSerCourses();

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
<style scoped>

</style>
