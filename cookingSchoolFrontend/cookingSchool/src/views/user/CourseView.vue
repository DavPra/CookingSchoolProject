<script setup>
import { useCourseStore } from "@/stores/CourseStore.js";
import { createApiUrl } from "@/helper/ApiHelper.js";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import CourseForm from "@/components/CourseForm.vue";
import CourseCard from "@/components/CourseCard.vue";

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
  <CourseCard v-for="course in courseStore.courses" :key="course.courseId"/>
</div>



</template>
<style scoped>

</style>
