<script setup>
import {useCourseStore} from "@/stores/CourseStore.js";
import {onMounted, ref, computed} from "vue";
import CourseCard from "@/components/CourseCard.vue";

const courseStore = useCourseStore();

const courses = computed(() => {
  // Filtern Sie die Kurse auf die nächsten 3 basierend auf dem Startdatum
  const currentDate = new Date();
  return courseStore.courses
      .filter(course => new Date(course.startDate) > currentDate)
      .slice(0, 3);
});

onMounted(() => {
  ShowCourses();
  console.log('mounted');
});

async function ShowCourses() {
  await courseStore.showCourses();
}
</script>

<template>
  <div>
    <h1 class="ms-6 text-center">Unsere nächsten Events:</h1>
    <div>
      <v-row class="d-flex ma-2 mb-10 justify-center ">
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
  </div>
</template>


