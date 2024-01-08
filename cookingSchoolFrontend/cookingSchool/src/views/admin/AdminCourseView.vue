<script setup>
import { useCourseStore } from "@/stores/CourseStore.js";
import { createApiUrl } from "@/helper/ApiHelper.js";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import CourseForm from "@/components/CourseForm.vue";

const courseStore = useCourseStore()
const router = useRouter();
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


async function deleteCourse(courseId){

  console.log('Deleted')
  await courseStore.deleteCourse(courseId)
}

function showForm(){
  console.log('showForm called');
  isVisible.value = true
  console.log('isVisible', isVisible.value)
}

</script>

<template>
  <!-- Übersicht aller Kurse zum Bearbeiten für den Admin -->
  <div>
    <v-btn @click ="showForm">Add new Course</v-btn>
    <p v-if="isVisible">CourseForm is visible</p>
    <CourseForm v-if="isVisible" style="display: block;"  />
  </div>


   <div>

    <div class="course-list">
      <h1>Course List</h1>
      <v-row>
        <v-col v-for="course in courseStore.courses" :key="course.courseId" cols="12" sm="6" md="4" lg="3">
          <v-card>
            <v-img
                cover
                height="250"
                src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
            ></v-img>

            <v-card-title>{{ course.courseTitle }}</v-card-title>
            <v-card-subtitle>{{ course.startDate }}</v-card-subtitle>
            <v-card-text>{{ course.description }}</v-card-text>
            <v-card-actions>


              <v-btn @click="editCourse(course)" icon>
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
              <v-btn @click="deleteCourse(course.courseId)" icon>
                <v-icon>mdi-delete</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </div>
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

</style>



