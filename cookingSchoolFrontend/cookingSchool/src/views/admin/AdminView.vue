
<script setup>
import { useCourseStore } from "@/stores/courseStore.js";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";

const courseStore = useCourseStore()
const router = useRouter();

onMounted(() => {
  showCourses();
  console.log('mounted');
});


const err = false;
const data = ref({
  courseTitle: '',
  description: '',
  teacher: '',
  startDate: '',
  maxAttendants: '',
  price: '',
});

async function createCourse() {
  console.log('createCourse function called');
  try {
    await courseStore.createCourse(data.value);
    router.push('/');
  } catch (err) {
    if (err.isAxiosError && err.status === 401) {
      console.log(err);
      return (err = true);
    }
  }
}

async function showCourses() {
  await courseStore.showCourses();
}

showCourses();
</script>

<template>
  <h1>create Course 1</h1>
  <div>
    <form @submit.preven="createCourse">
      <label>title
        <input type="text" v-model="data.courseTitle">
      </label>
      <label>description
        <input type="text" v-model="data.description">
      </label>
      <label>teacher
        <input type="text" v-model="data.teacher">
      </label>
      <label>startDate
        <input type="text" v-model="data.startDate">
      </label>
      <label>maxAttendants
        <input type="number" v-model="data.maxAttendants">
      </label>
      <label>price
        <input type="number" v-model="data.price">
      </label>
      <button type="submit">save</button>
    </form>
  </div>

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

<style>

</style>
