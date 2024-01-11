<script setup>
import { useCourseStore } from "@/stores/CourseStore.js";
import { createApiUrl } from "@/helper/ApiHelper.js";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import CourseForm from "@/components/CourseForm.vue";
import AdminCourseCard from "@/components/AdminCourseCard.vue";

//TODO v-cklick-outside npm error
const courseStore = useCourseStore()
const router = useRouter();
const isVisible = ref(false)
const err = false;

onMounted(() => {
  showCourses();
  console.log('mounted');
});


async function showCourses() {
  await courseStore.showCourses();
}

showCourses();

function showForm(){
  console.log('showForm called');
  isVisible.value = true
  console.log('isVisible', isVisible.value)
}

function closeForm() {
  isVisible.value = false;
}
</script>

<template>
  <!-- Übersicht aller Kurse zum Bearbeiten für den Admin -->
  <div>
    <v-btn @click ="showForm">Add new Course</v-btn>
    <p v-if="isVisible">CourseForm is visible</p>
    <CourseForm v-if="isVisible" style="display: block;"  />
    </div>

    <!-- <CourseForm v-if="isVisible" style="display: block;"  /> -->


    <!--
    <v-card v-if="isVisible" @click="closeForm"   class="modal-overlay">
      <CourseForm style="display: block;" />
    </v-card> -->



  <div id="course-list" >
    <h1>Your upcoming Courses</h1>
    <v-row class="d-flex">
      <v-col v-for="course in courseStore.courses" :key="course.courseId" cols="12" sm="6" md="4" lg="3"> -->
        <AdminCourseCard
                         :key="course.courseId"
                         :courseTitle="course.courseTitle"
                         :startDate="course.startDate"
                         :description="course.description"
                         :courseId="course.courseId"
                         :showForm = "showForm"
        />
      </v-col>
    </v-row>
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
<style scoped>

/*

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  z-index: 999;
}*/
</style>



