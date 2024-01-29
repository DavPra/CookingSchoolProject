<script setup>

import { useCourseStore } from "@/stores/CourseStore.js";
import { createApiUrl } from "@/helper/ApiHelper.js";
import { onMounted, ref, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import CourseCard from "@/components/CourseCard.vue";
import { useAuthStore } from "@/stores/AuthStore.js";
import HeaderIcon from "@/components/HeaderIcon.vue";

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