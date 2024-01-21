<script setup>
import { useCourseStore } from "@/stores/CourseStore.js";
import { createApiUrl } from "@/helper/ApiHelper.js";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import CourseForm from "@/components/CourseForm.vue";
import AdminCourseCard from "../../components/AdminCourseCard.vue";

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

function closeForm(){
  console.log('closeForm called')
  isVisible.value = false

}


</script>

<template>
  <div class="ma-2">
  <!-- Übersicht aller Kurse zum Bearbeiten für den Admin -->
  <div>
    <v-btn class="ma-2" @click ="showForm">Add new Course</v-btn>
    <p v-if="isVisible">CourseForm is visible</p>
  <CourseForm v-if="isVisible"  :closeForm="closeForm" style="display: block;"/>



    <div id="course-list" class="ma-2" >
      <h1 class="mx-auto">Your upcoming Courses</h1>
      <v-row class="d-flex ma-2">
        <v-col v-for="course in courseStore.courses" :key="course.courseId" cols="12" sm="6" md="4" lg="3">
          <AdminCourseCard
                           :key="course.courseId"
                           :courseTitle="course.courseTitle"
                           :startDate="course.startDate"
                           :description="course.description"
                           :courseId="course.courseId"
                           :teacher = "course.teacher"
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
  </div>
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



