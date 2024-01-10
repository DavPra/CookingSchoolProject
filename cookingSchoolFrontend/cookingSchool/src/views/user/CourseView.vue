<script setup>
import { useCourseStore } from "@/stores/CourseStore.js";
import { createApiUrl } from "@/helper/ApiHelper.js";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import CourseForm from "@/components/CourseForm.vue";

const courseStore = useCourseStore()
const router = useRouter(); // router
const isVisible = ref(false)

onMounted(() => {
  showCourses();
  console.log('mounted');
});


const err = false;


async function showCourses() {
  await courseStore.showCourses();
}

showCourses();

</script>

<template>
  <!-- Übersicht aller Kurse für User und Besucher -->


  <div>
    <h1>Course List</h1>
    <ul>
      <li v-for="course in courseStore.courses" :key="course.courseId">
        {{ course.courseId }}
        {{ course.courseTitle }}
        {{ course.description }}
        {{course.maxAttendants}}
        {{course.price}}
        {{course.startDate}}
        {{course.teacher}}

      </li>
    </ul>
  </div>



</template>
<style scoped>

</style>
