<script setup>
import {useCourseStore} from "@/stores/CourseStore";
const courseStore = useCourseStore()
const course = defineProps(['courseTitle','startDate','description', 'courseId'])

/*async function updateCourse(courseId){
  console.log('updateCourse function called')
  await courseStore.updateCourse(courseId)
  // await courseStore.showCourses()
}*/

async function deleteCourse(courseId){

  console.log('Deleted')
  await courseStore.deleteCourse(courseId)
}
</script>

<template>


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


            <v-btn @click="showForm" icon>
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn @click="deleteCourse(course.courseId)" icon>
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>

</template>

<style scoped>

</style>